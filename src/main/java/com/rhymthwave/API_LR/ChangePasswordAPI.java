package com.rhymthwave.API_LR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rhymthwave.DAO.AccountDAO;
import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.Request.DTO.ChangePasswordDTO;
import com.rhymthwave.entity.Account;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/v1/accounts")
public class ChangePasswordAPI {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private AccountDAO accountDAO;

	@PutMapping("/changepassword")
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO changepasswordDTO) {
		// Lấy email từ request
		String email = changepasswordDTO.email();

		// Kiểm tra xác thực email và mật khẩu
		Account account = accountDAO.findByEmail(email);

		if (account == null) {
			return ResponseEntity.ok(new MessageResponse(true, "This email does not exist"));
		}

		String password = changepasswordDTO.password();
		if (!encoder.matches(password, account.getPassword())) {
			return ResponseEntity.ok(new MessageResponse(true, "Incorrect password"));
		}

		// Kiểm tra mật khẩu mới và xác nhận mật khẩu mới
		String newPassword = changepasswordDTO.newpass();
		String confirmPassword = changepasswordDTO.confirmpass();

		if (!newPassword.equals(confirmPassword)) {
			return ResponseEntity.ok(new MessageResponse(true, "New password and confirm password do not match"));

		}

		String encodedNewPassword = encoder.encode(newPassword);
		account.setPassword(encodedNewPassword);
		accountDAO.save(account);
		return ResponseEntity.ok(new MessageResponse(true, "Password changed successfully"));
	}
}
