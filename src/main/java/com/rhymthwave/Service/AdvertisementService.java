package com.rhymthwave.Service;

import java.util.List;
import java.util.Map;

import com.rhymthwave.Request.DTO.AdvertisementDTO;
import com.rhymthwave.Service.Implement.ResultsADS_DTO;
import com.rhymthwave.entity.Advertisement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AdvertisementService extends CRUD<Advertisement, Long> {

    Advertisement save(AdvertisementDTO advertisementDTO,HttpServletRequest request);

    List<Advertisement> getAllAdvertisementRunningAndCompleted();

    List<Advertisement> getAllAdvertisementPendingAndReject();

    Advertisement getById(Integer idAdvertisementService);

    List<ResultsADS_DTO> getResultsADS(Integer idADS);

    void sendResultsADS(Integer idADS,HttpServletResponse response);

    Advertisement setStatus(Integer advertisementID, Integer status, HttpServletRequest request);
    
    Advertisement updateStatusAds(Long id, Boolean active, Integer status);
	
	List<Advertisement> findAdsByEmail(String email);
	
	List<Advertisement> findAdsRunning(Boolean active,Integer status);

    Advertisement buyAds(AdvertisementDTO advertisementDTO,HttpServletRequest request);
}
