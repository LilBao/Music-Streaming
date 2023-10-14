package com.rhymthwave.ServiceAdmin;

import org.springframework.data.domain.Page;

import com.rhymthwave.entity.Country;

public interface ICountryService {

	Page<Country> getCountryPage(Integer page, String sortBy, String sortField);

	
}
