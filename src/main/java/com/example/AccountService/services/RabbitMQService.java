package com.example.AccountService.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AccountService.domain.Account;
import com.example.AccountService.utils.RabbitMQConstants;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RabbitMQService {

	@Autowired
	private final RabbitTemplate rabbitMQ;
	
	public void insertIntoCreateBalanceQueue(Account acc) {
		
		rabbitMQ.convertAndSend(RabbitMQConstants.EXCHANGE, 
				RabbitMQConstants.QUEUE_TOPIC_KEY_CREATE_BALANCE, 
				acc);
	}	
}
