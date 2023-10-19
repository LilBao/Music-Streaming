package com.rhymthwave.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Episode;

@Repository
public interface EpisodeDAO extends JpaRepository<Episode, Long>{

}
