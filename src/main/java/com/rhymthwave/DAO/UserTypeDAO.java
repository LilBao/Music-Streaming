package com.rhymthwave.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.UserType;

@Repository
public interface UserTypeDAO extends JpaRepository<UserType, Long>{
	
}