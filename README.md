# SpringBoot-APIClient-Config

This project contains 3-microservices under this main project:
  1) eureka-server : Used for service discovery. Microservices, both providers and consumers, can query the Eureka registry to locate and communicate with other services seamlessly.
  2) microservice-one: Contains Rest-Endpoints which is going to be used by other Microservices. In current test scenario, microservice-two will be the consumer.
  3) microservice-two: Contains Rest-Endpoints which is inaternall invking the rest-endpoint of microservice-one. Different API-CLients config are used to establish connection between both microservice one and two.

## OpenFeign-Client
Feign is a declarative web service client. To use Feign an interface OpenFeignClientConfig is created and annotated with @FeignClient.

## Rest-Template
RestTemplate is a powerful synchronous client for handling HTTP communication in Spring Boot applications. It internally uses an HTTP client library i.e. java.net.HttpURLConnection, simplifying the process of making RESTful requests to external services and APIs

## Web-Client
Spring WebFlux includes a client to perform HTTP requests with. WebClient has a functional, fluent API based on Reactor, see Reactive Libraries, which enables declarative composition of asynchronous logic without the need to deal with threads or concurrency. It is fully non-blocking, it supports streaming, and relies on the same codecs that are also used to encode and decode request and response content on the server side.
