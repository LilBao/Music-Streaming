package com.rhymthwave.Service;

import com.rhymthwave.entity.Advertisement;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

public interface AdvertisementService extends CRUD<Advertisement, Long> {
    List<Advertisement> getAllAdvertisementRunningAndCompleted();

    List<Advertisement> getAllAdvertisementPendingAndReject();

    Advertisement getById(Integer idAdvertisementService);

    Advertisement updateStatusAds(Long id, Boolean active, Integer status);

    List<Advertisement> findAdsByEmail(String email);

    Map<String,Object> getResultsADS(Integer idADS);

    Advertisement setStatus(Integer advertisementID, Integer status, HttpServletRequest request);
}