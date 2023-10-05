package com.rhymthwave.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Recording;

@Repository
public interface RecordDAO extends JpaRepository<Recording, Integer>{

}
