package com.in.maurya.microservicetwo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.in.maurya.microservicetwo.apiClient.OpenFeignClientConfig;
import com.in.maurya.microservicetwo.model.RequestModel;

@RestController()
public class GreetController {
	
	@Autowired
	private OpenFeignClientConfig openFeignClientConfig;
	
	@GetMapping("/microservicetwo/hi")
	public ResponseEntity<String> greet() {
		//Call Microservice-one and return its response
		String microServiceOneResponse = openFeignClientConfig.hi();
		return ResponseEntity.ok("Received response from Microservice-one, {"+microServiceOneResponse+"}");
	}
	
	@GetMapping("/microservicetwo/name/{username}")
	public ResponseEntity<String> greetByUsername(@PathVariable String username) {
		//Call Microservice-one and return its response
		String microServiceOneResponse = openFeignClientConfig.hiByUsername(username);
		return ResponseEntity.ok("Received response from Microservice-one, {"+microServiceOneResponse+"}");
	}
	
	@PostMapping("/microservicetwo/createUser")
	public ResponseEntity<String> createUser(@RequestBody RequestModel requestModel) {
		//Call Microservice-one and return its response
		return ResponseEntity.ok(openFeignClientConfig.createUser(requestModel));
	}


}
