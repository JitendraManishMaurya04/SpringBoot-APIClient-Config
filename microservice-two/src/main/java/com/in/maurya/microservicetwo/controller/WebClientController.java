package com.in.maurya.microservicetwo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.in.maurya.microservicetwo.exception.PostServiceException;
import com.in.maurya.microservicetwo.model.Post;
import com.in.maurya.microservicetwo.model.RequestModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class WebClientController {
	
	@Autowired
	@Qualifier("getWebClienteUsingBuilder2")
	private WebClient webClient;
	
	@GetMapping("/microservicetwo/webClient2/hi")
	public Flux<String> findAll(){
		Flux<String> response =  webClient.get()
				.uri("/hi")
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, clientResponse -> handleClientError(clientResponse))
				.onStatus(HttpStatus::is5xxServerError, clientResponse -> handleServerError(clientResponse))
				.bodyToFlux(String.class)
				.onErrorResume(Exception.class, e -> Flux.empty()); // Return an empty collection on error
		//Logging the MS1 response  
		response.subscribe(a -> {
			System.out.println("Reponse from MS1: "+ a);
		});
		
		return response;
	}
	
	@GetMapping("/microservicetwo/webClient2/user/{username}/{age}")
	public Mono<RequestModel> findById(@PathVariable String username, @PathVariable String age){
		Mono<RequestModel> response =  webClient.get()
				.uri("/user/{username}/{age}", username, age)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, clientResponse -> handleClientError(clientResponse))
				.onStatus(HttpStatus::is5xxServerError, clientResponse -> handleServerError(clientResponse))
				.bodyToMono(RequestModel.class);
		//Logging the MS1 response 
		response.subscribe(a -> {
			System.out.println("Reponse from MS1: "+ a);
		});
		return response;
	}
	
	@PostMapping("/microservicetwo/webClient2/createUser")
	public Mono<String> createAPost(@RequestBody RequestModel requestModel){
		Mono<String> singlePost =  webClient.post()
				.uri("/createUser")
				.bodyValue(requestModel)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, response -> {
			        //logError("Client error occurred");
			        return Mono.error(new WebClientResponseException
			          (response.statusCode().value(), "Bad Request Exception", null, null, null));
			      })
			      .onStatus(HttpStatus::is5xxServerError, response -> {
			        //logError("Server error occurred");
			        return Mono.error(new WebClientResponseException
			          (response.statusCode().value(), "Server Error Exception", null, null, null));
			      })
				.bodyToMono(String.class);
		//Logging the post 
		singlePost.subscribe(System.out::println);
		return singlePost;
	}
	
	
	private Mono<? extends Throwable> handleClientError(ClientResponse clientResponse) {
		  // Handle client errors (e.g., 404 Not Found) here
		return Mono.error(new PostServiceException("Data not found"));
	}

	private Mono<? extends Throwable> handleServerError(ClientResponse clientResponse) {
		  // Handle server errors (e.g., 500 Internal Server Error) here
		return Mono.error(new RuntimeException("Server error"));
	}
	
}
