package com.in.maurya.microservicetwo.apiClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.in.maurya.microservicetwo.model.RequestModel;

@FeignClient("microservice-one")
public interface OpenFeignClientConfig {
	
	@GetMapping("/microserviceone/hi")
	public String hi();
	
	@GetMapping("/microserviceone/name/{username}")
	public String hiByUsername(@PathVariable String username);
		
	@PostMapping("microserviceone/createUser")
	public String createUser(@RequestBody RequestModel requestModel);
}
