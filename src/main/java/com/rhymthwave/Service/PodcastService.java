package com.rhymthwave.Service;

import java.util.List;

import com.rhymthwave.entity.Podcast;

public interface PodcastService {
	List<Podcast> findMyPodcast(String email);
}
