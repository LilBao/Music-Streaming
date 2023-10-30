package com.rhymthwave.Utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class SendMailTemplateService {
	@Autowired
	GetHostByRequest host;

	private static final String MAIL_TEMPLATE_BASE_NAME = "templateMail";
	private static final String MAIL_TEMPLATE_PREFIX = "/templates/Mail/";
	private static final String MAIL_TEMPLATE_SUFFIX = ".html";
	private static final String UTF_8 = "UTF-8";

	private static TemplateEngine templateEngine;

	static {
		templateEngine = emailTemplateEngine();
	}

	private static TemplateEngine emailTemplateEngine() {
		final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(htmlTemplateResolver());
		templateEngine.setTemplateEngineMessageSource(emailMessageSource());
		return templateEngine;
	}

	private static ResourceBundleMessageSource emailMessageSource() {
		final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename(MAIL_TEMPLATE_BASE_NAME);
		return messageSource;
	}

	private static ITemplateResolver htmlTemplateResolver() {
		final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix(MAIL_TEMPLATE_PREFIX);
		templateResolver.setSuffix(MAIL_TEMPLATE_SUFFIX);
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding(UTF_8);
		templateResolver.setCacheable(false);
		return templateResolver;
	}

	// For artist and podcaster
	public String getContentForConfirm(String owner, String templateName, String type, String address) {
		final Context context = new Context();
<<<<<<< HEAD
		context.setVariable("owner", owner);
		if (type.equalsIgnoreCase("podcast")) {
			context.setVariable("content",
					"is your email address, you’ll be able to publish episodes and distribute your podcast.");
			context.setVariable("address", address);
			context.setVariable("type", "Pobcaster");
		}
		if (type.equalsIgnoreCase("USER")) {
			context.setVariable("content",
					"is your email address, you’ll be able to publish episodes and distribute your podcast.");
			context.setVariable("address", address);
			context.setVariable("type", "USER");
		} else {
			context.setVariable("content",
					"is your email address, you’ll be received from us. Please follow your email to receive latest notifications.");
			context.setVariable("address", "");
			context.setVariable("address", address);
			context.setVariable("type", "Artist");
		}
		return templateEngine.process(templateName, context);
	}

	// Send template email  For Role
	public String getContentForNews(String title, String templateName, String content, String urlImg) {
		final Context context = new Context();
		context.setVariable("title", title);
		context.setVariable("content", content);
		context.setVariable("img", urlImg);

        context.setVariable("owner", owner);
        if(type.equalsIgnoreCase("podcast")) {
        	context.setVariable("content", "is your email address, you’ll be able to publish episodes and distribute your podcast.");
        	context.setVariable("address", address);
        	context.setVariable("type", "Pobcaster");
        }else {
        	context.setVariable("content", "is your email address, you’ll be received from us. Please follow your email to receive latest notifications.");
        	context.setVariable("type", "Artist");
        }
		return templateEngine.process(templateName, context);
	}

}
