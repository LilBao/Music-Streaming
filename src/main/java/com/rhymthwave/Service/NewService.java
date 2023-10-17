package com.rhymthwave.Service;

import com.rhymthwave.Request.DTO.NewDTO;
import com.rhymthwave.entity.News;

import jakarta.servlet.http.HttpServletRequest;

public interface NewService {

	News saveNews(NewDTO newDTO,HttpServletRequest request);

	News updateNews(Integer idNews, NewDTO newDTO, HttpServletRequest request);
}
