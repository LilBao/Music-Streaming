package com.rhymthwave.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Account;
import com.rhymthwave.entity.Episode;
import com.rhymthwave.entity.MonitorEpisode;

@Repository
public interface MonitorEpisodeDAO extends JpaRepository<MonitorEpisode, Long>{
	MonitorEpisode findByEpisodeAndAccount(Episode episode, Account account);
}
