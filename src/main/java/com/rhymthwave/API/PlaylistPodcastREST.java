package com.rhymthwave.API;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Utilities.GetHostByRequest;
import com.rhymthwave.entity.Account;
import com.rhymthwave.entity.Playlist_Podcast;
import com.rhymthwave.entity.UserType;
import com.rhymthwave.entity.Wishlist;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class PlaylistPodcastREST {
	private final CRUD<Account, String> crudAccount;

	private final CRUD<Playlist_Podcast, Long> crudPlaylistPodcast;

	private final GetHostByRequest host;

	@PostMapping("/api/v1/playlist-episode")
	public ResponseEntity<MessageResponse> additionEpisodeIntoPlaylist(HttpServletRequest req,
			@RequestBody Playlist_Podcast playlistPodcast) {
		String owner = host.getEmailByRequest(req);
		Account account = crudAccount.findOne(owner);
		UserType basic = account.getUserType().get(0);
		UserType premium = null;
		if(account.getUserType().size() > 1) {
			premium = account.getUserType().get(1);
		}
		Integer lengthPlaylist=0;
		if(playlistPodcast.getPlaylist().getPlaylistPodcast()!=null) {
			lengthPlaylist += playlistPodcast.getPlaylist().getPlaylistPodcast().toArray().length;
		}
		if(playlistPodcast.getPlaylist().getPlaylistRecords()!=null) {
			lengthPlaylist += playlistPodcast.getPlaylist().getPlaylistRecords().toArray().length;
		}
		if (lengthPlaylist <= basic.getSubscription().getNip()) {
			return ResponseEntity.ok(new MessageResponse(true, "success", crudPlaylistPodcast.create(playlistPodcast)));
		} else {
			if (premium != null) {
				if (premium.getEndDate().before(new Date())) {
					return ResponseEntity.ok(new MessageResponse(false, "Your subscription is expired!", null));
				} else {
					return ResponseEntity.ok(new MessageResponse(true, "success", crudPlaylistPodcast.create(playlistPodcast)));
				}
			} else {
				return ResponseEntity.ok(new MessageResponse(false, "Join Premium with us <3", null));
			}
		}

	}

	@DeleteMapping("/api/v1/playlist-episode/{id}")
	public ResponseEntity<MessageResponse> deleteEpisodeFromPlaylist(@PathVariable("id") Long id) {
		return ResponseEntity.ok(new MessageResponse(true, "success", crudPlaylistPodcast.delete(id)));
	}
}
