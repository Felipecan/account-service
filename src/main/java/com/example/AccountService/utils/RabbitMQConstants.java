package com.example.AccountService.utils;

import org.springframework.stereotype.Component;

@Component
public class RabbitMQConstants {

	public static final String EXCHANGE = "direct-exchange";

	public static final String QUEUE_TOPIC_CREATE_BALANCE= "account.exchange.topic.queue.create.balance";
	public static final String QUEUE_TOPIC_KEY_CREATE_BALANCE= "account.exchange.topic.queue.create.balance.*";
}
