package com.in.maurya.microservicetwo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.in.maurya.microservicetwo.model.RequestModel;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RestTemplateGreetController {
	
	private final RestTemplate restTemplate;
	@Qualifier("getBaseUrl")
	private final String getBaseUrl;
	
	
	@GetMapping("/microservicetwo/restTemplate/hi")
	public ResponseEntity<String> greet() {
		//Call Microservice-one and return its response
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Object> requestEntity =new HttpEntity<>(headers);
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(getBaseUrl + "microserviceone/hi",
				HttpMethod.GET,
				requestEntity,
				String.class);
		
		return responseEntity;
	}
	
	@GetMapping("/microservicetwo/restTemplate/user/{username}/{age}")
	public ResponseEntity<RequestModel> getUserByNameAndAge(@PathVariable String username, @PathVariable String age) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Object> requestEntity =new HttpEntity<>(headers);
		
		ResponseEntity<RequestModel> responseEntity = restTemplate.exchange(getBaseUrl + "microserviceone/user/"+username+"/"+age,
				HttpMethod.GET,
				requestEntity,
				RequestModel.class);
		
		return responseEntity;
	}
	
	@GetMapping("/microservicetwo/restTemplate/users")
	public ResponseEntity<List> getAllUsers() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Object> requestEntity =new HttpEntity<>(headers);
		
		ResponseEntity<List> responseEntity = restTemplate.exchange(getBaseUrl + "microserviceone/users",
				HttpMethod.GET,
				requestEntity,
				List.class);
		
		return responseEntity;
	}

	@PostMapping("/microservicetwo/restTemplate/createUser")
	public ResponseEntity<String> createUser(@RequestBody RequestModel requestModel) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Object> requestEntity =new HttpEntity<>(requestModel,headers);
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(getBaseUrl + "microserviceone/createUser",
				HttpMethod.POST,
				requestEntity,
				String.class);
		
		return responseEntity;
	}
	


}
