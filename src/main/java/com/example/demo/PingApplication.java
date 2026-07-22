package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.resilience.annotation.EnableResilientMethods;


@SpringBootApplication
@EnableResilientMethods
public class PingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PingApplication.class, args);
	}
}