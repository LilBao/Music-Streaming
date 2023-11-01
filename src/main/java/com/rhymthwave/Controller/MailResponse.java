package com.rhymthwave.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rhymthwave.Service.CRUD;
import com.rhymthwave.entity.Account;
import com.rhymthwave.entity.Author;
import com.rhymthwave.entity.Role;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MailResponse {
	private final CRUD<Account, String> crudAccount;
	
	private final CRUD<Author, Long> crudAuthor;
	
	private final CRUD<Role, Integer> crudRole;
	
	@GetMapping("/confirm-podcaster")
	public String uiresp() {
		return "";
	}
	
	//Test ==> Move to controller
	@GetMapping("/confirm-email-podcaster/{email}")
	public String confirmArtist(@PathVariable("email") String email) {
		Account account = crudAccount.findOne(email);
		Role role = crudRole.findOne(3);
		Author author = new Author();
		author.setAccount(account);
		author.setRole(role);
		crudAuthor.create(author);
		return "redirect:/confirm-podcaster";
	}
}
