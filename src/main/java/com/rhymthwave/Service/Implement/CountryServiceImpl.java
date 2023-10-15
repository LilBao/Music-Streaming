package com.rhymthwave.Service.Implement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.CountryDAO;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.ServiceAdmin.ICountryService;
import com.rhymthwave.Utilities.SortBy;
import com.rhymthwave.entity.Country;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
public class CountryServiceImpl implements CRUD<Country, String>, ICountryService{

	@Autowired
	private  CountryDAO dao;
	@Autowired
	private  SortBy<String, String> sortService;
	
	@Override
	@Transactional
	public Country create(Country entity) {
		if(entity!=null) {
			dao.save(entity);
			return entity;
		}
		return null;
	}

	@Override
	@Transactional
	public Country update(Country entity) {
		if(entity!=null) {
			dao.save(entity);
			return entity;
		}
		return null;
	}

	@Override
	@Transactional
	public Boolean delete(String key) {
		if(key instanceof String && !key.equals("")) {
			dao.deleteById(key);
			return true;
		}
		return false;
	}

	@Override
	public Country findOne(String key) {
		if(key instanceof String && !key.equals("")) {
			return dao.findById(key).get();
		}
		return null;
	}

	@Override
	public List<Country> findAll() {
		return dao.findAll();
	}

	@Override
	public Page<Country> getCountryPage(Integer page, String sortBy, String sortField) {

		try {
			Sort sort = sortService.sortBy(sortBy, sortField);

			Pageable pageable = PageRequest.of(page, 6, sort);

			Page<Country> pageMood = dao.findAll(pageable);
			return pageMood;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	
}
