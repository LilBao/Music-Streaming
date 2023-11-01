package com.rhymthwave.Service;

import java.util.List;
import java.util.Map;

import com.rhymthwave.entity.Episode;
import com.rhymthwave.entity.Image;

public interface EpisodeService{
	Episode snapEpisode(Episode episode,Map<?,?> recordAudio, Image coverImg);
	
	List<Episode> findAllEpisodeByPodcast(Long podcastId, Boolean status);
	
	Episode findLatestEpisodeByPodcast(Long podcastId);
}
