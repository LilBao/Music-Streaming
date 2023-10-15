package com.rhymthwave.Utilities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rhymthwave.DAO.GenreDAO;
import com.rhymthwave.DAO.MoodDAO;
import com.rhymthwave.entity.Genre;
import com.rhymthwave.entity.Mood;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImportEx {
	
	
	private final GenreDAO dao;
	
	private final MoodDAO moodDao;
	
	@Autowired
	Excel excel;
	
	public void save (MultipartFile multipartFile) {
		try {
			List<Mood> instruments = excel.convertExceltoInstrument(multipartFile.getInputStream());
				System.out.println(instruments.isEmpty());
				moodDao.saveAll(instruments);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
