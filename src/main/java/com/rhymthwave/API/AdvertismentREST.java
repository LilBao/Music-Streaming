package com.rhymthwave.API;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rhymthwave.DAO.AdvertismentDAO;
import com.rhymthwave.DTO.AdvertismentDTO;
import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.entity.Advertisement;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class AdvertismentREST {
	private final AdvertismentDAO dao;
	
	@PutMapping("/api/v1/ads")
	public ResponseEntity<MessageResponse> updateAds(@RequestBody AdvertismentDTO adsDto){
		Advertisement ads = dao.findById(adsDto.getAdId()).get();
		ads.setListened(adsDto.getListened());
		ads.setClicked(adsDto.getClicked());
		return ResponseEntity.ok(new MessageResponse(true,"success",dao.save(ads)));
	}
}
