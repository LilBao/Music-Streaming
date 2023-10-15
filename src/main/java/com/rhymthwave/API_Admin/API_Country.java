package com.rhymthwave.API_Admin;

import java.util.List;

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
import com.rhymthwave.ServiceAdmin.ICountryService;
import com.rhymthwave.Utilities.ExcelExportService;
import com.rhymthwave.entity.Country;
import com.rhymthwave.entity.Culture;
import com.rhymthwave.entity.Mood;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/category/country")
@RequiredArgsConstructor
public class API_Country {

	private final ICountryService iCountryService;

	private final  CRUD<Country, String> crud;

	private final ExcelExportService excelExportService;
	
	@GetMapping
	public ResponseEntity<?> getAllSongType(
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "sortBy", required = false, defaultValue = "asc") String sortBy,
			@RequestParam(value = "sortfield", required = false, defaultValue = "id") String sortField) {

		Page<Country> pages = iCountryService.getCountryPage(page, sortBy, sortField);
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", pages));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findOneMood(@PathVariable("id") String id) {

		Country country = crud.findOne(id);

		if (country == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new MessageResponse(false, "Mood does exists", country));
		}

		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", country));
	}

	@PostMapping()
	public ResponseEntity<?> createMood(@RequestBody Country countryRequest, final HttpServletRequest request) {

		Country country = crud.create(countryRequest);
		if (country == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new MessageResponse(false, "Mood exists", country));
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse(true, "Successfully", country));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateMood(@PathVariable("id") String id, @RequestBody Country countryRequest) {

		Country country = crud.update(countryRequest);

		if (country == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new MessageResponse(false, "Mood does exists", country));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", country));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMood(@PathVariable("id") String id) {

		boolean country = crud.delete(id);

		if (country == false) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new MessageResponse(false, "Mood does exists", null));
		}

		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", null));
	}
	
	@GetMapping("/export-excel")
    public ResponseEntity<?> exportToExcel(HttpServletResponse response) {
        List<Country> countries =  crud.findAll();
        try {
        	
            excelExportService.exportToExcel(countries,response);
            return  ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Export excel successfully",""));
        } catch (Exception e) {
            e.printStackTrace();
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(false, "Export excel Error",""));
        }
    }


}
