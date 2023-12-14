package com.rhymthwave.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;

import com.rhymthwave.entity.Podcast;

public interface PodcastService {
	List<Podcast> findMyPodcast(String email);
	
	List<Podcast> top50NewPodcast(Optional<String> country);
	
	List<Podcast> top50PodcastPopular(Optional<String> country);
}
