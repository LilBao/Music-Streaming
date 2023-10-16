package com.rhymthwave.API;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.Service.ArtistService;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.CloudinaryService;
import com.rhymthwave.Service.ImageService;
import com.rhymthwave.Service.SongService;
import com.rhymthwave.Utilities.GetHostByRequest;
import com.rhymthwave.Utilities.Cookie.CookiesUntils;
import com.rhymthwave.Utilities.JWT.JwtTokenCreate;
import com.rhymthwave.entity.Account;
import com.rhymthwave.entity.Image;
import com.rhymthwave.entity.Song;
import com.rhymthwave.entity.Writter;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class SongREST {

	private final ArtistService artistSer;

	private final CRUD<Song, Integer> crudSong;

	private final SongService songSer;

	private final CloudinaryService cloudinary;

	private final ImageService imgSer;

	private final CRUD<Image, String> crudImage;

	private final GetHostByRequest host;

	private final CRUD<Account, String> crudAccount;

	private final CRUD<Writter, Integer> crudWritter;

	@GetMapping("/api/v1/song")
	public ResponseEntity<MessageResponse> getAllSong() {
		return ResponseEntity.ok(new MessageResponse(true, "success", crudSong.findAll()));
	}

	@GetMapping("/api/v1/song/{id}")
	public ResponseEntity<MessageResponse> getOneSong(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(new MessageResponse(true, "success", crudSong.findOne(id)));
	}

	@PostMapping(value = "/api/v1/song", consumes = { "multipart/form-data" })
	public ResponseEntity<MessageResponse> createSong(@ModelAttribute Song song, HttpServletRequest req,
			@RequestParam("coverImg") MultipartFile coverImg) {
		// owner
		String owner = host.getEmailByRequest(req);
		Account account = crudAccount.findOne(owner);
		// create song
		if (coverImg != null) {
			Map<String, Object> respImg = cloudinary.Upload(coverImg, "CoverImage",
					account.getArtist().getArtistName());
			Image cover = imgSer.getEntity((String) respImg.get("asset_id"), (String) respImg.get("url"),
					(Integer) respImg.get("width"), (Integer) respImg.get("height"));
			cover.setPublicId((String) respImg.get("public_id"));
			crudImage.create(cover);
			song.setImage(cover);
		}
		Song dataSong = crudSong.create(song);
		// create writter
		Writter writter = new Writter();
		writter.setSong(dataSong);
		writter.setArtist(account.getArtist());
		crudWritter.create(writter);

		return ResponseEntity.ok(new MessageResponse(true, "success", dataSong));
	}

	@PutMapping(value = "/api/v1/song")
	public ResponseEntity<MessageResponse> updateSong(@RequestBody Song song) {
		Song dataSong = crudSong.update(song);
		return ResponseEntity.ok(new MessageResponse(true, "success", dataSong));
	}
	
	@PutMapping(value = "/api/v1/song-image/{id}", consumes = { "multipart/form-data" })
	public ResponseEntity<MessageResponse> updateSongImage(@PathVariable Integer id, HttpServletRequest req,
			@RequestParam("coverImg") MultipartFile coverImg) {
		// owner
		String owner = host.getEmailByRequest(req);
		Account account = crudAccount.findOne(owner);
		Song song = crudSong.findOne(id);
		// create song
		if (coverImg != null) {
			Map<String, Object> respImg = cloudinary.Upload(coverImg, "CoverImage",
					account.getArtist().getArtistName());
			Image cover = imgSer.getEntity((String) respImg.get("asset_id"), (String) respImg.get("url"),
					(Integer) respImg.get("width"), (Integer) respImg.get("height"));
			cover.setPublicId((String) respImg.get("public_id"));
			crudImage.create(cover);
			song.setImage(cover);
		}
		Song dataSong = crudSong.update(song);
		return ResponseEntity.ok(new MessageResponse(true, "success", dataSong));
	}

	@GetMapping("/api/v1/song/up-coming")
	public ResponseEntity<MessageResponse> songUpcoming(HttpServletRequest req) {
		String owner = host.getEmailByRequest(req);
		return ResponseEntity.ok(new MessageResponse(true, "success", songSer.findSongNotRecord(owner)));
	}

	@GetMapping("/api/v1/song-artist-released")
	public ResponseEntity<MessageResponse> albumReleasedByArtist(HttpServletRequest req) {
		String owner = host.getEmailByRequest(req);
		Account account = crudAccount.findOne(owner);
		return ResponseEntity.ok(new MessageResponse(true, "success",
				songSer.findSongReleasedByArtist(account.getArtist().getArtistId())));
	}
}
