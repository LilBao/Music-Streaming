package com.rhymthwave.API_Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.ServiceAdmin.IMoodService;
import com.rhymthwave.entity.Mood;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/category/mood")
public class API_Mood {

	@Autowired
	private IMoodService iMoodService;

	@Autowired
	private CRUD<Mood, Integer> crud;

	@GetMapping
	public ResponseEntity<?> getAllMood(
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "sortBy", required = false, defaultValue = "asc") String sortBy,
			@RequestParam(value = "sortfield", required = false, defaultValue = "moodid") String sortField) {

		Page<Mood> pageMood = iMoodService.getMoodPage(page, sortBy, sortField);

		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", pageMood));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findOneMood(@PathVariable("id") Integer id) {

		Mood mood = crud.findOne(id);

		if (mood == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(false, "Mood does exists", mood));
		}

		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", mood));
	}

	@PostMapping()
	public ResponseEntity<?> createMood(@RequestBody Mood moodRequest) {

		Mood mood = crud.create(moodRequest);

		return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse(true, "Successfully", mood));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateMood(@PathVariable("id") Integer id,  @RequestBody Mood moodRequest) {
		
		Mood mood = crud.update(moodRequest);
		
		if (mood == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(false, "Mood does exists", mood));
		}

		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", mood));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMood(@PathVariable("id") Integer id) {
		
		boolean mood = crud.delete(id);
		
		if (mood == false) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(false, "Mood does exists", mood));
		}

		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", mood));
	}

}
