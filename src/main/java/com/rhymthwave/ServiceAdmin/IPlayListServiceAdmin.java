package com.rhymthwave.ServiceAdmin;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rhymthwave.entity.Episode;
import com.rhymthwave.entity.Playlist;
import com.rhymthwave.entity.Recording;

import jakarta.servlet.http.HttpServletRequest;

public interface IPlayListServiceAdmin {

	List<Recording> getListRecordRandom(String nameGenre,String culture,String mood,String songstyle,String instrument);

	List<Episode> getListEpisodeRandom(Integer tag);
	
	Playlist createPlayListForSongs(MultipartFile file, String playlistName, String description, List<Recording> listRecord,
			HttpServletRequest request);

	Playlist createPlayListForPodcast(MultipartFile file, String playlistName, String description, List<Episode> listRecord,
			HttpServletRequest request);
	
	List<Playlist> getAllSongsPlaylist();
	
	List<Playlist> getAllPodcastPlaylist();

}
