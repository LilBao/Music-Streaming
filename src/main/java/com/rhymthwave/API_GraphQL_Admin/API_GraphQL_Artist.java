package com.rhymthwave.API_GraphQL_Admin;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.rhymthwave.ServiceAdmin.IArtistService;
import com.rhymthwave.entity.Artist;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class API_GraphQL_Artist {

	
	private  final  IArtistService artistService;
	
	
	
	@QueryMapping("findOneArtist")
	public Artist findone(@Argument("emailArtist") String email) {
		log.info(">>>>>>>>>>> idArtist {}", email);
		Artist artist = artistService.getOneArtistByEmail(email);
		return artist;
	}
//	
//	@QueryMapping("count")
//	public Object count(@Argument("idAccount") String idArtist) {
//		log.info(">>>>>>>>>>> idArtist {}", idArtist);
//		return idArtist;
//	}
	
//	
//	@QueryMapping("countReport")
//	public Integer count(@Argument("idAccount") String idAccount) {
//		int count = accountServiceAdmin.countReportByAccount(idAccount);
//		log.info(">>>>>>>>>>> idArtist {}", count);
//
//		return 123;
//	}

}
