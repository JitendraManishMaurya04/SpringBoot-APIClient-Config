package com.in.maurya.microservicetwo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {
	
	private Integer userId;
	private Integer Id;
	private String title;
	private String body;
	
	
	@Override
	public String toString() {
		return "Post [userId=" + userId + ", Id=" + Id + ", title=" + title + ", body=" + body + "]";
	}
	
	

}
