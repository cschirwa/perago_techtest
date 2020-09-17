package xyz.foobar.entity;

import java.io.Serializable;

/**
 * The object representing a diff.
 * Implement this class as you see fit. 
 *
 */
public class Diff<T extends Serializable> {
	
	private InnerNode root;
	
	public InnerNode getRoot() {
		return root;
	}
	
	public Diff() {
	}
	
	public Diff(InnerNode root) {
		this.root = root;
	}
	
	public Diff(Diff<T> diff) {
		this.root = diff.root;
	}
		
	public boolean isEmpty() {
		return root.isEmpty();
	}
}
