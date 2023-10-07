package com.rhymthwave.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Recording;

@Repository
public interface RecordDAO extends JpaRepository<Recording, Integer>{
	
	@Query("Select o from Recording o where o.emailCreate = :creater")
	List<Recording> findByCreater(@Param("creater") String creater);
}
