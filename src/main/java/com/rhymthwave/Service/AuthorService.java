package com.rhymthwave.Service;

import com.rhymthwave.entity.Author;

public interface AuthorService {
	Author findAuthor(Integer idRole, String email);
}
