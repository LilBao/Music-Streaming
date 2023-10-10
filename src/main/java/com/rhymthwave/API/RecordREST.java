package com.rhymthwave.API;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.CloudinaryService;
import com.rhymthwave.Service.RecordService;
import com.rhymthwave.entity.Image;
import com.rhymthwave.entity.Recording;
import com.rhymthwave.entity.Song;

import jakarta.websocket.server.PathParam;

@RestController
@CrossOrigin("*")
public class RecordREST {
	@Autowired
	CRUD<Image, String> crudImg;

	@Autowired
	CRUD<Recording, Integer> crudRecord;

	@Autowired
	CloudinaryService cloudinary;

	@Autowired
	RecordService recordSer;

	@GetMapping("/api/v1/record")
	public ResponseEntity<MessageResponse> getAllRecord() {
		return ResponseEntity.ok(new MessageResponse(true, "success", crudRecord.findAll()));
	}

	@GetMapping("/api/v1/record/{id}")
	public ResponseEntity<MessageResponse> getOneRecord(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(new MessageResponse(true, "success", crudRecord.findOne(id)));
	}
	
	@GetMapping("/api/v1/my-record")
	public ResponseEntity<MessageResponse> getMyRecord() {
		return ResponseEntity.ok(new MessageResponse(true, "success", recordSer.findRecordByCreater("mck@gmail.com")));
	}
	
	@GetMapping("/api/v1/my-record-not-raw")
	public ResponseEntity<MessageResponse> getOneRecord() {
		return ResponseEntity.ok(new MessageResponse(true, "success", recordSer.findRawRecordByCreater("mck@gmail.com")));
	}

	@PostMapping(value = "/api/v1/record", consumes = { "multipart/form-data" })
	public ResponseEntity<MessageResponse> createRecord(@ModelAttribute Recording record,
			@PathParam("fileRecord") MultipartFile fileRecord, @PathParam("fileLyrics") MultipartFile fileLyrics) {
		
		Map<String, Object> respRecord = cloudinary.Upload(fileRecord, "Records", "MCK");
		if (fileLyrics.getSize() > 0) {
			Map<String, Object> respLyrics = cloudinary.Upload(fileLyrics, "Lyrics", "MCK");
			record.setLyricsUrl((String) respLyrics.get("url"));
		}
		record.setAudioFileUrl((String) respRecord.get("url"));
		record.setEmailCreate("mck@gmail.com");
		return ResponseEntity.ok(new MessageResponse(true, "success", crudRecord.create(record)));
	}

	@PutMapping("/api/v1/record")
	public ResponseEntity<MessageResponse> createUpcoming(@RequestBody Recording record) {
		return ResponseEntity.ok(new MessageResponse(true, "success", crudRecord.update(record)));
	}
}
