package com.rhymthwave.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.News;

@Repository
public interface NewsDAO extends JpaRepository<News, Integer>{

}
