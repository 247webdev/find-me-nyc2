package com.example.resultsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ResultsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResultsApiApplication.class, args);
	}
}
