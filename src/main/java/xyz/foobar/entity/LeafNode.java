package xyz.foobar.entity;

import java.lang.reflect.Field;

public class LeafNode extends Node{

	private Object original;
	
	private Object modified;
	
	private Field field;
	
	public LeafNode() {}
	
	public LeafNode(Object original, Object modified, Field field) {
		this.original = original;
		this.modified = modified;
		this.field = field;
	}
	
	public Object getOriginal() {
		return original;
	}
	public Object getModified() {
		return modified;
	}
	public Field getField() {
		return field;
	}
	
}
