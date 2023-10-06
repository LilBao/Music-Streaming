package com.rhymthwave.API;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.CloudinaryService;
import com.rhymthwave.Service.ImageService;
import com.rhymthwave.entity.Account;
import com.rhymthwave.entity.Artist;
import com.rhymthwave.entity.Image;

import jakarta.websocket.server.PathParam;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ArtistREST {
	@Autowired
	CRUD<Artist, Integer> crud;
	
	@Autowired
	CloudinaryService cloudinary;
	
	@Autowired
	CRUD<Image, String> crudImg;
	
	@Autowired
	ImageService imgSer;
	
	@Autowired
	CRUD<Account, String> crudAccount;

	@GetMapping("/api/v1/artist")
	public ResponseEntity<MessageResponse> getAll() {
		return ResponseEntity.ok(new MessageResponse(true, "succeess", crud.findAll()));
	}

	@GetMapping("/api/v1/artist/{id}")
	public ResponseEntity<MessageResponse> getById(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(new MessageResponse(true, "succeess", crud.findOne(id)));
	}

	@PostMapping(value="/api/v1/artist",consumes = { "multipart/form-data" })
	public ResponseEntity<MessageResponse> creatArtist(@ModelAttribute Artist artist,
			@PathParam("avatar") MultipartFile avatar, @PathParam("background") MultipartFile background) {
		Map<String,Object> respAvatar = cloudinary.UploadResizeImage(avatar,"Avatar",artist.getArtistName(),512,512);
		Map<String,Object> respBg = cloudinary.UploadResizeImage(background,"Background",artist.getArtistName(),1500,500);
		
		Image imgAvatar = imgSer.getEntity((String)respAvatar.get("asset_id"), (String)respAvatar.get("url"), (Integer) respAvatar.get("width"), (Integer)respAvatar.get("height"));
		Image imgBackground = imgSer.getEntity((String)respBg.get("asset_id"), (String)respBg.get("url"),(Integer) respBg.get("width"),(Integer) respBg.get("height"));
		
		crudImg.create(imgAvatar);
		crudImg.create(imgBackground);
		
		artist.setBackgroundImage(imgBackground);
		artist.setImagesProfile(imgAvatar);
		artist.setAccounts(crudAccount.findOne("mck@gmail.com"));
		return ResponseEntity.ok(new MessageResponse(true, "succeess", crud.create(artist)));
	}
	
	
}
