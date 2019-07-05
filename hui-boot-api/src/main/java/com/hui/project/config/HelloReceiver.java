package com.hui.project.config;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
@Profile({"test"})
public class HelloReceiver {
	@RabbitHandler
	public void process(String hello) {
		System.out.println("Receiver【1】:" + hello);
	}
}