package com.rhymthwave.ServiceAdmin.Implement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.AccountDAO;
import com.rhymthwave.ServiceAdmin.IAccountServiceAdmin;
import com.rhymthwave.Utilities.SortBy;
import com.rhymthwave.entity.Account;
import com.rhymthwave.entity.Author;
import com.rhymthwave.entity.Mood;
import com.rhymthwave.entity.TypeEnum.EROLE;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceAdminImp implements IAccountServiceAdmin{

	private final AccountDAO accountDAO;
	
	private final SortBy<String, String> sortService;
	
	@Override
	public Page<Account> findAllAccount(Integer page, String sortBy, String sortField, EROLE role) {
		

		try {
			Sort sort = sortService.sortBy(sortBy, sortField);

			Pageable pageable = PageRequest.of(page, 1, sort);

			Page<Account> pageAccount = accountDAO.findAllAccountRole(pageable,role);
			return pageAccount;
		} catch (Exception e) {
			return null;
		}
	}

	

}
