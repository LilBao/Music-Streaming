package com.rhymthwave.API_Admin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhymthwave.DAO.AccountDAO;
import com.rhymthwave.DAO.AdvertismentDAO;
import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.Service.AdvertisementService;
import com.rhymthwave.entity.Account;
import com.rhymthwave.entity.Advertisement;
import com.rhymthwave.entity.Recording;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/admin/advertisement/statistics")
@RequiredArgsConstructor
public class API_AdvertisementStatistics {

	private  final AdvertisementService advertisementService;
	@GetMapping("/{id}")
	public ResponseEntity<?> getAllAdvertisementRunAndComplete(@PathVariable("id") Integer idAdvertisementService) {

		Advertisement advertisement = advertisementService.getById(idAdvertisementService);

		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully",advertisement));
	}

	private  final AccountDAO accountDAO;
	@PostMapping("")
	public ResponseEntity<?> asd(@RequestBody String account) {

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			List<Account> accountsList = objectMapper.readValue(account, new TypeReference<List<Account>>() {
			});
			for (Account account1 : accountsList) {
				System.out.println(account1.getEmail());
			}
			accountDAO.saveAll(accountsList);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}



		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully"));
	}
}
