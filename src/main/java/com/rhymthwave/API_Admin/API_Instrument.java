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
import org.springframework.web.multipart.MultipartFile;

import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.ServiceAdmin.IInstrumentService;
import com.rhymthwave.Utilities.ExcelExportService;
import com.rhymthwave.Utilities.ImportEx;
import com.rhymthwave.entity.Instrument;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/admin/category/instrument")
@RequiredArgsConstructor
public class API_Instrument {

	private final IInstrumentService iinstrumentService;

	private final CRUD<Instrument, Integer> crud;
	
    private final ExcelExportService excelExportService;
	
	private final ImportEx importEx;
	
	@GetMapping
	public ResponseEntity<?> getAllMood(
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "sortBy", required = false, defaultValue = "asc") String sortBy,
			@RequestParam(value = "sortfield", required = false, defaultValue = "instrumentId") String sortField) {

		Page<Instrument> pageMood = iinstrumentService.getInstrumentPage(page, sortBy, sortField);
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", pageMood));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findOneMood(@PathVariable("id") Integer id) {

		Instrument instrument = crud.findOne(id);

		if (instrument == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(false, "Mood does exists", instrument));
		}

		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", instrument));
	}

	@PostMapping()
	public ResponseEntity<?> createMood(@RequestBody Instrument instrumentDTO, final HttpServletRequest request) {

		Instrument instrument = crud.create(instrumentDTO);
		if (instrument == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(false, "Mood exists", instrument));
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse(true, "Successfully", instrument));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateMood(@PathVariable("id") Integer id,  @RequestBody Instrument instrumentDTO) {
		
		Instrument instrument = crud.update(instrumentDTO);
		
		if (instrument == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(false, "Mood does exists", instrument));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", instrument));
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMood(@PathVariable("id") Integer id) {
		
		boolean mood = crud.delete(id);
		
		if (mood == false) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(false, "Mood does exists", mood));
		}

		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", mood));
	}
	
	@GetMapping("/export-excel")
    public ResponseEntity<?> exportToExcel(HttpServletResponse response) {
        List<Instrument> Instrument =  crud.findAll();
        try {
        	
            excelExportService.exportToExcel(Instrument,response);
            return  ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Export excel successfully",""));
        } catch (Exception e) {
            e.printStackTrace();
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(false, "Export excel Error",""));
        }
    }
	
	
	
	
	@GetMapping("/import")
	public ResponseEntity<?> deleteMood(@RequestParam("excel") MultipartFile file) {
		importEx.save(file);
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", "OK"));
	}

}
