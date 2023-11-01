package com.rhymthwave.ServiceAdmin;

public interface INotification<T> {
	
	void sendNotification(T noti, String urlImage);
}
