package com.rhymthwave.ServiceAdmin;

import com.rhymthwave.entity.Artist;

public interface IArtistService {


	Artist getOneArtistByEmail(String email);
}
