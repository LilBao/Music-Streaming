package com.rhymthwave.API;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.MonitorService;
import com.rhymthwave.entity.Monitor;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class MonitorREST {
	private final CRUD<Monitor, Long> crudMonitor;
	
	private final MonitorService monitorSer;
	
	@GetMapping("/api/v1/monitor/{id}")
	public ResponseEntity<MessageResponse> findMonitorId(@PathVariable("id") Long id){
		return ResponseEntity.ok(new MessageResponse(true,"success",crudMonitor.findOne(id)));
	}
	
	@PostMapping("/api/v1/monitor")
	public ResponseEntity<MessageResponse> createMonitor(@RequestBody Monitor monitor){
		if(monitorSer.checkExist(monitor.getRecording(), monitor.getAccount()) == null) {
			return ResponseEntity.ok(new MessageResponse(true,"success",crudMonitor.create(monitor)));
		}else {
			return ResponseEntity.ok(new MessageResponse(false,"success","Existed"));
		}
	}
	
	@DeleteMapping("/api/v1/monitor/{id}")
	public ResponseEntity<MessageResponse> deleteMonitor(@PathVariable("id") Long id){
		return ResponseEntity.ok(new MessageResponse(true,"success",crudMonitor.delete(id)));
	}
}
