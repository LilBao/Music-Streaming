package com.rhymthwave.API;

import java.util.Date;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.CloudinaryService;
import com.rhymthwave.Service.ImageService;
import com.rhymthwave.Service.PlaylistService;
import com.rhymthwave.Service.SubscriptionService;
import com.rhymthwave.Utilities.GetHostByRequest;
import com.rhymthwave.entity.Account;
import com.rhymthwave.entity.Image;
import com.rhymthwave.entity.Playlist;
import com.rhymthwave.entity.UserType;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class PlaylistREST {
	private final PlaylistService playlistSer;

	private final CRUD<Playlist, Long> crudPlaylist;

	private final CRUD<Image, String> crudImage;

	private final CRUD<Account, String> crudAccount;
	
	private final CRUD<UserType, Long> crudUserType;

	private final SubscriptionService subSer;

	private final ImageService imgSer;

	private final CloudinaryService cloudinary;

	private final GetHostByRequest host;

	@GetMapping("/api/v1/playlist")
	public ResponseEntity<MessageResponse> getAllPlaylist() {
		return ResponseEntity.ok(new MessageResponse(true, "success", crudPlaylist.findAll()));
	}

	@GetMapping("/api/v1/playlist/{id}")
	public ResponseEntity<MessageResponse> getPlaylist(@PathVariable("id") Long id) {
		return ResponseEntity.ok(new MessageResponse(true, "success", crudPlaylist.findOne(id)));
	}

	@GetMapping("/api/v1/my-playlist/{usertypeId}")
	public ResponseEntity<MessageResponse> getMyPlaylist(@PathVariable("usertypeId") Long usertypeId) {
		UserType usertype = crudUserType.findOne(usertypeId);
		return ResponseEntity.ok(new MessageResponse(true, "success", playlistSer.findMyPlaylist(usertype)));
	}

	@PostMapping("/api/v1/playlist")
	public ResponseEntity<MessageResponse> createPlaylist(@RequestBody Playlist playlist, HttpServletRequest req) {
		String owner = host.getEmailByRequest(req);
		Account account = crudAccount.findOne(owner);
		if (account.getUserType().get(0).getPlaylists().toArray().length < subSer.getSubByName("BASIC")
				.getPlaylistAllow()) {
			playlist.setUsertype(account.getUserType().get(0));
			return ResponseEntity.ok(new MessageResponse(true, "success", crudPlaylist.create(playlist)));
		} else {
			if (account.getUserType().toArray().length > 1) {
				if (account.getUserType().get(1).getEndDate().before(new Date())) {
					return ResponseEntity.ok(new MessageResponse(false, "success", "The premium account has expired."));
				} else {
					playlist.setUsertype(account.getUserType().get(1));
					return ResponseEntity.ok(new MessageResponse(true, "success", crudPlaylist.create(playlist)));
				}
			}else {
				return ResponseEntity.ok(new MessageResponse(false, "success", "Please upgrade your account package to premium."));
			}

		}
	}

	@PutMapping("/api/v1/playlist")
	public ResponseEntity<MessageResponse> updatePlaylist(@RequestBody Playlist playlist) {
		return ResponseEntity.ok(new MessageResponse(true, "success", crudPlaylist.update(playlist)));
	}

	@PutMapping(value = "/api/v1/playlist-image", consumes = { "multipart/form-data" })
	public ResponseEntity<MessageResponse> updateImagePlaylist(@ModelAttribute Playlist playlist,
			HttpServletRequest req, @PathParam("coverImg") MultipartFile coverImg) {
		String owner = host.getEmailByRequest(req);
		Account account = crudAccount.findOne(owner);
		if (coverImg != null) {
			Map<?, ?> respImg = cloudinary.Upload(coverImg, "ImagePlaylist", account.getUsername());
			Image image = imgSer.getEntity(respImg);
			crudImage.create(image);
			playlist.setImage(image);
		}

		return ResponseEntity.ok(new MessageResponse(true, "success", crudPlaylist.update(playlist)));
	}

	@DeleteMapping("/api/v1/playlist/{id}")
	public ResponseEntity<MessageResponse> updatePlaylist(@PathVariable("id") Long id) {
		return ResponseEntity.ok(new MessageResponse(true, "success", crudPlaylist.delete(id)));
	}
}