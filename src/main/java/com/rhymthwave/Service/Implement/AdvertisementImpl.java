package com.rhymthwave.Service.Implement;

import com.rhymthwave.DAO.AdvertismentDAO;
import com.rhymthwave.Service.AdvertisementService;
import com.rhymthwave.entity.Advertisement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertisementImpl implements AdvertisementService {

    private final AdvertismentDAO advertisementDAO;

    @Override
    public List<Advertisement> getAllAdvertisementRunningAndCompleted() {
        return advertisementDAO.findAllAdvertisementRunningAndCompleted();
    }

    @Override
    public List<Advertisement> getAllAdvertisementPendingAndReject() {
        return advertisementDAO.findAllAdvertisementPendingAndReject();

    }

    @Override
    public Advertisement getById(Integer idAdvertisementService) {
        return advertisementDAO.findById(Long.valueOf(idAdvertisementService)).orElse(null);
    }
}
