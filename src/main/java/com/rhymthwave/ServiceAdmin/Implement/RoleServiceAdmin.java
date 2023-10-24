package com.rhymthwave.ServiceAdmin.Implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.RoleDAO;
import com.rhymthwave.ServiceAdmin.IRole;
import com.rhymthwave.entity.Role;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceAdmin implements IRole{

	private final RoleDAO roleDAO;
	
	@Override
	public List<Role> findAllRole() {
		return roleDAO.findAll();
	}

}
