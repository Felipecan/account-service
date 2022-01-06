package com.example.AccountService.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.example.AccountService.domain.Account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountDTO implements Serializable {

	private String name;
	private String identificationDocument;
	private Integer status;
	
	public AccountDTO(Account acc) {
		
		this.name = acc.getName();
		this.identificationDocument = acc.getIdentificationDocument();
		this.status = acc.getStatus();
	}
}
