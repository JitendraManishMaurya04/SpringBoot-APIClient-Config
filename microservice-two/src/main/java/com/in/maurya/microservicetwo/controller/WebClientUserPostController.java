package com.in.maurya.microservicetwo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.in.maurya.microservicetwo.exception.PostServiceException;
import com.in.maurya.microservicetwo.model.Post;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class WebClientUserPostController {
	
	@Autowired
	@Qualifier("getWebClienteUsingBuilder")
	private WebClient webClient;
	
	@GetMapping("/microservicetwo/webClient/post")
	public Flux<Post> findAll(){
		Flux<Post> posts =  webClient.get()
				.uri("/posts")
				.retrieve()
				.onStatus(httpStatus -> !httpStatus.is2xxSuccessful(),
			              clientResponse -> handleErrorResponse(clientResponse.statusCode()))
				.bodyToFlux(Post.class)
				.onErrorResume(Exception.class, e -> Flux.empty()); // Return an empty collection on error
		//Logging the post 
		posts.subscribe(post -> {
			//Process each post
			System.out.println("Post: "+ post);
		});
		
		return posts;
	}
	
	@GetMapping("/microservicetwo/webClient/post/{postId}")
	public Mono<Post> findById(@PathVariable Integer postId){
		Mono<Post> post =  webClient.get()
				.uri("/posts/{postId}", postId)
				.retrieve()
				.onStatus(httpStatus -> !httpStatus.is2xxSuccessful(),
	                    clientResponse -> handleErrorResponse(clientResponse.statusCode()))
				.bodyToMono(Post.class);
		//Logging the post 
		post.subscribe(System.out::println);
		return post;
	}
	
	@PostMapping("/microservicetwo/webClient/post/create")
	public Mono<Post> createAPost(@RequestBody Post post){
		Mono<Post> singlePost =  webClient.post()
				.uri("/posts")
				.bodyValue(post)
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
				.bodyToMono(Post.class);
		//Logging the post 
		singlePost.subscribe(System.out::println);
		return singlePost;
	}
	
	@PutMapping("/microservicetwo/webClient/post/update/{postId}")
	public Mono<Post> updateAPost(@PathVariable Integer postId, @RequestBody Post post){
		Mono<Post> singlePost =  webClient.put()
				.uri("/posts/{postId}", postId)
				.bodyValue(post)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, clientResponse -> handleClientError(clientResponse))
				.onStatus(HttpStatus::is5xxServerError, clientResponse -> handleServerError(clientResponse))
				.bodyToMono(Post.class);
		//Logging the post 
		singlePost.subscribe(System.out::println);
		return singlePost;
	}
	
	@DeleteMapping("/microservicetwo/webClient/post/delete/{postId}")
	public Mono<Void> deleteAPost(@PathVariable Integer postId){
		Mono<Void> emptyResp =  webClient.delete()
				.uri("/posts/{postId}", postId)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, clientResponse -> handleClientError(clientResponse))
				.onStatus(HttpStatus::is5xxServerError, clientResponse -> handleServerError(clientResponse))
				.bodyToMono(Void.class);
		return emptyResp;
	}

	
	private Mono<? extends Throwable> handleErrorResponse(HttpStatus statusCode) {
	    // Handle non-success status codes here (e.g., logging or custom error handling)
	    return Mono.error(new PostServiceException("Failed to fetch employee. Status code: " + statusCode));
	  }
	
	private Mono<? extends Throwable> handleClientError(ClientResponse clientResponse) {
		  // Handle client errors (e.g., 404 Not Found) here
		return Mono.error(new PostServiceException("Post not found"));
	}

	private Mono<? extends Throwable> handleServerError(ClientResponse clientResponse) {
		  // Handle server errors (e.g., 500 Internal Server Error) here
		return Mono.error(new RuntimeException("Server error"));
	}
}
