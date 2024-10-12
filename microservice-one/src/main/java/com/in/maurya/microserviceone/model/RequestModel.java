package com.in.maurya.microserviceone.model;

public class RequestModel {
	
	private String username;
	private String age;
	
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	@Override
	public String toString() {
		return "RequestModel [username=" + username + ", age=" + age + "]";
	}

	
}
