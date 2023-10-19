package com.rhymthwave.API;

import java.util.Map;

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
import com.rhymthwave.Utilities.GetHostByRequest;
import com.rhymthwave.entity.Account;
import com.rhymthwave.entity.Image;
import com.rhymthwave.entity.Podcast;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class PobcastREST {

	private final CRUD<Podcast, Long> crudPobcast;
	
	private final CRUD<Image, String> crudImage;
	
	private final CloudinaryService cloudinarySer;
	
	private final ImageService imageSer;
	
	private final CRUD<Account, String> crudAccount;
	
	private final GetHostByRequest host;
	
	@GetMapping("/api/v1/pobcast")
	public ResponseEntity<MessageResponse> findAllPobcast(){
		return ResponseEntity.ok(new MessageResponse(true,"successs",crudPobcast.findAll()));
	}
	
	@PostMapping(value="/api/v1/pobcast", consumes = { "multipart/form-data" })
	public ResponseEntity<MessageResponse> createPobcast(@ModelAttribute Podcast pobcast,HttpServletRequest req,
													@PathParam("coverImg") MultipartFile coverImg){
		String owner = host.getEmailByRequest(req);
		Account account = crudAccount.findOne(owner);
		if(coverImg != null) {
			Map<?,?> respImage = cloudinarySer.UploadResizeImage(coverImg,"ImagePobcast",account.getUsername(), 250, 250);
			Image image = imageSer.getEntity((String)respImage.get("asset_id"),(String)respImage.get("url"), (Integer)respImage.get("weight"), (Integer)respImage.get("height"));
			pobcast.setImage(crudImage.create(image));
		}
		pobcast.setAccount(account);
		return ResponseEntity.ok(new MessageResponse(true,"successs",crudPobcast.findAll()));
	}
	
}
