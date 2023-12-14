package com.rhymthwave.Service.Implement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.AdvertismentDAO;
import com.rhymthwave.Request.DTO.AdvertisementDTO;
import com.rhymthwave.Service.AdvertisementService;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.entity.Advertisement;

import jakarta.servlet.http.HttpServletRequest;

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

	@Override
	public List<Advertisement> findAdsRunning(Boolean active, Integer status) {
		return dao.findAdsRunning(active, status);
	}

	@Override
	public Advertisement save(AdvertisementDTO advertisementDTO, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Advertisement> getAllAdvertisementRunningAndCompleted() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Advertisement> getAllAdvertisementPendingAndReject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Advertisement getById(Integer idAdvertisementService) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getResultsADS(Integer idADS) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Advertisement setStatus(Integer advertisementID, Integer status, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
