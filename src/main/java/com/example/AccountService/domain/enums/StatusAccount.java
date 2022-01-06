package com.example.AccountService.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusAccount {

	ACTIVE(0),
	INACTIVE(1),
	;
	
	private int status;
}
