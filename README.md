# SpringBoot-APIClient-Config

This project contains 3-microservices under this main project:
  1) eureka-server : Used for ervice discovery. Microservices, both providers and consumers, can query the Eureka registry to locate and communicate with other services seamlessly.
  2) microservice-one: Contains Rest-Endpoints which is going to be used by other Microservices. In current test scenario, microservice-two will be the consumer.
  3) microservice-two: Contains Rest-Endpoints which is inaternall invking the rest-endpoint of microservice-one. Different API-CLients config are used to establish connection between both microservice one and two.

## OpenFeign-Client
Feign is a declarative web service client. To use Feign an interface OpenFeignClientConfig is created and annotated with @FeignClient.
