package com.in.maurya.microservicetwo.apiClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import reactor.netty.http.client.HttpClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {
	
	private final RestTemplateConfig restTemplateConfig;
	

	  HttpClient httpClient = HttpClient.create()
	    .tcpConfiguration(client ->
	        client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
	        .doOnConnected(conn -> conn
	            .addHandlerLast(new ReadTimeoutHandler(10))
	            .addHandlerLast(new WriteTimeoutHandler(10))));
	  
	 ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);     
	
	@Bean
	@Primary
	public WebClient getWebClienteUsingBuilder() {
		WebClient client = WebClient.builder()
				  .baseUrl("https://jsonplaceholder.typicode.com")
				  .clientConnector(connector)
				  .build();
		return client;
	}
	
	
	@Bean
	public WebClient getWebClienteUsingBuilder2() {
		WebClient client = WebClient.builder()
				  .baseUrl(restTemplateConfig.getBaseUrl()+"microserviceone")
				  .clientConnector(connector)
				  .build();
		return client;
	}

}
