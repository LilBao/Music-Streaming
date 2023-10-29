package com.rhymthwave.API_LR;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rhymthwave.DAO.AccountDAO;
import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.Request.DTO.SignUpDTO;
import com.rhymthwave.Service.Implement.CustomUserDetails;
import com.rhymthwave.Service_LR.ISignUpService;
import com.rhymthwave.Service_LR.Implement.SignUpServiceImpl;
//import com.rhymthwave.Utilities.JWT.VerificationTokenRepository;
//import com.rhymthwave.Utilities.JWT.VerifyToken;
import com.rhymthwave.entity.Account;
import com.rhymthwave.event.SignUpCompleteEvent;
import com.rhymthwave.event.listener.SignUpCompleteEventListener;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/v1/accounts")
public class SignUpAPI {

	@Autowired
	private SignUpServiceImpl signUpServiceImpl;

	@Autowired
	private ApplicationEventPublisher publisher;

	private Account account;

	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private SignUpCompleteEventListener listener;

	@GetMapping("/acc")
	public List<Account> getAccounts() {
		return signUpServiceImpl.getAccounts();
	}

	@PostMapping("/signUp")
	public ResponseEntity<?> signUp(@RequestBody SignUpDTO signUpDTO, final HttpServletRequest request) {
		Account account = signUpServiceImpl.signUp(signUpDTO);
		publisher.publishEvent(new SignUpCompleteEvent(account, applicationUrl(request)));
		return ResponseEntity.ok(new MessageResponse(true, "Success! Please, check your email for to complete your signUp"));
	}

	@GetMapping("/verifyEmail")
	public ResponseEntity<?> verifyEmail(@RequestParam("token") String verificationToken) {
		Account account = accountDAO.findByVerificationCode(verificationToken);

		if (account == null) {
			return ResponseEntity.badRequest().body("Invalid verification token");
		}

		Calendar calendar = Calendar.getInstance();
		int remainingVerification = account.getRemainingVerification();
		
		if (account.getVerificationCodeExpires().getTime() - calendar.getTime().getTime() <= 0) {
			remainingVerification--;
			account.setRemainingVerification(remainingVerification);
			account.setVerificationCodeExpires(listener.getTokenExpirationTime());
			accountDAO.save(account);
			return ResponseEntity.badRequest().body("The token has expired, please click on the verification link again");
		}

		if (remainingVerification == 0) {
			account.setVerificationCode(null);
			account.setVerificationCodeExpires(null);
			account.setBlocked(true);
			accountDAO.save(account);
			return ResponseEntity.badRequest().body("Email is blocked");
		}

		account.setVerificationCode(null);
		account.setVerificationCodeExpires(null);
		account.setVerify(true);
		accountDAO.save(account);

		return ResponseEntity.ok("Verified successfully");
	}

	private String applicationUrl(HttpServletRequest request) {

		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}
}