package com.rhymthwave.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Slide;

@Repository
public interface SlideDAO extends JpaRepository<Slide, Integer>{

	@Query(value = "select distinct POSITION from SLIDE",nativeQuery = true)
	List<String> getSlidePositionContainer();
	
}
