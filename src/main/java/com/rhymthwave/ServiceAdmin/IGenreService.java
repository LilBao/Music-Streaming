package com.rhymthwave.ServiceAdmin;

import org.springframework.data.domain.Page;

import com.rhymthwave.entity.Genre;

public interface IGenreService {

	Page<Genre> getGenrePage(Integer page, String sortBy, String sortField);

	
}
