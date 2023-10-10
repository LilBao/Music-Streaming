package com.rhymthwave.Service_LR.Implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rhymthwave.Request.DTO.LoginDTO;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service_LR.ILoginService;
import com.rhymthwave.entity.Account;

@Service
public class LoginServiceImp implements ILoginService{
	
	
	@Autowired
	private CRUD<Account, String> accountService; 
	
	@Autowired
	private PasswordEncoder encoder;
	

	@Override
	public int checkIsVerified(LoginDTO loginRequest) {
		Account user = accountService.findOne(loginRequest.email());
		if (user == null) {
			return 0;
		}
		
		if(encoder.matches(loginRequest.password(), user.getPassword()) && user.isVerify() && user.isBlocked() == false) {
			return 1;
		}
		if(user.isBlocked() == true) {
			return 3;
		}
		if (user.isVerify() == false) {
			return 2;
		}
		
		return 4;
	}

}
