package com.rhymthwave.API;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.Service.ArtistService;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.CloudinaryService;
import com.rhymthwave.Service.ImageService;
import com.rhymthwave.Service.SongService;
import com.rhymthwave.entity.Image;
import com.rhymthwave.entity.Song;

@RestController
@CrossOrigin("*")
public class SongREST {
	@Autowired
	ArtistService artistSer;
	
	@Autowired
	CRUD<Song, Integer> crudSong;
	
	@Autowired
	SongService songSer;
	
	@Autowired
	CloudinaryService cloudinary;
	
	@Autowired
	ImageService imgSer;
	
	@Autowired
	CRUD<Image, String> crudImage;
	
	@GetMapping("/api/v1/song")
	public ResponseEntity<MessageResponse> getAllSong(){
		return ResponseEntity.ok(new MessageResponse(true, "success", crudSong.findAll()));
	}
	
	@GetMapping("/api/v1/song/{id}")
	public ResponseEntity<MessageResponse> getOneSong(@PathVariable("id") Integer id){
		return ResponseEntity.ok(new MessageResponse(true, "success", crudSong.findOne(id)));
	}
	
	@PostMapping(value="/api/v1/song",consumes = { "multipart/form-data" })
	public ResponseEntity<MessageResponse> createSong(@ModelAttribute Song song, @RequestParam("coverImg") MultipartFile coverImg){
		if(coverImg.isEmpty()) {
			Map<String, Object> respImg = cloudinary.Upload(coverImg, "CoverImage", "MCK");
			Image cover = imgSer.getEntity((String) respImg.get("asset_id"), (String)respImg.get("url"),(Integer) respImg.get("width"),(Integer) respImg.get("height"));
			crudImage.create(cover);
			song.setImages(cover);
		}
		//song.setWritters();
		return ResponseEntity.ok(new MessageResponse(true,"success",crudSong.create(song)));
	}
}
