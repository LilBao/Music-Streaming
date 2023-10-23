package com.rhymthwave.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Episode;

@Repository
public interface EpisodeDAO extends JpaRepository<Episode, Long>{
	@Query("select o from Episode o where o.podcast.podcastId = :podcastId")
	List<Episode> findAllEpisodeByPodcast(@Param("podcastId") Long podcastId);
}
