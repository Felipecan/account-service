package com.example.AccountService.asyc;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.AccountService.domain.Account;
import com.example.AccountService.domain.enums.StatusAccount;
import com.example.AccountService.services.AccountService;
import com.example.AccountService.services.BalanceService;
import com.example.AccountService.services.RabbitMQService;
import com.example.AccountService.utils.RabbitMQConstants;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class RabbitMQListener {
	
	@Autowired
	private BalanceService balanceService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private RabbitMQService rabbitService;

	@RabbitListener(queues = RabbitMQConstants.QUEUE_TOPIC_CREATE_BALANCE)	
	public void createBalance(@Payload Account acc) {
		
		try {
			
			if (!balanceService.createBalanceForAccount(acc) || accountService.updateStatusAccount(acc.getId(), StatusAccount.ACTIVE) < 0) {

				log.warn("Cannot creating balance or activate account... Retring");
				rabbitService.insertIntoCreateBalanceQueue(acc);
				return;
			}
			
		} catch (Exception e) {
			
			log.error("Error on creating balance or activate account... Retring", e);
			rabbitService.insertIntoCreateBalanceQueue(acc);
		}
	}
}
