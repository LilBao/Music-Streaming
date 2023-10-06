package com.rhymthwave.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Writter;

@Repository
public interface WritterDAO extends JpaRepository<Writter, Integer>{

}
