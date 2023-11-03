package com.rhymthwave.Service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.CategoryDAO;
import com.rhymthwave.Service.CategoryService;
import com.rhymthwave.entity.Category;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryDAO dao;
	
	@Override
	public List<Category> SearchMedia(String keyword) {
		return dao.SearchMedia(keyword);
	}

}
