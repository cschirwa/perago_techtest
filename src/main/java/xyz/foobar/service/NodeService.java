package xyz.foobar.service;

import java.lang.reflect.Field;

import xyz.foobar.entity.InnerNode;
import xyz.foobar.entity.LeafNode;
import xyz.foobar.entity.NodeStatus;

public class NodeService {
	
	public NodeService() {}
	
	public static InnerNode createInnerNode(String value, NodeStatus status) {
		return new InnerNode(value, status);
	}
	
	public static InnerNode createInnerNode(Field field, NodeStatus status) {
		return new InnerNode(field.getName(), status);
	}

	public static LeafNode createLeafNode(Object original, Object modified, Field field) {
		return new LeafNode(original, modified, field);
	}

}
