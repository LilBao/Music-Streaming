package com.rhymthwave.ServiceAdmin;

public interface INotification<T> {
	
	void sendNotification(T noti, String urlImage);

	void sendEmailBan(String email);

	void sendEmailWarring(String email);

}
