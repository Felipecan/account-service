package com.example.AccountService.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.AccountService.domain.Account;
import com.example.AccountService.domain.Balance;
import com.example.AccountService.domain.enums.StatusAccount;
import com.example.AccountService.dto.AccountDetailsDTO;
import com.example.AccountService.repositories.AccountRepository;
import com.example.AccountService.services.exceptions.DataIntegrityException;
import com.example.AccountService.services.exceptions.ObjectNotFoundException;

@Service
public class AccountService {

	@Autowired
	private AccountRepository repository;
	
	@Autowired
	RabbitMQService rabbitService;
	
	@Autowired
	BalanceService balanceService;
	
	public Account findAccount(Integer id) {
		
		Optional<Account> acc = repository.findById(id);
		return acc.orElseThrow(() -> new ObjectNotFoundException(
				"Account with ID " + id + " not found!"));
	}
	
	public AccountDetailsDTO getAccountWithDetails(Integer id) {
		
		Account acc = findAccount(id);
		Balance balance = balanceService.getBalanceFromAccount(id);
		
		AccountDetailsDTO accDTO = new AccountDetailsDTO(acc, balance);
		
		return accDTO;
	}
	
	public Account createAccount(Account acc) {
		
		acc.setId(null);
		acc.setStatus(StatusAccount.INACTIVE.getStatus());
		
		Account accSaved = repository.save(acc);
		
		rabbitService.insertIntoCreateBalanceQueue(accSaved);
		
		return accSaved;
	}
	
	public Account updateAccount (Account acc) {
		
		Account actualAcc = findAccount(acc.getId());
		updateAccountBeforeSave(actualAcc, acc);
		
		return repository.save(actualAcc);
	}
	
	public void deleteAccount(Integer id) {
		
		findAccount(id);
		repository.deleteById(id);
	}
	
	public int updateStatusAccount(Integer id, StatusAccount status) {
		
		findAccount(id);
		return repository.updateStatusAccount(status.getStatus(), id);
	}
	
	private void updateAccountBeforeSave(Account newAcc, Account acc) {
		
		newAcc.setName(acc.getName() != null ? acc.getName() : newAcc.getName());
		
		StatusAccount status = StatusAccount.findStatus(acc.getStatus());
		newAcc.setStatus(status != null ? status.getStatus() : newAcc.getStatus());
	}
}
