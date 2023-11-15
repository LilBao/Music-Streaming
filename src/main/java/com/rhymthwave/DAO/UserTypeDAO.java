package com.rhymthwave.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.UserType;

@Repository
public interface UserTypeDAO extends JpaRepository<UserType, Long>{
	
	@Query("SELECT uty FROM UserType uty WHERE uty.account.email = ?1 AND uty.subscription.subscriptionType = 'PROFESSIONAL' ")
	UserType findUserTypeyEmail(String email);
}
