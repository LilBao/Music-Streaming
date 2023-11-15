package com.rhymthwave.API;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.CloudinaryService;
import com.rhymthwave.Service.ImageService;
import com.rhymthwave.Service.Implement.AccountServiceImpl;
import com.rhymthwave.Utilities.GetHostByRequest;
import com.rhymthwave.entity.Account;
import com.rhymthwave.entity.Artist;
import com.rhymthwave.entity.Image;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;


@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class AccountREST {
	private final CRUD<Account,String> crudAccount;
	
	private final CRUD<Image,String> crudImage;
	
	private final AccountServiceImpl accountService;
	
	private final CloudinaryService cloudinary;
	
	private final ImageService imgSer;
	
	private final GetHostByRequest host;
	
	@GetMapping("/api/v1/account")
	public ResponseEntity<MessageResponse> findAccountByJWT(HttpServletRequest req){
		String owner = host.getEmailByRequest(req);
		return ResponseEntity.ok(new MessageResponse(true, "success",crudAccount.findOne(owner)));
	}

	@GetMapping("/api/v1/account/{id}")
	public ResponseEntity<MessageResponse> findAccount(@PathVariable("id") String id){
		return ResponseEntity.ok(new MessageResponse(true, "success",crudAccount.findOne(id)));
	}
	
	@PutMapping(value="/api/v1/account")
	public ResponseEntity<MessageResponse> updateProfile(@RequestBody Account account){
		return ResponseEntity.ok(new MessageResponse(true, "succeess", crudAccount.update(account)));
	}
		
	@PutMapping(value="/api/v1/account-image",consumes = { "multipart/form-data" })
	public ResponseEntity<MessageResponse> updateImageArtist(HttpServletRequest req,@PathParam("avatar") MultipartFile avatar) {
		String owner =host.getEmailByRequest(req);
		Account account =crudAccount.findOne(owner);
		Image imgOld = account.getImage();
		if(avatar !=null) {
			Map<String,Object> respAvatar = cloudinary.Upload(avatar,"ProfilePicture",account.getUsername());
			Image imgAvatar = imgSer.getEntity(respAvatar);
			crudImage.create(imgAvatar);
			account.setImage(imgAvatar);
			cloudinary.deleteFile(imgOld.getPublicId());
		}	
		return ResponseEntity.ok(new MessageResponse(true, "succeess", crudAccount.update(account)));
	}
}
