//package com.rhymthwave.API_Admin;
//
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
//import com.rhymthwave.ServiceAdmin.ICultureService;
//import com.rhymthwave.entity.Culture;
//
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//
//@RestController
//@CrossOrigin("*")
//@RequestMapping("/api/v1/category/culture")
//@RequiredArgsConstructor
//public class API_Culture {
//	
//	private final ICultureService iCultureService;
//
//	private final CRUD<Culture, Integer> crud;
//	
//	@GetMapping
//	public ResponseEntity<?> getAllSongType(
//			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
//			@RequestParam(value = "sortBy", required = false, defaultValue = "asc") String sortBy,
//			@RequestParam(value = "sortfield", required = false, defaultValue = "moodid") String sortField) {
//
//		Page<Culture> pages = iCultureService.getCulturePage(page, sortBy, sortField);
//		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", pages));
//	}
//
//	@GetMapping("/{id}")
//	public ResponseEntity<?> findOneMood(@PathVariable("id") Integer id) {
//
//		Culture culture = crud.findOne(id);
//
//		if (culture == null) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(false, "Mood does exists", culture));
//		}
//
//		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", culture));
//	}
//
//	@PostMapping()
//	public ResponseEntity<?> createMood(@RequestBody Culture cultureRequest, final HttpServletRequest request) {
//
//		Culture culture = crud.create(cultureRequest);
//		if (culture == null) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(false, "Mood exists", culture));
//		}
//		
//		return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse(true, "Successfully", culture));
//	}
//	
//	@PutMapping("/{id}")
//	public ResponseEntity<?> updateMood(@PathVariable("id") Integer id,  @RequestBody Culture cultureRequest) {
//		
//		Culture culture = crud.update(cultureRequest);
//		
//		if (culture == null) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(false, "Mood does exists", culture));
//		}
//		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", culture));
//	}
//	
//	
//	
//	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<?> deleteMood(@PathVariable("id") Integer id) {
//		
//		boolean culture = crud.delete(id);
//		
//		if (culture == false) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(false, "Mood does exists", null));
//		}
//
//		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", null));
//	}
//	
//}
