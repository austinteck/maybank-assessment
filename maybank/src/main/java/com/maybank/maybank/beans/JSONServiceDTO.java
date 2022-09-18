package com.maybank.maybank.beans;

public class JSONServiceDTO {

	private Long id;
	private String name;
	private int age;
	
	public JSONServiceDTO(Long id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String toString() {
		 return "\n{ Name: " + name + ", Age: " + age + " }";
	}
	
	
}

