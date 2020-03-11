package com.srit.collegedesigns.home.advertisement;

import java.io.Serializable;

public class AdvertisemrntModel implements Serializable {
	private int id;
	private String advText;
	private int collegeId;
	private String addedAt;
	private int deleted;
	private int addedByUser;
	private String type;

	public int getId() {
		return id;
	}

	public String getAdvText() {
		return advText;
	}

	public int getCollegeId() {
		return collegeId;
	}

	public String getAddedAt() {
		return addedAt;
	}

	public int getDeleted() {
		return deleted;
	}

	public int getAddedByUser() {
		return addedByUser;
	}

	public String getType() {
		return type;
	}

	@Override
 	public String toString(){
		return 
			"AdvertisemrntModel{" + 
			"id = '" + id + '\'' + 
			",advText = '" + advText + '\'' + 
			",collegeId = '" + collegeId + '\'' + 
			",addedAt = '" + addedAt + '\'' + 
			",deleted = '" + deleted + '\'' + 
			",addedByUser = '" + addedByUser + '\'' + 
			",type = '" + type + '\'' + 
			"}";
		}
}