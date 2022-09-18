package com.maybank.maybank.beans;

import javax.persistence.*;

/**
 * 
 * @author Austin Teck
 *
 */
@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "age", nullable = false)
	private int age;
	
	public Employee() {}
	
	public Employee(long id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
