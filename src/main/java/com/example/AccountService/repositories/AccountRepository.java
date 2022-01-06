package com.example.AccountService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AccountService.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

}

