package com.rhymthwave.Utilities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rhymthwave.DAO.GenreDAO;
import com.rhymthwave.entity.Genre;

@Service
public class ImportEx {
	
	
	@Autowired
	GenreDAO dao;
	
	
	@Autowired
	Excel excel;
	
	public void save (MultipartFile multipartFile) {
		try {
			List<Genre> instruments = excel.convertExceltoInstrument(multipartFile.getInputStream());
				System.out.println(instruments.isEmpty());
				dao.saveAll(instruments);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
