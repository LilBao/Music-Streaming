package com.rhymthwave.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(	
		info = 	@Info(
					contact = @Contact(name = "Swe",email = "rhymthwave@gmail.com",url = "URL WEB"),
					description = "Docs API rhymthwave",
					title = "Docs API rhymthwave",
					version = "1.0",
					license = @License(name = "abc name", url = "url/"),
					termsOfService =  "terms of service"
				),
		servers = {
				@Server(description = "localhost dev", url = "http://localhost:8080")
		}
)

public class ConfigSwagger {

}
