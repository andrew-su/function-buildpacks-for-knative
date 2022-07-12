package com.example.demo;

import java.net.URI;
import java.time.Duration;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class DemoApplication {
	Logger LOGGER = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public WebClient makeClient() {
		return WebClient.create();
	}

	@Bean
	public Consumer<Message<ImageMsg>> func(WebClient client) {
		return m -> {
			URI imageUri = URI.create(m.getPayload().getImageUrl());
				client.get()
				.uri(imageUri)
				.retrieve()
				.bodyToMono(String.class)
				.delayElement(Duration.ofSeconds(20))
				.subscribe(s -> LOGGER.info("RECEIVED: {}", s));
		};
	}
}
