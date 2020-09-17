package xyz.foobar.entity;

public abstract class Node {
	
	private String value;
	
	private NodeStatus status;
	
	public Node() {	}
	
	public Node(String value, NodeStatus status) {
		this.value = value;
		this.status = status;
	}
	
	public String getValue() {
		return value;
	}
	public NodeStatus getStatus() {
		return status;
	}
//	public void setValue(String value) {
//		this.value = value;
//	}
//	public void setStatus(NodeStatus status) {
//		this.status = status;
//	}
}
