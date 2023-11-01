package com.rhymthwave.Service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.RoleDAO;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.entity.Role;

@Service
public class RoleServiceImpl implements CRUD<Role, Integer>{

	@Autowired
	RoleDAO dao;
	
	@Override
	public Role create(Role entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role update(Role entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role findOne(Integer key) {
		return dao.findById(key).get();
	}

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
