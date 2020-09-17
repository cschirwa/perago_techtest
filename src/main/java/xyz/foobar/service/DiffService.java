package xyz.foobar.service;

import java.io.Serializable;
import java.lang.reflect.Field;

import xyz.foobar.DiffEngine;
import xyz.foobar.DiffException;
import xyz.foobar.entity.Diff;
import xyz.foobar.entity.InnerNode;
import xyz.foobar.entity.LeafNode;
import xyz.foobar.entity.NodeStatus;

public class DiffService implements DiffEngine, Serializable {

	public <T extends Serializable> T apply(T original, Diff<?> diff) throws DiffException {
		
		return null;
	}

	public <T extends Serializable> Diff<T> calculate(T original, T modified)
			throws DiffException, IllegalArgumentException, IllegalAccessException {

		if (!original.getClass().equals(modified.getClass()))
			throw new DiffException("Cannot calculate diff on unequal classes");

//		Check for equality
		if (original != null && original.equals(modified))
			return null;

		NodeStatus status = (original == null && modified != null) ? NodeStatus.CREATED
				: (original != null && modified == null) ? NodeStatus.DELETED : NodeStatus.UPDATED;

		InnerNode root = NodeService.createInnerNode(null, original.getClass().getSimpleName(), status);
		diff(root, original, modified);
		return new Diff<>(root);
	}

	private static void diff(InnerNode node, Object original, Object modified)
			throws IllegalArgumentException, IllegalAccessException {

		Field[] originalFields = original.getClass().getDeclaredFields();
//		Field[] modifiedFields = modified.getClass().getDeclaredFields();

		Object originalValue, modifiedValue;
		NodeStatus status = null;
		InnerNode innerNode;
		LeafNode leafNode;
		for (Field field : originalFields) {
			field.setAccessible(true);
			originalValue = field.get(original);
			modifiedValue = field.get(modified);

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
			leafNode = NodeService.createLeafNode(originalValue, modifiedValue);
			node.getInnerNodes().add(innerNode);
			node.getLeafNodes().add(leafNode);

		}
	}
}
