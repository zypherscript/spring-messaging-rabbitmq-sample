package com.example.messagingrabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.RabbitMQContainer;

@TestConfiguration(proxyBeanMethods = false)
public class TestMessagingRabbitmqApplication {

	@Bean
	@ServiceConnection
	RabbitMQContainer rabbitContainer() {
		return new RabbitMQContainer("rabbitmq:latest");
	}

	public static void main(String[] args) {
		SpringApplication.from(MessagingRabbitmqApplication::main).with(TestMessagingRabbitmqApplication.class).run(args);
	}

}
