package xyz.foobar.entity;

public class Node {
	
	private Object key;
	
	private String name;
	
	private NodeStatus status;
	
	public Node() {	}
	
	public Node(Object key, String name, NodeStatus status) {
		this.key = key;
		this.name = name;
		this.status = status;
	}
	
	public Object getKey() {
		return key;
	}
	public String getName() {
		return name;
	}
}
