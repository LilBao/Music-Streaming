package com.rhymthwave.API_GraphQL_Admin;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.rhymthwave.ServiceAdmin.IAccountServiceAdmin;
import com.rhymthwave.entity.Account;
import com.rhymthwave.entity.TypeEnum.EROLE;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class GraphQL_AccountManager {

	private final IAccountServiceAdmin accountServiceAdmin;
	
	@QueryMapping("getAllAccountByRole")
	public List<Account> findone(@Argument("role") EROLE role) {
	log.info(">>>>>>> GraphQL_AccountManager:findone | role: {}", role);
		return accountServiceAdmin.findAllAccountByRole(null, null, null, role);
	}
}
