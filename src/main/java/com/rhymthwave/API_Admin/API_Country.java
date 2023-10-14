//package com.rhymthwave.API_Admin;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.rhymthwave.DTO.MessageResponse;
//import com.rhymthwave.Service.CRUD;
//import com.rhymthwave.ServiceAdmin.ICountryService;
//import com.rhymthwave.entity.Country;
//
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//
//@RestController
//@CrossOrigin("*")
//@RequestMapping("/api/v1/category/country")
//public class API_Country {
//	
//	@Autowired
//	private  ICountryService iCountryService;
//	
//	@Autowired
//	private  CRUD<Country, Integer> crud;
//	
//	@GetMapping
//	public ResponseEntity<?> getAllSongType(
//			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
//			@RequestParam(value = "sortBy", required = false, defaultValue = "asc") String sortBy,
//			@RequestParam(value = "sortfield", required = false, defaultValue = "moodid") String sortField) {
//
//		Page<Country> pages = iCountryService.getCountryPage(page, sortBy, sortField);
//		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", pages));
//	}
//
//	@GetMapping("/{id}")
//	public ResponseEntity<?> findOneMood(@PathVariable("id") Integer id) {
//
//		Country country = crud.findOne(id);
//
//		if (country == null) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(false, "Mood does exists", country));
//		}
//
//		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", country));
//	}
//
//	@PostMapping()
//	public ResponseEntity<?> createMood(@RequestBody Country countryRequest, final HttpServletRequest request) {
//
//		Country country = crud.create(countryRequest);
//		if (country == null) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(false, "Mood exists", country));
//		}
//		
//		return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse(true, "Successfully", country));
//	}
//	
//	@PutMapping("/{id}")
//	public ResponseEntity<?> updateMood(@PathVariable("id") Integer id,  @RequestBody Country countryRequest) {
//		
//		Country country = crud.update(countryRequest);
//		
//		if (country == null) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(false, "Mood does exists", country));
//		}
//		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", country));
//	}
//	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<?> deleteMood(@PathVariable("id") Integer id) {
//		
//		boolean country = crud.delete(id);
//		
//		if (country == false) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(false, "Mood does exists", null));
//		}
//
//		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", null));
//	}
//	
//}
