package com.rhymthwave.Service_LR.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.rhymthwave.DAO.AccountDAO;
import com.rhymthwave.DAO.AuthorDAO;
import com.rhymthwave.DAO.RoleDAO;
import com.rhymthwave.DTO.UserAlreadyExistsException;
import com.rhymthwave.Request.DTO.SignUpDTO;
import com.rhymthwave.Service_LR.ISignUpService;
//import com.rhymthwave.Utilities.JWT.VerificationTokenRepository;
//import com.rhymthwave.Utilities.JWT.VerifyToken;
import com.rhymthwave.entity.Account;
import com.rhymthwave.entity.Author;
import com.rhymthwave.entity.Role;
import com.rhymthwave.entity.UserType;
import com.rhymthwave.entity.TypeEnum.EROLE;
import com.rhymthwave.entity.TypeEnum.EUserType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements ISignUpService {

	private final AccountDAO dao;

	
	private final RoleDAO roleDAO;
	
	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private AuthorDAO authorDAO;

//	@Autowired
//	private VerificationTokenRepository tokenRepository;

	@Override
	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Account signUp(@RequestBody SignUpDTO request) {
		Account account = dao.findByEmail(request.email());
		if (account != null) {
			throw new UserAlreadyExistsException("User with email "+ request.email()+" already exists");
		}
		Account newAccount = new Account();
		newAccount.setEmail(request.email());
		newAccount.setUsername(request.username());
		newAccount.setPassword(encoder.encode(request.password()));
		newAccount.setBirthday(request.birthday());
		newAccount.setCountry(request.country());
		newAccount.setGender(request.gender());
        newAccount.setVerify(request.birthday() == null ? true : false);
		newAccount.setRemainingVerification(3);
		newAccount.setBlocked(false);
		dao.save(newAccount);
		Author newAuthor = new Author();
		newAuthor.setRole(roleDAO.findByRole(EROLE.USER));
		newAuthor.setAccount(newAccount);
		authorDAO.save(newAuthor);
		return newAccount ;
	}
}
