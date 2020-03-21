package com.srit.collegedesigns.activities.section.library;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class LibraryModel implements Serializable {
	private int id;
	private String bookTitle;
	private String url;
	private String description;
	private int collegeId;
	private int specialization;
	private int deleted;
	private String addedAt;

	public int getId(){
		return id;
	}

	public String getBookTitle(){
		return bookTitle;
	}

	public String getUrl(){
		return url;
	}

	public String getDescription(){
		return description;
	}

	public int getCollegeId(){
		return collegeId;
	}

	public int getSpecialization(){
		return specialization;
	}

	public int getDeleted(){
		return deleted;
	}

	public String getAddedAt(){
		return addedAt;
	}

	@NotNull
	@Override
 	public String toString(){
		return 
			"LibraryModel{" + 
			"id = '" + id + '\'' + 
			",bookTitle = '" + bookTitle + '\'' + 
			",url = '" + url + '\'' + 
			",description = '" + description + '\'' + 
			",collegeId = '" + collegeId + '\'' + 
			",specialization = '" + specialization + '\'' + 
			",deleted = '" + deleted + '\'' + 
			",addedAt = '" + addedAt + '\'' + 
			"}";
		}
}