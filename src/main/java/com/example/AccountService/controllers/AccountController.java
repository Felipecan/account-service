package com.example.AccountService.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.AccountService.domain.Account;
import com.example.AccountService.services.AccountService;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {
	
	@Autowired
	private AccountService accountService;

	@PostMapping
	public ResponseEntity<Void> createAccount(@RequestBody Account acc) {
		
		acc = accountService.createAccount(acc);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(acc.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
//		return ResponseEntity.accepted().build();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getAccountById(@PathVariable Integer id) {
		
		Account acc = accountService.findAccount(id);		
		return ResponseEntity.ok().body(acc);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> updateAccount(@RequestBody Account acc, @PathVariable Integer id) {
		
		acc.setId(id);
		acc = accountService.updateAccount(acc);
		return ResponseEntity.noContent().build();
	}	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteAccountById(@PathVariable Integer id) {
		
		accountService.deleteAccount(id);		
		return ResponseEntity.noContent().build();
	}
}
