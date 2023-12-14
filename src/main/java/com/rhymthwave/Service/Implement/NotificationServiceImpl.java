package com.rhymthwave.Service.Implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymthwave.Service.NotiService;
import com.rhymthwave.entity.Notification;

@Service
public class NotificationServiceImpl implements NotiService{

	@Override
	public Notification notifyLatest() {
		return null;
	}
}
