package xyz.foobar.entity;

public class LeafNode extends Node{

	private Object original;
	
	private Object modified;
	
	public LeafNode() {}
	
	public LeafNode(Object original, Object modified) {
		this.original = original;
		this.modified = modified;
	}
	
	public Object getOriginal() {
		return original;
	}
	public Object getModified() {
		return modified;
	}
	
}
