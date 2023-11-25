package com.rhymthwave.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Account;
import com.rhymthwave.entity.Episode;
import com.rhymthwave.entity.MonitorEpisode;

@Repository
public interface MonitorEpisodeDAO extends JpaRepository<MonitorEpisode, Long>{
	MonitorEpisode findByEpisodeAndAccount(Episode episode, Account account);
	
	@Query("select o from MonitorEpisode o where o.episode.podcast.podcastId = :id")
	List<MonitorEpisode> findMonitorEpisodeByPodcast(@Param("id") Long id);
}
