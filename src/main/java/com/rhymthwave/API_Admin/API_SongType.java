package com.rhymthwave.API_Admin;

import java.util.List;

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
import com.rhymthwave.ServiceAdmin.ISongTypeService;
import com.rhymthwave.Utilities.ExcelExportService;
import com.rhymthwave.entity.SongStyle;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/admin/category/song-style")
@RequiredArgsConstructor
public class API_SongType {
	
	private final ISongTypeService iSongTypeService;

	private final CRUD<SongStyle, Integer> crud;
	
    private final ExcelExportService excelExportService;
	
	@GetMapping
	public ResponseEntity<?> getAllSongType(
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "sortBy", required = false, defaultValue = "asc") String sortBy,
			@RequestParam(value = "sortfield", required = false, defaultValue = "songStyleId") String sortField) {

		Page<SongStyle> pages = iSongTypeService.getSongTypePage(page, sortBy, sortField);
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", pages));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findOneMood(@PathVariable("id") Integer id) {

		SongStyle songType = crud.findOne(id);

		if (songType == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(false, "Mood does exists", songType));
		}

		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", songType));
	}

	@PostMapping()
	public ResponseEntity<?> createMood(@RequestBody SongStyle songTypeRequest, final HttpServletRequest request) {

		SongStyle songType = crud.create(songTypeRequest);
		if (songType == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(false, "Mood exists", songType));
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse(true, "Successfully", songType));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateMood(@PathVariable("id") Integer id,  @RequestBody SongStyle songTypeRequest) {
		
		SongStyle songType = crud.update(songTypeRequest);
		
		if (songType == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(false, "Mood does exists", songType));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", songType));
	}
	
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMood(@PathVariable("id") Integer id) {
		
		boolean songType = crud.delete(id);
		
		if (songType == false) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(false, "Mood does exists", songType));
		}

		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", null));
	}
	
	
	@GetMapping("/export-excel")
    public ResponseEntity<?> exportToExcel(HttpServletResponse response) {
        List<SongStyle> SongStyle =  crud.findAll();
        try {
        	
            excelExportService.exportToExcel(SongStyle,response);
            return  ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Export excel successfully",""));
        } catch (Exception e) {
            e.printStackTrace();
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(false, "Export excel Error",""));
        }
    }
	
	
	
}
