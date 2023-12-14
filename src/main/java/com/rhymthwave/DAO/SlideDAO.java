package com.rhymthwave.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Slide;

@Repository
public interface SlideDAO extends JpaRepository<Slide, Integer>{

//	@Query(value = "select distinct POSITION from SLIDE",nativeQuery = true)
//	List<String> getSlidePositionContainer();
	
    @Query(value = "SELECT * FROM SLIDE WHERE position = ?1", nativeQuery = true)
	List<Slide> getSlidePostion(String position);
	
    @Query(value = "select * from slide where listimage = ?1", nativeQuery = true)
    Optional<Slide> findIDSlideByAccessID(String accessID);

    @Query(value = "select * from slide where listimage = ?1", nativeQuery = true)
	Slide findIDSlideByAccessIDToUpdate(String idAccess);
}
