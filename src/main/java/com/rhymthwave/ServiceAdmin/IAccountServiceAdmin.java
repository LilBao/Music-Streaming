package com.rhymthwave.ServiceAdmin;

import java.util.List;

import org.springframework.data.domain.Page;

import com.rhymthwave.entity.Account;
import com.rhymthwave.entity.Author;
import com.rhymthwave.entity.TypeEnum.EROLE;

public interface IAccountServiceAdmin {

	List<Account> findAllAccountByRole(Integer page,  String sortBy, String sortField, EROLE role);

	Account findById(String idUser);
	
	
}
