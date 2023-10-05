package com.rhymthwave.Service;

import com.rhymthwave.entity.Artist;

public interface ArtistService {
	Artist findByEmail(String email);
}
