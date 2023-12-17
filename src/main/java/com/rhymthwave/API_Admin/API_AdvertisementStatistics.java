package com.rhymthwave.API_Admin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhymthwave.DAO.AccountDAO;
import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.Service.AdvertisementService;
import com.rhymthwave.Service.Implement.ResultsADS_DTO;
import com.rhymthwave.entity.Account;
import com.rhymthwave.entity.Advertisement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/admin/advertisement/statistics")
@RequiredArgsConstructor
public class API_AdvertisementStatistics {

	private  final AdvertisementService advertisementService;
	@GetMapping("/{id}")
	public ResponseEntity<?> getAdvertisementRuOrComplete(@PathVariable("id") Integer idAdvertisementService) {

		Advertisement advertisement = advertisementService.getById(idAdvertisementService);

		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully",advertisement));
	}

	@GetMapping("/{id}/results")
	public ResponseEntity<?> getResults(@PathVariable("id") Integer idADS) {

		List<ResultsADS_DTO> resultsADS = advertisementService.getResultsADS(idADS);

		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully",resultsADS));
	}


}
