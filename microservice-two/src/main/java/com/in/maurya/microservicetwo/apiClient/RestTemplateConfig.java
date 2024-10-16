package com.in.maurya.microservicetwo.apiClient;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RootUriTemplateHandler;
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
	public RestTemplate getRestTemplateUsingBuilder() {
		return new RestTemplateBuilder()
				.uriTemplateHandler(new RootUriTemplateHandler(getBaseUrl()+"microserviceone"))
				.setConnectTimeout(Duration.ofMillis(5000))
				.build();
	}
	
	//Used for getting base url of other microservice based on hostname/application-name
	public String getBaseUrl() {
	    InstanceInfo instance = eurekaClient.getNextServerFromEureka("microservice-one", false);
	    System.out.println(instance.getHomePageUrl());
	    return instance.getHomePageUrl();
	}

}
