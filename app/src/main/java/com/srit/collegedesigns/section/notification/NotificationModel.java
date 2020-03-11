package com.srit.collegedesigns.section.notification;

import java.io.Serializable;

public class NotificationModel implements Serializable {
	private int id;
	private String notificationTitle;
	private String notificationText;
	private String section;
	private String stage;
	private String division;
	private int collegeId;
	private int addedByUser;
	private String addedAt;
	private int deleted;
	private String type;

	public int getId(){
		return id;
	}

	public String getNotificationTitle(){
		return notificationTitle;
	}

	public String getNotificationText(){
		return notificationText;
	}

	public String getSection(){
		return section;
	}

	public String getStage(){
		return stage;
	}

	public String getDivision(){
		return division;
	}

	public int getCollegeId(){
		return collegeId;
	}

	public int getAddedByUser(){
		return addedByUser;
	}

	public String getAddedAt(){
		return addedAt;
	}

	public int getDeleted(){
		return deleted;
	}

	public String getType(){
		return type;
	}

	@Override
 	public String toString(){
		return 
			"NotificationModel{" + 
			"id = '" + id + '\'' + 
			",notificationTitle = '" + notificationTitle + '\'' + 
			",notificationText = '" + notificationText + '\'' + 
			",section = '" + section + '\'' + 
			",stage = '" + stage + '\'' + 
			",division = '" + division + '\'' + 
			",collegeId = '" + collegeId + '\'' + 
			",addedByUser = '" + addedByUser + '\'' + 
			",addedAt = '" + addedAt + '\'' + 
			",deleted = '" + deleted + '\'' + 
			",type = '" + type + '\'' + 
			"}";
		}
}