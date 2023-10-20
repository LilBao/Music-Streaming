package com.rhymthwave.ServiceAdmin;

import org.springframework.data.domain.Page;

import com.rhymthwave.entity.Instrument;

public interface IInstrumentService {
	Page<Instrument> getInstrumentPage(Integer page, String sortBy, String sortField);
}
