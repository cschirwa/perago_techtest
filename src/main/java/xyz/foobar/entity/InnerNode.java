package xyz.foobar.entity;

import java.util.List;

public class InnerNode<T> extends Node {

	private Class type;
	
	private List<LeafNode> leafNodes;
	
	private List<InnerNode> innerNodes;
	
	public InnerNode() {}
	
	public InnerNode(Object key, String name, Class type) {
		super(key, name, NodeStatus.UPDATED);
	}
	
	
	public Class getType() {
		return type;
	}
	
	public List<LeafNode> getLeafNodes() {
		return leafNodes;
	}
	
	public List<InnerNode> getInnerNodes() {
		return innerNodes;
	}
}
