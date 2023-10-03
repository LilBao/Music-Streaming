package com.rhymthwave.Service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.ImageDAO;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.ImageService;
import com.rhymthwave.entity.Image;

@Service
public class ImageServiceImpl implements ImageService, CRUD<Image, String>{

	@Autowired
	ImageDAO dao;
	
	@Override
	public Image create(Image entity) {
		return dao.save(entity);
	}

	@Override
	public Image update(Image entity) {
		return dao.save(entity);
	}

	@Override
	public Boolean delete(String key) {
		dao.deleteById(key);
		return true;
	}

	@Override
	public Image findOne(String key) {
		return null;
	}

	@Override
	public List<Image> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image getEntity(String assetid, String url, Integer weight, Integer height) {
		Image image = new Image();
		image.setAccessId(assetid);
		image.setUrl(url);
		image.setWeight(weight);
		image.setHeight(height);
		return image;
	}
	
}
