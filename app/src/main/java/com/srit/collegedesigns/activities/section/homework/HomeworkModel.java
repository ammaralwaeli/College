package com.srit.collegedesigns.activities.section.homework;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class HomeworkModel implements Serializable {
	private int id;
	private String homeWorkTitle;
	private String homeWorkText;
	private String img;
	private int specialization;
	private int collegeId;
	private int addedByUser;
	private String addedAt;
	private int deleted;

	public int getId(){
		return id;
	}

	public String getHomeWorkTitle(){
		return homeWorkTitle;
	}

	public String getHomeWorkText(){
		return homeWorkText;
	}

	public String getImg(){
		return img;
	}

	public int getSpecialization(){
		return specialization;
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

	@NotNull
	@Override
 	public String toString(){
		return 
			"HomeworkModel{" + 
			"id = '" + id + '\'' + 
			",homeWorkTitle = '" + homeWorkTitle + '\'' + 
			",homeWorkText = '" + homeWorkText + '\'' + 
			",img = '" + img + '\'' + 
			",specialization = '" + specialization + '\'' + 
			",collegeId = '" + collegeId + '\'' + 
			",addedByUser = '" + addedByUser + '\'' + 
			",addedAt = '" + addedAt + '\'' + 
			",deleted = '" + deleted + '\'' + 
			"}";
		}
}