package com.rhymthwave.Service;

import java.util.List;
import java.util.Map;

import com.rhymthwave.Request.DTO.AdvertisementDTO;
import com.rhymthwave.entity.Advertisement;
import jakarta.servlet.http.HttpServletRequest;

public interface AdvertisementService extends CRUD<Advertisement, Long> {

    Advertisement save(AdvertisementDTO advertisementDTO,HttpServletRequest request);

    List<Advertisement> getAllAdvertisementRunningAndCompleted();

    List<Advertisement> getAllAdvertisementPendingAndReject();

    Advertisement getById(Integer idAdvertisementService);

    Map<String,Object> getResultsADS(Integer idADS);

    Advertisement setStatus(Integer advertisementID, Integer status, HttpServletRequest request);
    
    Advertisement updateStatusAds(Long id, Boolean active, Integer status);
	
	List<Advertisement> findAdsByEmail(String email);
	
	List<Advertisement> findAdsRunning(Boolean active,Integer status);
}
