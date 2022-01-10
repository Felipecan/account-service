package com.example.AccountService.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.AccountService.controllers.exceptions.FieldMessage;
import com.example.AccountService.domain.Account;
import com.example.AccountService.dto.AccountDTO;
import com.example.AccountService.repositories.AccountRepository;

public class AccountInsertValidator implements ConstraintValidator<AccountInsert, AccountDTO> {
	
	@Autowired
	private AccountRepository repository;
	
	@Override
	public void initialize(AccountInsert ai) { }

	@Override
	public boolean isValid(AccountDTO acc, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();

		Account temp = repository.findByIdentificationDocument(acc.getIdentificationDocument());
		
		if (temp != null) {
			
			list.add(new FieldMessage("identificationDocument", "identificationDocument " + acc.getIdentificationDocument() +  " already exists"));
		}
		
		for (FieldMessage e : list) {
			
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		
		return list.isEmpty();
	}

}
