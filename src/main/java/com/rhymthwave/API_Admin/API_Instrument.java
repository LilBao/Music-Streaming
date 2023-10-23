package com.rhymthwave.API_Admin;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
import org.springframework.web.multipart.MultipartFile;

import com.rhymthwave.DAO.CountryDAO;
import com.rhymthwave.DAO.CultureDAO;
import com.rhymthwave.DAO.GenreDAO;
import com.rhymthwave.DAO.InstrumentDAO;
import com.rhymthwave.DAO.MoodDAO;
import com.rhymthwave.DAO.SongStyleDAO;
import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.ServiceAdmin.IInstrumentServiceAdmin;
import com.rhymthwave.Utilities.Excel;
import com.rhymthwave.Utilities.ExcelExportService;
import com.rhymthwave.Utilities.ImportEx;
import com.rhymthwave.entity.Country;
import com.rhymthwave.entity.Culture;
import com.rhymthwave.entity.Genre;
import com.rhymthwave.entity.Instrument;
import com.rhymthwave.entity.Mood;
import com.rhymthwave.entity.SongStyle;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/admin/category/instrument")
@RequiredArgsConstructor
public class API_Instrument {

	private final IInstrumentServiceAdmin iinstrumentService;

	
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

		Instrument instrument = iinstrumentService.findById(id);

		if (instrument == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(false, "Mood does exists", instrument));
		}

		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", instrument));
	}

	@PostMapping()
	public ResponseEntity<?> createMood(@RequestBody Instrument instrumentDTO, final HttpServletRequest request) {

		Instrument instrument = iinstrumentService.create(instrumentDTO,request );
		if (instrument == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(false, "Mood exists", instrument));
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse(true, "Successfully", instrument));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateMood(@PathVariable("id") Integer id,  @RequestBody Instrument instrumentDTO,final HttpServletRequest request) {
		
		Instrument instrument = iinstrumentService.update(instrumentDTO,request);
		
		if (instrument == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(false, "Mood does exists", instrument));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", instrument));
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMood(@PathVariable("id") Integer id) {
		
		boolean mood = iinstrumentService.delete(id);
		
		if (mood == false) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(false, "Mood does exists", mood));
		}

		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", mood));
	}
	
	@GetMapping("/export-excel")
    public ResponseEntity<?> exportToExcel(HttpServletResponse response) {
        List<Instrument> Instrument =  iinstrumentService.findAllInstrument();
        try {
        	
            excelExportService.exportToExcel(Instrument,response);
            return  ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Export excel successfully",""));
        } catch (Exception e) {
            e.printStackTrace();
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(false, "Export excel Error",""));
        }
    }
	
	
	private final GenreDAO moodDao;

	
	@GetMapping("/import")
	public ResponseEntity<?> deleteMood(@RequestParam("excel") MultipartFile file) {
		try {
			List<Genre> instruments = convertExceltoInstrument(file.getInputStream());
				System.out.println(instruments.isEmpty());
				moodDao.saveAll(instruments);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", "OK"));
	}
	
	public List<Genre>  convertExceltoInstrument(InputStream inputStream){
		
		List<Genre> list = new ArrayList<>();
		try {
			Workbook workbook = WorkbookFactory.create(inputStream);
			Sheet sheet = workbook.getSheet("Genre");
		int rowNum = 0;
		Iterator<Row> iterator = sheet.iterator();
		while (iterator.hasNext()) {
			Row row = iterator.next();
			if(rowNum == 0) {
				rowNum++;
				continue; 
			}
			Iterator<Cell> cells = row.iterator();
			
			int cid = 0;
			var instrument = new Genre();
			
			while (cells.hasNext()) {
				Cell cell = cells.next();
				switch(cid) {
					case 0:
						instrument.setId( (int)cell.getNumericCellValue());
						break;
					case 1:
						instrument.setNameGenre((cell.getStringCellValue())); 
						break;
//					case 2:
//						instrument.setCreateBy((cell.getStringCellValue())); 
//						break;
//					case 3:
//						instrument.setCreateDate(new Date()); 
//						break;
						
					default:
						break;
				}
				cid ++; 
			}
			list.add(instrument);
		}
		workbook.close();
		 inputStream.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
       
		
		return list;
	}

}
