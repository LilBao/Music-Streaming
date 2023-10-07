package com.rhymthwave.Service;

import java.util.List;

import com.rhymthwave.entity.Recording;

public interface RecordService {
	List<Recording> findRecordByCreater(String email);
}
