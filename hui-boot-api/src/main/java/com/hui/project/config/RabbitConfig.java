package com.hui.project.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"test"})
public class RabbitConfig {
	@Bean
	public Queue Queue() {
		Queue queue = new Queue("hello");
		return queue;
	}
}
