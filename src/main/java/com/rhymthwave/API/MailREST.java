package com.rhymthwave.API;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rhymthwave.Service.EmailService;
import com.rhymthwave.Utilities.GetHostByRequest;
import com.rhymthwave.Utilities.SendMailTemplateService;
import com.rhymthwave.entity.Email;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class MailREST {
	
	private final EmailService mailService;
	
	private final SendMailTemplateService sendMailTemplateSer;
	
	private final GetHostByRequest host;
	
	@PostMapping("/api/v1/email-confirm-podcast")
	public void sendMail(HttpServletRequest req) {
		String owner = host.getEmailByRequest(req);
		Email mail = new Email();
		mail.setTo(owner);
		mail.setSubject("RTHYMEWAVE: CONFIRM YOUR EMAIL");
		mail.setBody(sendMailTemplateSer.getContentForConfirm(owner, "templateMail", "podcast", applicationUrl(req,"/confirm-email-podcaster")));
		mailService.enqueue(mail);
	}
	
	@PostMapping("/api/v1/email-request-artist")
	public void sendMailRequestArtist(HttpServletRequest req) {
		String owner = host.getEmailByRequest(req);
		Email mail = new Email();
		mail.setTo(owner);
		mail.setSubject("RTHYMEWAVE: CONFIRM YOUR REQUEST");
		mail.setBody(sendMailTemplateSer.getContentForConfirm(owner, "templateMail", "podcast", applicationUrl(req,"/request-email-artist")));
		mailService.enqueue(mail);
	}
	
	private String applicationUrl(HttpServletRequest request, String path) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + path;
	}
}
