package com.locked.model;

public class lockedMe {
	
	private int id;
	private String name;
	
	public lockedMe() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "lockedMe [id=" + id + ", name=" + name + "]";
	}
	

}
