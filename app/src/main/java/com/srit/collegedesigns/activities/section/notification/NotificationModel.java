package com.srit.collegedesigns.activities.section.notification;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class NotificationModel implements Serializable {

	private String notificationTitle;
	private String notificationText;

	public String getNotificationTitle(){
		return notificationTitle;
	}

	public String getNotificationText(){
		return notificationText;
	}


	@NotNull
	@Override
	public String toString() {
		return "NotificationModel{" +
				"notificationTitle='" + notificationTitle + '\'' +
				", notificationText='" + notificationText + '\'' +
				'}';
	}
}