package com.in.maurya.microservicetwo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.in.maurya.microservicetwo.apiClient.RestTemplateConfig;
import com.in.maurya.microservicetwo.model.RequestModel;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RestTemplateController {
	
	private final RestTemplate restTemplate;
	
	@Autowired 
	private RestTemplateConfig restTemplateConfig;
	
	@GetMapping("/microservicetwo/restTemplate/exchange/hi")
	public ResponseEntity<String> greet() {
		//Call Microservice-one and return its response
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Object> requestEntity =new HttpEntity<>(headers);
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(restTemplateConfig.getBaseUrl() + "microserviceone/hi",
				HttpMethod.GET,
				requestEntity,
				String.class);
		
		return responseEntity;
	}
	
	@GetMapping("/microservicetwo/restTemplate/byEntity/hi")
	public ResponseEntity<String> greet2() {
		//Call Microservice-one and return its response
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(restTemplateConfig.getBaseUrl() + "microserviceone/hi", String.class);
		return responseEntity;
	}
	
	@GetMapping("/microservicetwo/restTemplate/byObject/hi")
	public String greet3() {
		//Call Microservice-one and return its response
		String response = restTemplate.getForObject(restTemplateConfig.getBaseUrl() + "microserviceone/hi", String.class);
		return response;
	}
	
	@GetMapping("/microservicetwo/restTemplate/exchange/user/{username}/{age}")
	public ResponseEntity<RequestModel> getUserByNameAndAge(@PathVariable String username, @PathVariable String age) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Object> requestEntity =new HttpEntity<>(headers);
		
		ResponseEntity<RequestModel> responseEntity = restTemplate.exchange(restTemplateConfig.getBaseUrl()  + "microserviceone/user/"+username+"/"+age,
				HttpMethod.GET,
				requestEntity,
				RequestModel.class);
		
		return responseEntity;
	}
	
	@GetMapping("/microservicetwo/restTemplate/exchange/users")
	public ResponseEntity<List> getAllUsers() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Object> requestEntity =new HttpEntity<>(headers);
		
		ResponseEntity<List> responseEntity = restTemplate.exchange(restTemplateConfig.getBaseUrl()  + "microserviceone/users",
				HttpMethod.GET,
				requestEntity,
				List.class);
		
		return responseEntity;
	}
	
	@GetMapping("/microservicetwo/restTemplate/byEntity/users")
	public ResponseEntity<List> getAllUsers2() {
		ResponseEntity<List> responseEntity = restTemplate.getForEntity(restTemplateConfig.getBaseUrl()  + "microserviceone/users",List.class);
		return responseEntity;
	}
	
	@GetMapping("/microservicetwo/restTemplate/byObject/users")
	public List getAllUsers3() {
		List response = restTemplate.getForObject(restTemplateConfig.getBaseUrl()  + "microserviceone/users",List.class);
		return response;
	}

	@PostMapping("/microservicetwo/restTemplate/exchange/createUser")
	public ResponseEntity<String> createUser(@RequestBody RequestModel requestModel) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Object> requestEntity =new HttpEntity<>(requestModel,headers);
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(restTemplateConfig.getBaseUrl()  + "microserviceone/createUser",
				HttpMethod.POST,
				requestEntity,
				String.class);
		
		return responseEntity;
	}
	
	@PostMapping("/microservicetwo/restTemplate/byEntity/createUser")
	public ResponseEntity<String> createUser2(@RequestBody RequestModel requestModel) {
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(restTemplateConfig.getBaseUrl()  + "microserviceone/createUser", requestModel, String.class);
		return responseEntity;
	}
	
	@PostMapping("/microservicetwo/restTemplate/byObject/createUser")
	public String createUser3(@RequestBody RequestModel requestModel) {
		String response = restTemplate.postForObject(restTemplateConfig.getBaseUrl()  + "microserviceone/createUser", requestModel, String.class);
		return response;
	}
	
	
	@PostMapping("/microservicetwo/restTemplate/postForLocation/createUser")
	public URI createUser4(@RequestBody RequestModel requestModel) {
		URI uri = restTemplate.postForLocation(restTemplateConfig.getBaseUrl()  + "microserviceone/createUser", requestModel, String.class);
		return uri;
	}


}
