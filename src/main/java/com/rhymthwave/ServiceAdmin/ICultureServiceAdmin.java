package com.rhymthwave.ServiceAdmin;

import org.springframework.data.domain.Page;

import com.rhymthwave.entity.Culture;

public interface ICultureService {

	Page<Culture> getCulturePage(Integer page, String sortBy, String sortField);

	
}
