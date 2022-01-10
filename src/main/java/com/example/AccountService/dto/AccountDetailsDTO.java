package com.example.AccountService.dto;

import java.math.BigDecimal;

import com.example.AccountService.domain.Account;
import com.example.AccountService.domain.Balance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailsDTO extends AccountDTO {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer status;
	private BigDecimal balance;
	
	public AccountDetailsDTO(Account acc, Balance balance) {
		
		super(acc);
		this.id = acc.getId();
		this.status = acc.getStatus();
		this.balance = balance.getBalanceValue();
	}
}
