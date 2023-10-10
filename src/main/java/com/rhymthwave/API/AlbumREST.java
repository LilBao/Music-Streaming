package com.rhymthwave.API;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.Service.AlbumService;
import com.rhymthwave.Service.ArtistService;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.CloudinaryService;
import com.rhymthwave.Service.ImageService;
import com.rhymthwave.entity.Album;
import com.rhymthwave.entity.Image;

@Controller
@CrossOrigin("http://127.0.0.1:5500")
public class AlbumREST {
	@Autowired
	ArtistService artistSer;
	
	@Autowired
	AlbumService albumSer;
	
	@Autowired
	CRUD<Album, Integer> crudAlbum;
	
	@Autowired
	CloudinaryService cloudinary;
	
	@Autowired
	CRUD<Image, String> crudImage;
	
	@Autowired
	ImageService imgSer;
	
	
	@GetMapping("/api/v1/album")
	public ResponseEntity<MessageResponse> getAllAlbum(){
		return ResponseEntity.ok(new MessageResponse(true, "success", crudAlbum.findAll()));
	}
	
	@GetMapping("/api/v1/album/{id}")
	public ResponseEntity<MessageResponse> getAllAlbumByID(@PathVariable("id") Integer id){
		return ResponseEntity.ok(new MessageResponse(true, "success", crudAlbum.findOne(id)));
	}
	
	@PostMapping(value="/api/v1/album",consumes = { "multipart/form-data" })
	public ResponseEntity<MessageResponse> createAlbum(@ModelAttribute Album album, @RequestParam("coverImg") MultipartFile coverImg){
		if(!coverImg.isEmpty()) {
			Map<String, Object> respImg = cloudinary.Upload(coverImg, "CoverImage", "MCK");
			Image cover = imgSer.getEntity((String) respImg.get("asset_id"), (String)respImg.get("url"),(Integer) respImg.get("width"),(Integer) respImg.get("height"));
			crudImage.create(cover);
			album.setImages(cover);
		}
		album.setArtistId(artistSer.findByEmail("mck@gmail.com"));
		return ResponseEntity.ok(new MessageResponse(true,"success",crudAlbum.create(album)));
	}
	
	@GetMapping("/api/v1/album/up-coming")
	public ResponseEntity<MessageResponse> albumUpcoming(){
		return ResponseEntity.ok(new MessageResponse(true,"success",albumSer.findAlbumNotRecord("mck@gmail.com")));
	}
	
}
