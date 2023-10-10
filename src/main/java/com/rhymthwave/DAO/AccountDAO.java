package com.rhymthwave.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Account;

@Repository
public interface AccountDAO extends JpaRepository<Account, String>{

	Account findByEmail(String email);
}
