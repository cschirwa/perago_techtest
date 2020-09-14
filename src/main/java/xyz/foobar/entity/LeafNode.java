package xyz.foobar.entity;

public class LeafNode extends Node{

	private Object preValue;
	
	private Object postValue;
	
	public LeafNode() {}
	
	public LeafNode(Object key, String name, Object preValue, Object postValue) {
		super(key, name, NodeStatus.UPDATED);
		this.preValue = preValue;
		this.postValue = postValue;
	}
	
	public Object getPreValue() {
		return preValue;
	}
	
	public Object getPostValue() {
		return postValue;
	}
	
}
