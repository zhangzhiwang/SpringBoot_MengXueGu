package com.asiainfo.entity;

public class User2 implements Cloneable {
	private int id;
	private String name;

	public User2(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public User2() {
		super();
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
		return "User [id=" + id + ", name=" + name + "]";
	}
	
	public User2 clone() {
		try {
			return (User2) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
