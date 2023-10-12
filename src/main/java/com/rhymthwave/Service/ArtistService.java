package com.rhymthwave.Service;

import java.util.List;

import com.rhymthwave.entity.Artist;

public interface ArtistService {
	Artist findByEmail(String email);
	List<Artist> findIsVerify(Boolean verify);
}
