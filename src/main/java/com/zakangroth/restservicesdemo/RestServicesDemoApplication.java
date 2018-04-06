package com.zakangroth.restservicesdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class RestServicesDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestServicesDemoApplication.class, args);
	}
}
