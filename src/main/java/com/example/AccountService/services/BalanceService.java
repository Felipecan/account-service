package com.example.AccountService.services;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.example.AccountService.config.Properties;
import com.example.AccountService.domain.Account;
import com.example.AccountService.domain.Balance;
import com.example.AccountService.services.exceptions.ServiceComunicationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BalanceService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Properties properties;
	
	private String balanceServiceHost;
	
	@PostConstruct
	public void init() {
		
		this.balanceServiceHost = properties.getApp().getServices().get(this.getClass().getSimpleName()).getHost();
	}
		
	public boolean createBalanceForAccount(Account acc) {

		Balance balance = new Balance(acc.getId());

		try {

			ResponseEntity<Balance> response = restTemplate.postForEntity(
					balanceServiceHost + "/balances", 
					balance, 
					Balance.class);			

			if (response.getStatusCode().is2xxSuccessful())
				return response.getStatusCode().is2xxSuccessful();
			else
				throw new ServiceComunicationException("Balance cannot be posted");

		} catch (RestClientResponseException e) {
			
			String msg = getErroMessageFromBalanceService(e.getResponseBodyAsString());
			throw new ServiceComunicationException("Cannot be got balance service | " + msg);
			
		} catch (Exception e) {
			
			throw new ServiceComunicationException("Cannot be got balance service");
			
		}	
	}
	
	public Balance getBalanceFromAccount(Integer accountId) {		
		
		try {
			
			ResponseEntity<Balance> response = restTemplate
					.getForEntity(this.balanceServiceHost + "/balances/{accountId}", Balance.class, accountId);

			if (response.getStatusCode().is2xxSuccessful())
				return response.getBody();
			else
				throw new ServiceComunicationException("Balance cannot be got");
			
		} catch (RestClientResponseException e) {
						
			String msg = getErroMessageFromBalanceService(e.getResponseBodyAsString());
			throw new ServiceComunicationException("Cannot be got balance service | " + msg);
			
		} catch (Exception e) {
			
			throw new ServiceComunicationException("Cannot be got balance service");
			
		}	
	}
	
	private String getErroMessageFromBalanceService(String s) {
		
		String msg = "";
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			
			Map<String, String> map = mapper.readValue(s, Map.class);
			msg = map.get("message");
			
		} catch (JsonProcessingException e1) {
			
			log.error("Error on parsing json message");
		}
		
		return msg;
	}
}
