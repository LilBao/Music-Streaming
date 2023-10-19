package com.rhymthwave.Service;

import java.util.List;

import com.rhymthwave.entity.Recording;

public interface RecordService {
	List<Recording> findListRecordNotRaw(String email);
	
	List<Recording> findRecordByCreater(String email);
	
	List<Recording> findRawRecordByCreater(String email);
	
	List<Recording> findRecordBySong(Long songId);
	
	List<Recording> findRecordDelete(String email);
}
