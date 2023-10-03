package com.rhymthwave.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Artist;

@Repository
public interface ArtistDAO extends JpaRepository<Artist, Integer>{

}
