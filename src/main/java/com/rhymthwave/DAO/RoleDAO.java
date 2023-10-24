package com.rhymthwave.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rhymthwave.entity.Role;
import com.rhymthwave.entity.TypeEnum.EROLE;

public interface RoleDAO extends JpaRepository<Role, Integer>{
	Role findByRole(EROLE role);
}
