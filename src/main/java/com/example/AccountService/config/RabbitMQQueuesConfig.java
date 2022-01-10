package com.example.AccountService.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.AccountService.utils.RabbitMQConstants;

import lombok.Getter;

@Getter
@EnableRabbit
@Configuration
public class RabbitMQQueuesConfig {

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(RabbitMQConstants.EXCHANGE);
    }
    
    @Bean
    public Queue queueExecuteOperation() {
    	
        return new Queue(RabbitMQConstants.QUEUE_TOPIC_CREATE_BALANCE, true, false, false);
    }

    @Bean
    public Binding bindingExecuteOperation() {
    	
        return BindingBuilder
        		.bind(queueExecuteOperation())
        		.to(exchange())
        		.with(RabbitMQConstants.QUEUE_TOPIC_KEY_CREATE_BALANCE);
    }
}
