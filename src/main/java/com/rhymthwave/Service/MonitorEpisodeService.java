package com.rhymthwave.Service;

import java.util.List;

import com.rhymthwave.entity.Account;
import com.rhymthwave.entity.Episode;
import com.rhymthwave.entity.MonitorEpisode;

public interface MonitorEpisodeService {
	MonitorEpisode checkExist(Episode episode, Account account);
	
	List<MonitorEpisode> findMonitorEpisodeByPodcast(Long id);
}
