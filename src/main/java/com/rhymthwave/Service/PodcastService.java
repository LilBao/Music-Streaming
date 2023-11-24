package com.rhymthwave.Service;

import java.util.List;

import com.rhymthwave.entity.Podcast;
import com.rhymthwave.entity.Recording;

public interface PodcastService {
	List<Podcast> findMyPodcast(String email);
	
}
