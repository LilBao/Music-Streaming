package com.rhymthwave.Service.Implement;

import com.rhymthwave.DAO.AccountDAO;
import com.rhymthwave.DAO.AdvertismentDAO;
import com.rhymthwave.DAO.ImageDAO;
import com.rhymthwave.DAO.SubscriptionDAO;
import com.rhymthwave.Request.DTO.AdvertisementDTO;
import com.rhymthwave.Service.AdvertisementService;
import com.rhymthwave.Service.CloudinaryService;
import com.rhymthwave.Utilities.GetCurrentTime;
import com.rhymthwave.Utilities.GetHostByRequest;
import com.rhymthwave.entity.Advertisement;
import com.rhymthwave.entity.Image;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Primary
public class AdvertisementImpl implements AdvertisementService {

	private final AdvertismentDAO advertisementDAO;

	private final GetHostByRequest getIdByRequest;

	private final CloudinaryService cloudinaryService;

	private final AccountDAO accountDAO;

	private final SubscriptionDAO subscriptionDAO;

	private final ImageDAO imageDAO;

    private static String FOLDER_CONTAINING_IMAGE_NEWS  = "ImageManager";

	@Override
	public Advertisement save(AdvertisementDTO dto, HttpServletRequest request) {
		Map<String, Object> mapCloudinary = cloudinaryService.Upload(dto.getImage(), FOLDER_CONTAINING_IMAGE_NEWS,
				"Advertisement");
		Map<String, Object> map = cloudinaryService.Upload(dto.getAudio(), "Audio", "Advertisement");
		String urlImage = (String) mapCloudinary.get("url");
		String urlAudio = (String) map.get("url");
		String accessId = (String) mapCloudinary.get("asset_id");
		String public_id = (String) mapCloudinary.get("public_id");
		Image image = new Image();
		image.setUrl(urlImage);
		image.setPublicId(public_id);
		image.setAccessId(accessId);
		imageDAO.save(image);
        Advertisement advertisement = new Advertisement();
        advertisement.setActive(true);
        advertisement.setStatus(2);
        advertisement.setUrl(dto.getUrl());
        advertisement.setTitle(dto.getTitle());
        advertisement.setTag(dto.getTag());
        advertisement.setStartDate(GetCurrentTime.getTimeNow());
        advertisement.setContent(dto.getContent());
        advertisement.setPriority(subscriptionDAO.findById(dto.getSubscription()).orElse(null).getPriority());
        advertisement.setAudioFile(urlAudio);
        advertisement.setImage(image);
        advertisement.setListened(0L);
        advertisement.setClicked(0L);
        advertisement.setAccount(accountDAO.findById(getIdByRequest.getEmailByRequest(request)).orElse(null));
        advertisement.setSubscription(subscriptionDAO.findById(dto.getSubscription()).orElse(null));
        return advertisementDAO.save(advertisement);
	}

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

	@Override
	public Advertisement updateStatusAds(Long id, Boolean active, Integer status) {
		Advertisement ads = findOne(id);
		if (ads != null) {
			ads.setActive(false);
			ads.setStatus(1);
			return update(ads);
		} else {
			return null;
		}
	}

	@Override
	public List<Advertisement> findAdsByEmail(String email) {
		return advertisementDAO.findAdsByEmail(email);
	}

	@Override
	public Map<String, Object> getResultsADS(Integer idADS) {
		Advertisement advertisement = getById(idADS);
		double resultsListened = advertisement.getListened();
		double resultsClicked = advertisement.getClicked();
		Map<String, Object> list = new HashMap<>();

		if (resultsListened > advertisement.getClicked()) {
			list.put("resultsListened", Math.round((resultsListened / resultsListened * 100)));
			list.put("resultsClicked", Math.round((resultsClicked / resultsListened * 100)));
		} else {
			list.put("resultsListened", Math.round((resultsListened / resultsClicked * 100)));
			list.put("resultsClicked", Math.round((resultsClicked / resultsClicked * 100)));
		}
		return list;
	}

	@Override
	public Advertisement setStatus(Integer advertisementID, Integer status, HttpServletRequest request) {
		String modify = getIdByRequest.getEmailByRequest(request);
		Advertisement advertisement = getById(advertisementID);
		advertisement.setStatus(status);
		advertisement.setModifiedBy(modify);
		if (status == 4)
			advertisement.setActive(false);
		else
			advertisement.setActive(true);
		advertisement.setModifiDate(GetCurrentTime.getTimeNow());
		return advertisementDAO.save(advertisement);
	}

	@Override
	public Advertisement create(Advertisement entity) {

		return advertisementDAO.save(entity);
	}

	@Override
	public Advertisement update(Advertisement entity) {
		return advertisementDAO.save(entity);
	}

	@Override
	public Boolean delete(Long key) {
		advertisementDAO.deleteById(key);
		return true;
	}

	@Override
	public Advertisement findOne(Long key) {
		if (key == null) {
			return null;
		}
		return advertisementDAO.findById(key).get();
	}

	@Override
	public List<Advertisement> findAll() {
		return advertisementDAO.findAll();
	}

	@Transactional
	@Override
	public Advertisement buyAds(AdvertisementDTO dto, HttpServletRequest request) {
		Map<String, Object> mapCloudinary = cloudinaryService.Upload(dto.getImage(), FOLDER_CONTAINING_IMAGE_NEWS,
				"Advertisement");
		String audioString = null;
		if (dto.getAudio() != null) {
			Map<String, Object> map = cloudinaryService.Upload(dto.getAudio(), "Audio", "Advertisement");
			audioString = (String) map.get("url");
		}

		String urlImage = (mapCloudinary != null) ? (String) mapCloudinary.get("url") : null;
		String accessId = (mapCloudinary != null) ? (String) mapCloudinary.get("asset_id") : null;
		String public_id = (mapCloudinary != null) ? (String) mapCloudinary.get("public_id") : null;

		Image image = new Image();
		image.setUrl(urlImage);
		image.setPublicId(public_id);
		image.setAccessId(accessId);

		if (mapCloudinary != null) {
			imageDAO.save(image);
		}

		Advertisement advertisement = new Advertisement();
		advertisement.setActive(false);
		advertisement.setStatus(1);
		advertisement.setClicked(0L);
		advertisement.setListened(0L);
		advertisement.setUrl(dto.getUrl());
		advertisement.setTitle(dto.getTitle());
		advertisement.setTag(dto.getTag());
		advertisement.setTargetAudience(dto.getTarget());
		advertisement.setContent((dto.getContent().equals("undefined")) ? null : dto.getContent());
		advertisement.setAudioFile(audioString);
		advertisement.setImage((mapCloudinary != null) ? image : null);
		advertisement.setCurrency("$");
		advertisement.setSubscription(subscriptionDAO.findById(dto.getSubscriptionId()).orElse(null));
		advertisement.setPriority((dto.getPriority()));
		advertisement.setAccount(accountDAO.findById(getIdByRequest.getEmailByRequest(request)).orElse(null));

		return advertisementDAO.save(advertisement);
	}

}
