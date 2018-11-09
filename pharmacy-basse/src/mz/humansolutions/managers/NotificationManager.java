package mz.humansolutions.managers;

import java.io.IOException;


public interface NotificationManager {
	
	void sendSms(String phoneNumber, String message) throws IOException;

	void sendSmsNotificationSislog(String phoneNumber, String message) throws IOException;


}
