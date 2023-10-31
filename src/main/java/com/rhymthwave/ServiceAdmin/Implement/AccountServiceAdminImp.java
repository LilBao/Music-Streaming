package com.rhymthwave.ServiceAdmin.Implement;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.AccountDAO;
import com.rhymthwave.ServiceAdmin.IAccountServiceAdmin;
import com.rhymthwave.Utilities.SortBy;
import com.rhymthwave.entity.Account;
import com.rhymthwave.entity.TypeEnum.EROLE;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceAdminImp implements IAccountServiceAdmin{

	private final AccountDAO accountDAO;
	
	private final SortBy<String, String> sortService;
	
	@Override
	public Page<Account> findAllAccountByRole(Integer page, String sortBy, String sortField, EROLE role) {

		try {
			Sort sort = sortService.sortBy(sortBy, sortField);

			Pageable pageable = PageRequest.of(page, 6,sort);

			Page<Account> pageAccount = accountDAO.findAllAccountRole(pageable, role);
			log.info(">>>>>>>>>>> AccountServiceAdminImp:findAllAccount |  Page findAllAccountByRole: {}",pageAccount);
			return pageAccount;
		} catch (Exception e) {
			log.error(">>>>>>>>>>> AccountServiceAdminImp:findAllAccount | Error findAllAccount: {}",e.getMessage());
			return null;
		}
	}

	@Override
	public Account findById(String idUser) {
		Optional<Account> account = accountDAO.findById(idUser);
		if(account.isEmpty()) {
			return null;
		}
		return account.get();
	}

	

}
