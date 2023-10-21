package com.rhymthwave.ServiceAdmin.Implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.SlideDAO;
import com.rhymthwave.ServiceAdmin.IDisplaySlide;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DisplaySlideImp implements IDisplaySlide{

	private final SlideDAO slideDAO;
	
	
	@Override
	public List<String> getAllPositionSlidesShowing() {
		
		return slideDAO.getSlidePositionContainer();
	}

}
