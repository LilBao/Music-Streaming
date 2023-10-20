package com.rhymthwave.ServiceAdmin;

import org.springframework.data.domain.Page;

import com.rhymthwave.entity.Mood;

public interface IMoodService {

	Page<Mood> getMoodPage(Integer page, String sortBy, String sortField);

	
}
