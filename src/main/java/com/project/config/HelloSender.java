package com.project.config;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"test"})
public class HelloSender {
	@Autowired
	private AmqpTemplate amqpTemplate;

	public void send(Integer i) {
		String context = "hello: " + new Date();
		//System.out.println("Sender:" + context);
		this.amqpTemplate.convertAndSend("hello", i.toString());
	}

}
