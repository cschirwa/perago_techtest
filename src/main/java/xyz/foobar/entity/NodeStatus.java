package xyz.foobar.entity;

public enum NodeStatus {
	CREATED("Create"), 
	UPDATED("Update"),
	DELETED("Delete");
	
	private NodeStatus(String description) {
		this.description = description;
	}
	private String description;
	
	public String getDescription() {
		return description;
	}
}
