package com.rhymthwave.API;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rhymthwave.Utilities.GetHostByRequest;
import com.rhymthwave.Utilities.SendMailTemplateService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class MailREST {
	
	private final SendMailTemplateService sendMailTemplateSer;
	
	private final GetHostByRequest host;
	
	@PostMapping("/api/v1/email-verify")
	public void sendMail(HttpServletRequest req) {
		String owner = host.getEmailByRequest(req);

		sendMailTemplateSer.getContentForConfirm(owner,"templateMail","podcast","abc");
		//covert MultipartFile to File
		//Gửi mail
		
	}
}
