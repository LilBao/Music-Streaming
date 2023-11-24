package com.rhymthwave.Service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.AdvertismentDAO;
import com.rhymthwave.Service.AdvertisementService;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.entity.Advertisement;

@Service
public class AdvertisementServiceImpl implements CRUD<Advertisement, Long>, AdvertisementService{
	@Autowired
	AdvertismentDAO dao;

	@Override
	public Advertisement create(Advertisement entity) {

		return dao.save(entity);
	}

	@Override
	public Advertisement update(Advertisement entity) {
		return dao.save(entity);
	}

	@Override
	public Boolean delete(Long key) {
		dao.deleteById(key);
		return true;
	}

	@Override
	public Advertisement findOne(Long key) {
		if(key==null) {
			return null;
		}
		return dao.findById(key).get();
	}

	@Override
	public List<Advertisement> findAll() {
		return dao.findAll();
	}

	@Override
	public Advertisement updateStatusAds(Long id, Boolean active, Integer status) {
		Advertisement ads = findOne(id);
		if(ads!=null) {
			ads.setActive(false);
			ads.setStatus(1);
			return update(ads);
		}else {
			return null;
		}
	}

	@Override
	public List<Advertisement> findAdsByEmail(String email) {
		return dao.findAdsByEmail(email);
	}
}
