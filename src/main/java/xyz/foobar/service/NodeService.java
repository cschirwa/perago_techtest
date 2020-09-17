package xyz.foobar.service;

import java.lang.reflect.Field;

import xyz.foobar.entity.InnerNode;
import xyz.foobar.entity.LeafNode;
import xyz.foobar.entity.Node;
import xyz.foobar.entity.NodeStatus;

public class NodeService {
	
	public NodeService() {}
	
	public static InnerNode createInnerNode(String name, String value, NodeStatus status) {
		return new InnerNode(name, value, status);
	}
	
	public static InnerNode createInnerNode(Field field, NodeStatus status) {
		return new InnerNode(field.getName(), "test-field", status);
	}

	public static LeafNode createLeafNode(Object original, Object modified) {
		return new LeafNode(original, modified);
	}

}
