package com.rhymthwave.Service;

import com.rhymthwave.entity.Advertisement;

import java.util.List;

public interface AdvertisementService {
    List<Advertisement> getAllAdvertisementRunningAndCompleted();

    List<Advertisement> getAllAdvertisementPendingAndReject();

    Advertisement getById(Integer idAdvertisementService);
}
