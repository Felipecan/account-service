package com.example.AccountService.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.example.AccountService.domain.Account;
import com.example.AccountService.services.validation.AccountInsert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AccountInsert
public class AccountDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Cannot be null")
	private String name;
	
	@NotNull(message = "Cannot be null")
	private String identificationDocument;
	
	public AccountDTO(Account acc) {
		
		this.name = acc.getName();
		this.identificationDocument = acc.getIdentificationDocument();
	}
}
