package xyz.foobar.entity;

import java.util.ArrayList;
import java.util.List;

public class InnerNode extends Node {

	private List<InnerNode> innerNodes;
	
	private List<LeafNode> leafNodes;
	
	public InnerNode() {}
	
	public InnerNode(String name, String value, NodeStatus status) {
		super(name, value, status);
		this.innerNodes = new ArrayList<>();
		this.leafNodes = new ArrayList<>();
	}
	
	public InnerNode(String name, String value, NodeStatus status, List<InnerNode> innerNode, List<LeafNode> leafNode) {
		super(name, value, status);
		this.innerNodes = innerNode;
		this.leafNodes = leafNode;
	}
	
	public boolean isEmpty() {
		return leafNodes.isEmpty() && innerNodes.isEmpty();
	}
	
	public List<InnerNode> getInnerNodes() {
		return innerNodes;
	}
	
	public List<LeafNode> getLeafNodes() {
		return leafNodes;
	}
}
