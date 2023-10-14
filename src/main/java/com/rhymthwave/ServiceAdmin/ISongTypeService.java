package com.rhymthwave.ServiceAdmin;

import org.springframework.data.domain.Page;

import com.rhymthwave.entity.Mood;
import com.rhymthwave.entity.SongStyle;

public interface ISongTypeService {

	Page<SongStyle> getSongTypePage(Integer page, String sortBy, String sortField);

	
}
