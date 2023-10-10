package com.rhymthwave.Service_LR;

import com.rhymthwave.Request.DTO.LoginDTO;

public interface ILoginService {

	int checkIsVerified(LoginDTO loginRequest);
	
}
