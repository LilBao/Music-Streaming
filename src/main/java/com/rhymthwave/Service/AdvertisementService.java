package com.rhymthwave.Service;

import java.util.List;

import com.rhymthwave.entity.Advertisement;

public interface AdvertisementService {
	Advertisement updateStatusAds(Long id, Boolean active, Integer status);
	
	List<Advertisement> findAdsByEmail(String email);
}
