package com.example.AccountService.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.AccountService.domain.Account;
import com.example.AccountService.repositories.AccountRepository;
import com.example.AccountService.services.exceptions.DataIntegrityException;
import com.example.AccountService.services.exceptions.ObjectNotFoundException;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	public Account findAccount(Integer id) {
		
		Optional<Account> acc = accountRepository.findById(id);
		return acc.orElseThrow(() -> new ObjectNotFoundException(
				"Account with ID " + id + " not found!"));
	}
	
	public Account createAccount(Account acc) {
		
		acc.setId(null);
		return accountRepository.save(acc);
	}
	
	public Account updateAccount (Account acc) {
		
		findAccount(acc.getId());
		return accountRepository.save(acc);
	}
	
	public void deleteAccount(Integer id) {
		
		findAccount(id);
		
		try {
			accountRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Account has balance");
		}
	}
}
