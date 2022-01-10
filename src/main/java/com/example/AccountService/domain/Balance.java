package com.example.AccountService.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Balance implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	private Integer accountId;
	private BigDecimal balanceValue;
	
	public Balance(Integer accountId) {
		this.accountId = accountId;
	}
}
