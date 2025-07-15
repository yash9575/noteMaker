package com.example.notetaking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class NotetakingApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotetakingApplication.class, args);
	}

	@Bean
	public Function<String, String> echoFunction() {
		return input -> "Echo: " + input;
	}
}
