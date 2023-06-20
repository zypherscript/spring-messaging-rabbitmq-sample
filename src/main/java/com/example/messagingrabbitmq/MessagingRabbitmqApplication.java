package com.example.messagingrabbitmq;

import java.util.concurrent.TimeUnit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class MessagingRabbitmqApplication {

  public static void main(String[] args) {
    SpringApplication.run(MessagingRabbitmqApplication.class, args);
  }

  @Bean
  CommandLineRunner runner(RabbitTemplate rabbitTemplate, Receiver receiver) {
    return args -> {
      System.out.println("Sending message...");
      rabbitTemplate.convertAndSend(AmqpConfig.topicExchangeName, "foo.bar.baz",
          "Hello from RabbitMQ!");
      receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    };
  }
}
