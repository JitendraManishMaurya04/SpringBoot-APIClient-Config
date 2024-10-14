package com.in.maurya.microservicetwo.apiClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {
	
	private final EurekaClient eurekaClient;
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	//Used for getting base url of other microservice based on hostname/application-name
	@Bean
	public String getBaseUrl() {
	    InstanceInfo instance = eurekaClient.getNextServerFromEureka("microservice-one", false);
	    System.out.println(instance.getHomePageUrl());
	    return instance.getHomePageUrl();
	}

}
