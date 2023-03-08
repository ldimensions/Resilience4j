package com.resilience4j.app2.app1.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
class AppAController {

	@Autowired
	private RestTemplate restTemplate;

	private static final String BASE_URL = "http://localhost:8081/";

	private static final String SERVICE_A = "serviceA";

	int count=1;

	@CircuitBreaker(name = SERVICE_A, fallbackMethod = "serviceAFallback")
	//@Retry(name = SERVICE_A)
//	@RateLimiter(name = SERVICE_A)
	@RequestMapping("/a")
	public String serviceA(@RequestParam String id) {

		String url = BASE_URL + "b";
		System.out.println(id+ "----Retry method called " + count++ + " times at " + new Date());
		return restTemplate.getForObject(
				url,
				String.class
		);

	}

	@RequestMapping("/a1")
	@CircuitBreaker(name = SERVICE_A, fallbackMethod = "serviceAFallback")
	public String serviceA1(@RequestParam String id) {

		return callService(id);
	}

	@CircuitBreaker(name = SERVICE_A, fallbackMethod = "serviceAFallback")
	public String callService(String id)  {
		String url = BASE_URL + "b";
		return restTemplate.getForObject(
				url,
				String.class
		);
	}

	public String serviceAFallback(String id, Exception e) {
		return id+" - This is a fallback method for Service A - "+ e;
	}
}
