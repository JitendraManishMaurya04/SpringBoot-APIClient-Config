package com.in.maurya.microservicetwo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.in.maurya.microservicetwo.apiClient.OpenFeignClientConfig;
import com.in.maurya.microservicetwo.model.RequestModel;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OpenFeignClientGreetController {
	
	private final OpenFeignClientConfig openFeignClientConfig;
	
	@GetMapping("/microservicetwo/openfeignclient/hi")
	public ResponseEntity<String> greet() {
		//Call Microservice-one and return its response
		String microServiceOneResponse = openFeignClientConfig.hi();
		return ResponseEntity.ok("Received response from Microservice-one, {"+microServiceOneResponse+"}");
	}
	
	@GetMapping("/microservicetwo/openfeignclient/name/{username}")
	public ResponseEntity<String> greetByUsername(@PathVariable String username) {
		//Call Microservice-one and return its response
		String microServiceOneResponse = openFeignClientConfig.hiByUsername(username);
		return ResponseEntity.ok("Received response from Microservice-one, {"+microServiceOneResponse+"}");
	}
	
	@PostMapping("/microservicetwo/openfeignclient/createUser")
	public ResponseEntity<String> createUser(@RequestBody RequestModel requestModel) {
		//Call Microservice-one and return its response
		return ResponseEntity.ok(openFeignClientConfig.createUser(requestModel));
	}
	
	@GetMapping("/microservicetwo/openfeignclient/user/{username}/{age}")
	public ResponseEntity<RequestModel> getDetailsByUsernameAndAge(@PathVariable String username, @PathVariable String age) {
		//Call Microservice-one and return its response
		RequestModel requestModel = openFeignClientConfig.getUserByNameAndAge(username,age);
		return ResponseEntity.ok(requestModel);
	}
	
	@GetMapping("/microservicetwo/openfeignclient/users")
	public ResponseEntity<List<RequestModel>> greetAllByUsers() {
		//Call Microservice-one and return its response
		List<RequestModel> requestModelList = openFeignClientConfig.getUserList();
		return ResponseEntity.ok(requestModelList);
	}


}
