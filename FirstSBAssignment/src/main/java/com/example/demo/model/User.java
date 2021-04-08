package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private Integer salary;
	
	@OneToMany(mappedBy = "user")
	private List<Posts> postList;
	
	public List<Posts> getPostList() {
		return postList;
	}

	public void setPostList(List<Posts> postList) {
		this.postList = postList;
	}

	public User(Integer id, String name, Integer salary) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
