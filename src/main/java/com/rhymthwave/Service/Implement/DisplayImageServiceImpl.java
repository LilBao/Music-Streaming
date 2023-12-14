package com.rhymthwave.Service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.SlideDAO;
import com.rhymthwave.Service.DisplayImageService;
import com.rhymthwave.entity.Slide;

@Service
public class DisplayImageServiceImpl implements DisplayImageService{

	@Autowired
	SlideDAO dao;
	
	@Override
	public List<Slide> displayImageByPosition(String position) {
		return dao.getImageByPosition(position);
	}

}
