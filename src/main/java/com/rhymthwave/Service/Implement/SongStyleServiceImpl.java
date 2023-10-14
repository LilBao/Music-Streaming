package com.rhymthwave.Service.Implement;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.rhymthwave.DAO.SongStyleDAO;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.ServiceAdmin.ISongTypeService;
import com.rhymthwave.Utilities.SortBy;
import com.rhymthwave.entity.SongStyle;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SongStyleServiceImpl implements CRUD<SongStyle, Integer>,ISongTypeService{

	private final SongStyleDAO dao;
	
	private final SortBy<String, String > sortService;
	
	@Override
	@Transactional
	public SongStyle create(SongStyle entity) {
		if(entity!=null) {
			dao.save(entity);
			return entity;
		}
		return null;
	}

	@Override
	@Transactional
	public SongStyle update(SongStyle entity) {
		if(entity!=null) {
			dao.save(entity);
			return entity;
		}
		return null;
	}

	@Override
	@Transactional
	public Boolean delete(Integer key) {
		if(key instanceof Integer && key>0) {
			dao.deleteById(key);
			return true;
		}
		return false;
	}

	@Override
	public SongStyle findOne(Integer key) {
		if(key instanceof Integer && key>0) {
			return dao.findById(key).get();
		}
		return null;
	}

	@Override
	public List<SongStyle> findAll() {
		return dao.findAll();
	}

	@Override
	public Page<SongStyle> getSongTypePage(Integer page, String sortBy, String sortField) {

		try {
			Sort sort = sortService.sortBy(sortBy, sortField);

			Pageable pageable = PageRequest.of(page, 6, sort);

			Page<SongStyle> pageMood = dao.findAll(pageable);
			return pageMood;
		} catch (Exception e) {
			return null;
		}

	}


	
}
