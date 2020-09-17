package xyz.foobar.entity;

public abstract class Node {
	
	private String name;
	
	private String value;
	
	private NodeStatus status;
	
	public Node() {	}
	
	public Node(String name, String value, NodeStatus status) {
		this.name = name;
		this.value = value;
		this.status = status;
	}
	
	public String getName() {
		return name;
	}
	public String getValue() {
		return value;
	}
	public NodeStatus getStatus() {
		return status;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void setStatus(NodeStatus status) {
		this.status = status;
	}
}
