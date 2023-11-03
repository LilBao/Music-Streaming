package com.rhymthwave.DAO;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Category;

@Repository
public interface CategoryDAO extends JpaRepository<Category, Integer>{

	@Procedure(name = "SearchMedia")
	List<Category> SearchMedia(@Param("keyword") String keyword);
}
