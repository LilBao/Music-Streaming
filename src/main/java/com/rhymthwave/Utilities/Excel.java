package com.rhymthwave.Utilities;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import com.rhymthwave.entity.Culture;
import com.rhymthwave.entity.Genre;
import com.rhymthwave.entity.SongStyle;

@Service
public class Excel {
		public List<Genre>  convertExceltoInstrument(InputStream inputStream){
			
			List<Genre> list = new ArrayList<>();
			try {
				Workbook workbook = WorkbookFactory.create(inputStream);
				Sheet sheet = workbook.getSheet("data4");
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
							instrument.setId((int) cell.getNumericCellValue());
							break;
						case 1:
							instrument.setNameGenre((cell.getStringCellValue())); 
							break;
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
