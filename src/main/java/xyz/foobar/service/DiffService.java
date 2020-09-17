package xyz.foobar.service;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import xyz.foobar.DiffEngine;
import xyz.foobar.DiffException;
import xyz.foobar.entity.Diff;
import xyz.foobar.entity.InnerNode;
import xyz.foobar.entity.LeafNode;
import xyz.foobar.entity.NodeStatus;

public class DiffService implements DiffEngine {

	public <T extends Serializable> T apply(T original, Diff<?> diff) throws DiffException {
		T result = null;

		if (!diff.getRoot().getValue().equals(original.getClass().getSimpleName()))
			throw new DiffException("Cannot apply diff on different class types");
		
		result = original;
		
		List<Field> diffFields = new ArrayList<>();

		for(LeafNode node : diff.getRoot().getLeafNodes()) {
			diffFields.add(node.getField());
		}
		for(int i = 0; i< diffFields.size(); i++) { 
			Field field = diffFields.get(i);
			try {
				if(field.getName().equals("serialVersionUID"))
					continue;
				
				field.setAccessible(true);
				field.set(result, diff.getRoot().getLeafNodes().get(i).getModified());
			} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
				throw new DiffException(e.getMessage());
			} 
		}
		return result;
	}


	public <T extends Serializable> Diff<T> calculate(T original, T modified)
			throws DiffException, IllegalArgumentException, IllegalAccessException {

		if (original == null && modified == null)
			throw new DiffException("Cannot calculate diff on null objects");

		if (original != null && modified != null && !original.getClass().equals(modified.getClass()))
			throw new DiffException("Cannot calculate diff on different class types");

//		Check for equality
		if (original != null && original.equals(modified))
			return null;

		NodeStatus status = (original == null && modified != null) ? NodeStatus.CREATED
				: (original != null && modified == null) ? NodeStatus.DELETED : NodeStatus.UPDATED;
		
		InnerNode root = original == null ? NodeService.createInnerNode(modified.getClass().getSimpleName(), status) 
				: NodeService.createInnerNode(original.getClass().getSimpleName(), status);

		diff(root, original, modified);
		return new Diff<>(root);
	}

	private static void diff(InnerNode node, Object original, Object modified) {

		Field[] fields = original == null ? modified.getClass().getDeclaredFields() : original.getClass().getDeclaredFields();

		Object originalValue, modifiedValue;
		NodeStatus status = null;
		InnerNode innerNode;
		LeafNode leafNode;
		for (Field field : fields) {
			field.setAccessible(true);
			
			//Ignore serialVersionUID - ignore field for diffing
			if(field.getName().equals("serialVersionUID"))
				continue;
			
			try {
				originalValue = original == null ? null : field.get(original);
				modifiedValue = modified == null ? null : field.get(modified);
				if(originalValue == null && modifiedValue == null) {
					continue;
				}
				if (originalValue != null && originalValue.equals(modifiedValue)) {
					continue;
				}
				if (originalValue != null && modifiedValue == null) {
					status = NodeStatus.DELETED;
				}
				if (originalValue != null && modifiedValue != null) {
					status = NodeStatus.UPDATED;
				}
				if ((originalValue == null) && (modifiedValue != null)) {
					status = NodeStatus.CREATED;
				}
				innerNode = NodeService.createInnerNode(field, status);
				leafNode = NodeService.createLeafNode(originalValue, modifiedValue, field);
				node.getInnerNodes().add(innerNode);
				if(modified != null)
					node.getLeafNodes().add(leafNode);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new DiffException(e.getMessage());
			}
		}

	}

}
