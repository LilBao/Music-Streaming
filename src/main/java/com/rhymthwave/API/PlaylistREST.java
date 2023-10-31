//package com.rhymthwave.API;
//
//import java.util.Map;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.rhymthwave.DTO.MessageResponse;
//import com.rhymthwave.Service.AccountService;
//import com.rhymthwave.Service.CRUD;
//import com.rhymthwave.Service.CloudinaryService;
//import com.rhymthwave.Service.ImageService;
//import com.rhymthwave.Service.PlaylistService;
//import com.rhymthwave.Utilities.GetHostByRequest;
//import com.rhymthwave.entity.Account;
//import com.rhymthwave.entity.Image;
//import com.rhymthwave.entity.Playlist;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.websocket.server.PathParam;
//import lombok.RequiredArgsConstructor;
//
//@RestController
//@CrossOrigin("*")
//@RequiredArgsConstructor
//public class PlaylistREST {
//	private final PlaylistService playlistSer;
//
//	private final CRUD<Playlist, Long> crudPlaylist;
//
//	private final CRUD<Image, String> crudImage;
//	
//	private final CRUD<Account,Long> crudAccount;
//
//	private final ImageService imgSer;
//
//	private final AccountService accountSer;
//
//	private final CloudinaryService cloudinary;
//
//	private final GetHostByRequest host;
//
//	@GetMapping("/api/v1/playlist")
//	public ResponseEntity<MessageResponse> getAllPlaylist() {
//		return ResponseEntity.ok(new MessageResponse(true, "success", crudPlaylist.findAll()));
//	}
//
//	@GetMapping("/api/v1/playlist/{id}")
//	public ResponseEntity<MessageResponse> getPlaylist(@PathVariable("id") Long id) {
//		return ResponseEntity.ok(new MessageResponse(true, "success", crudPlaylist.findOne(id)));
//	}
//
//	@GetMapping("/api/v1/my-playlist")
//	public ResponseEntity<MessageResponse> getMyPlaylist(HttpServletRequest req) {
//		String owner = host.getEmailByRequest(req);
//		return ResponseEntity.ok(new MessageResponse(true, "success", playlistSer.findMyPlaylist(owner)));
//	}
//
//	@PostMapping(value="/api/v1/playlist",consumes = { "multipart/form-data" })
//	public ResponseEntity<MessageResponse> createPlaylist(@ModelAttribute Playlist playlist ,HttpServletRequest req,@PathParam("coverImg") MultipartFile coverImg){
//		String owner = host.getEmailByRequest(req);
////		Account accont = crudAccount.findOne(owner);
////		if(coverImg!=null) {
////			Map<?,?> respImg = cloudinary.Upload(coverImg, , owner)
////			Image image = imgSer.getEntity(null);
////		}
//		
//		return ResponseEntity.ok(new MessageResponse(true,"success",playlistSer.findMyPlaylist(owner)));
//	}
//
//	@PutMapping("/api/v1/playlist")
//	public ResponseEntity<MessageResponse> updatePlaylist(@RequestBody Playlist playlist) {
//		return ResponseEntity.ok(new MessageResponse(true, "success", crudPlaylist.update(playlist)));
//	}
//
//	@PutMapping(value = "/api/v1/playlist-image", consumes = { "multipart/form-data" })
//	public ResponseEntity<MessageResponse> updateImagePlaylist(@ModelAttribute Playlist playlist,
//			HttpServletRequest req) {
//
//		return ResponseEntity.ok(new MessageResponse(true, "success", crudPlaylist.update(playlist)));
//	}
//
//}
