package com.rhymthwave.API;

import org.springframework.web.bind.annotation.RestController;

import com.rhymthwave.Service.NotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class NotificationREST {
	
	private final NotificationService notifySer;
}
