package com.rhymthwave.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Account;
import com.rhymthwave.entity.TypeEnum.EROLE;


@Repository
public interface AccountDAO extends JpaRepository<Account, String>{

	@Query("SELECT au.account FROM Author au WHERE au.role.role = :role")
	Page<Account> findAllAccountRole(Pageable pageable, @Param("role") EROLE role);
	
	@Query("SELECT au.account FROM Author au WHERE au.role.role = :role")
	List<Account> findAllAccountRole(@Param("role") EROLE role);
	
	Account findByEmail(String email);

	Account findByVerificationCode(String verificationCode);
	
}
