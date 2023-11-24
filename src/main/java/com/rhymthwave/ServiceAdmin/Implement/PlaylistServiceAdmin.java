package com.rhymthwave.ServiceAdmin.Implement;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rhymthwave.DAO.EpisodeDAO;
import com.rhymthwave.DAO.PlaylistDAO;
import com.rhymthwave.DAO.RecordDAO;
import com.rhymthwave.DAO.UserTypeDAO;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.CloudinaryService;
import com.rhymthwave.ServiceAdmin.IPlayListServiceAdmin;
import com.rhymthwave.Utilities.GetCurrentTime;
import com.rhymthwave.Utilities.GetHostByRequest;
import com.rhymthwave.entity.Episode;
import com.rhymthwave.entity.Image;
import com.rhymthwave.entity.Playlist;
import com.rhymthwave.entity.PlaylistRecord;
import com.rhymthwave.entity.Playlist_Podcast;
import com.rhymthwave.entity.Recording;
import com.rhymthwave.entity.UserType;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaylistServiceAdmin implements IPlayListServiceAdmin {

	private final RecordDAO recordDAO;

	private final EpisodeDAO episodeDAO;
	
	private final UserTypeDAO userTypeDAO;

	private final PlaylistDAO playlistDAO;
	
	private final CloudinaryService cloudinaryService;

	private final CRUD<Image, String> crudImage;

	private final CRUD<PlaylistRecord, Long> playlistRecordService;
	
	private final CRUD<Playlist_Podcast, Long> playlistPodcastService;

	private final CRUD<Playlist, Long> playlistService;

	private final GetHostByRequest getEmailByRequest;

	private String FOLDER_CONTAINING_IMAGE_PLAYLIST = "ImagePlaylist";

	private String FOLDER_CONTAINING_IMAGE_PLAYLIST_SYSTEM = "System";

	@Override
	public List<Recording> getListRecordRandom(String nameGenre, String culture, String mood, String songstyle,
			String instrument) {
		return recordDAO.findListRecordRandom(nameGenre, culture, instrument, mood, songstyle);
	}

	@Override
	public Playlist createPlayListForSongs(MultipartFile file, String playlistName, String description,
			List<Recording> listRecord, HttpServletRequest request) {
		UserType userType = userTypeDAO.findUserTypeyEmail(getEmailByRequest.getEmailByRequest(request));

		Playlist playlist = new Playlist();

		Map<String, Object> mapCloudinary = cloudinaryService.Upload(file, FOLDER_CONTAINING_IMAGE_PLAYLIST,
				FOLDER_CONTAINING_IMAGE_PLAYLIST_SYSTEM);

		String urlImage = (String) mapCloudinary.get("url");
		String accessId = (String) mapCloudinary.get("asset_id");
		String public_id = (String) mapCloudinary.get("public_id");
		// save image
		Image img = new Image();
		img.setUrl(urlImage);
		img.setAccessId(accessId);
		img.setPublicId(public_id);
		crudImage.create(img);

		// create playlist
		playlist.setPlaylistName(playlistName);
		playlist.setDescription(description);
		 playlist.setQuantity(listRecord.size());
		playlist.setIsPublic(false);
		playlist.setCreateDate(GetCurrentTime.getTimeNow());
		playlist.setImage(img);
		playlist.setUsertype(userType);
		playlistService.create(playlist);
		// create a playlist record

//		
		listRecord.forEach(recording -> {
			PlaylistRecord playlistRecord = new PlaylistRecord();
			playlistRecord.setRecording(recording);
			playlistRecord.setPlaylist(playlist);
			playlistRecordService.create(playlistRecord);
		});

		return playlist;
	}

	
	@Override
	public Playlist createPlayListForPodcast(MultipartFile file, String playlistName, String description,
			List<Episode> listPodcast, HttpServletRequest request) {
		UserType userType = userTypeDAO.findUserTypeyEmail(getEmailByRequest.getEmailByRequest(request));

		Playlist playlist = new Playlist();

		Map<String, Object> mapCloudinary = cloudinaryService.Upload(file, FOLDER_CONTAINING_IMAGE_PLAYLIST,
				FOLDER_CONTAINING_IMAGE_PLAYLIST_SYSTEM);

		String urlImage = (String) mapCloudinary.get("url");
		String accessId = (String) mapCloudinary.get("asset_id");
		String public_id = (String) mapCloudinary.get("public_id");
		// save image
		Image img = new Image();
		img.setUrl(urlImage);
		img.setAccessId(accessId);
		img.setPublicId(public_id);
		crudImage.create(img);

		// create playlist
		playlist.setPlaylistName(playlistName);
		playlist.setDescription(description);
		playlist.setQuantity(listPodcast.size());
		playlist.setIsPublic(false);
		playlist.setCreateDate(GetCurrentTime.getTimeNow());
		playlist.setImage(img);
		playlist.setUsertype(userType);
		playlistService.create(playlist);
		// create a playlist Podcast

//		
		listPodcast.forEach(episode -> {
			var playlist_Podcast = new Playlist_Podcast();
			playlist_Podcast.setEpisode(episode);
			playlist_Podcast.setPlaylist(playlist);
			playlistPodcastService.create(playlist_Podcast);
		});
		return playlist;
	}

	
	
	@Override
	public List<Playlist> getAllSongsPlaylist() {

		return playlistDAO.getplayListSongAdmin();
	}

	@Override
	public List<Playlist> getAllPodcastPlaylist() {
	
		return playlistDAO.getPodcastPlayListAdmin();
	}

	@Override
	public List<Episode> getListEpisodeRandom(Integer tag) {
		
		return episodeDAO.getRandomPodcasts(tag);
	}

	
}
