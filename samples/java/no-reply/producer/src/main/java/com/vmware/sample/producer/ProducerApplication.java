package com.vmware.sample.producer;

import java.net.URI;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.cloudevent.CloudEventMessageBuilder;
import org.springframework.cloud.function.cloudevent.CloudEventMessageUtils;
import org.springframework.cloud.function.web.util.HeaderUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ProducerApplication {

	@Value("${K_SINK}")
	private String sink;

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

	@Bean
	public WebClient makeClient() {
		return WebClient.builder()
			.defaultHeader("Content-Type", CloudEventMessageUtils.APPLICATION_CLOUDEVENTS_VALUE + "+json")
			.baseUrl(sink)
			.build();
	}

	@Bean
	public Consumer<URLInput> func(WebClient client) {
		return in -> {
			URI imageURL = URI.create(in.getUrl());
			Message<ImageMsg> m = CloudEventMessageBuilder
				.withData(new ImageMsg(imageURL))
				.setHeader(MessageHeaders.CONTENT_TYPE, CloudEventMessageUtils.APPLICATION_CLOUDEVENTS_VALUE + "+json")
				.build();
			client.post()
			.headers(headers -> headers.addAll(HeaderUtils.fromMessage(m.getHeaders())))
			.bodyValue(m.getPayload())
			.retrieve()
			.bodyToMono(Void.class)
			.subscribe();
		};
	}

	public class ImageMsg {
		private URI imageUrl;

		public ImageMsg() {}
		public ImageMsg(URI imageUrl) {
			this.imageUrl = imageUrl;
		}

		public URI getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(URI imageUrl) {
			this.imageUrl = imageUrl;
		}
	}
}
