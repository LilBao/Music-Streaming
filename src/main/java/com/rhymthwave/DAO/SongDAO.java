package com.rhymthwave.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Song;

@Repository
public interface SongDAO extends JpaRepository<Song, Integer>{

}
