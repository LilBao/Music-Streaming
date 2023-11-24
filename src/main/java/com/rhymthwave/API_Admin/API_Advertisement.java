package com.rhymthwave.API_Admin;

import com.rhymthwave.DAO.AdvertismentDAO;
import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.Service.AdvertisementService;
import com.rhymthwave.ServiceAdmin.IRole;
import com.rhymthwave.entity.Advertisement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/admin/advertisement")
@RequiredArgsConstructor
public class API_Advertisement {

	private  final AdvertisementService advertisementService;
	private  final AdvertismentDAO advertismentDAO;
	@GetMapping("/running-completed")
	public ResponseEntity<?> getAllAdvertisementRunAndComplete() {

		List<Advertisement> list = advertisementService.getAllAdvertisementRunningAndCompleted();

		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully",list));
	}

	@GetMapping("/pending-reject")
	public ResponseEntity<?> getAllAdvertisementPendingAndReject() {

		List<Advertisement> list = advertisementService.getAllAdvertisementPendingAndReject();

		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully",list));
	}

	@GetMapping("/count")
	public ResponseEntity<?> abc() {

		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(
				true, "Successfully",advertismentDAO.findAllAdvertisementPendingAndRejectByStatus()));
	}
}
