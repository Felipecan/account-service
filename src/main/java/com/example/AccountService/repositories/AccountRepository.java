package com.example.AccountService.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.AccountService.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	@Transactional(rollbackOn = Exception.class)
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query(value = ""
	             + " UPDATE accounts "
			     + " SET "
			     + "   status = ? "
			     + " WHERE "
			     + "   id = ? " , 
			nativeQuery = true)
	public int updateStatusAccount(Integer status, Integer id);
	
	public Account findByIdentificationDocument(String identificationDocument);
}

