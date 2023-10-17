package com.rhymthwave.API_Admin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.Request.DTO.NewDTO;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.CloudinaryService;
import com.rhymthwave.Service.NewService;
import com.rhymthwave.Service.Implement.CloudinaryServiceImpl;
import com.rhymthwave.entity.News;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/admin/new")
@RequiredArgsConstructor
public class API_New {
	
	private final NewService newService;
	
	private final CRUD<News, Integer> crud;
	
	@PostMapping()
	public ResponseEntity<?> createNew(@ModelAttribute NewDTO newDTO, final HttpServletRequest request){
		
		News news = newService.saveNews(newDTO, request);
		
		if(news == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(false, "Account not found", news));

		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse(true, "Successfully", news));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateNews(@PathVariable("id") Integer idNews,@ModelAttribute NewDTO newDTO, final HttpServletRequest request){
	
		News news = newService.updateNews(idNews,newDTO,request);
		
		if(news == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(false, "New with id not found ", news));

		}
		
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", news));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteNews(@PathVariable("id") Integer idNews){
		
		boolean status = crud.delete(idNews);
		
		if(status == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(status, "Successfully", status));

		}
		
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(status, "Successfully", status));
	}
	
	@GetMapping()
	public ResponseEntity<?> getAllNews(){
		
		List<News> list = crud.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", list));
	}
	


}
