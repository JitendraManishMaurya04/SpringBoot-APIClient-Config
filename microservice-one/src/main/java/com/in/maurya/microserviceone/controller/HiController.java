package com.in.maurya.microserviceone.controller;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.in.maurya.microserviceone.model.RequestModel;

import lombok.RequiredArgsConstructor;

@RestController()
@RequiredArgsConstructor
public class HiController {
	
	private final Environment env;
	
	@GetMapping("microserviceone/hi")
	public String hi() {
		return "Hi from Micorservice-one, port: "+ env.getProperty("local.server.port");
	}
	
	@GetMapping("microserviceone/name/{username}")
	public String hiByUsername(@PathVariable String username) {
		return "Hi from "+username +" of Micorservice-one, port: "+ env.getProperty("local.server.port");
	}
	
	@PostMapping("microserviceone/createUser")
	public String createUser(@RequestBody RequestModel requestModel) {
		System.out.println(requestModel);
		return "Resource Created!!!";
	}



}
