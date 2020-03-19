package com.srit.collegedesigns.activities.home;

import com.google.gson.Gson;
import com.srit.collegedesigns.helpers.RetrofitService;
import com.srit.collegedesigns.helpers.SharedPrefHelper;
import com.srit.collegedesigns.helpers.ViewExtensionsKt;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class UserModel implements Serializable {
	private int userId;
	private int collegeId;
	private int specialization;
	private String section;
	private String stage;
	private String division;
	private String studentName;
	private int exp;


	private static UserModel instance;

	public static UserModel getInstance(){
		if(instance==null){
			instance=fromToken(SharedPrefHelper.getInstance().getAccessToken());
		}
		return instance;
	}

	private static UserModel fromToken(String accessToken) {
		String body = ViewExtensionsKt.decodeToken(accessToken);
		Gson gson = RetrofitService.getGson();
		return gson.fromJson(body, UserModel.class);

	}

	public int getUserId(){
		return userId;
	}

	public int getCollegeId(){
		return collegeId;
	}

	public int getSpecialization(){
		return specialization;
	}

	public String getSection(){
		return section;
	}

	String getStage(){
		return stage;
	}

	String getDivision(){
		return division;
	}

	String getStudentName(){
		return studentName;
	}

	public int getExp(){
		return exp;
	}

	@NotNull
	@Override
 	public String toString(){
		return 
			"UserModel{" + 
			"userId = '" + userId + '\'' + 
			",collegeId = '" + collegeId + '\'' + 
			",specialization = '" + specialization + '\'' + 
			",section = '" + section + '\'' + 
			",stage = '" + stage + '\'' + 
			",division = '" + division + '\'' + 
			",studentName = '" + studentName + '\'' + 
			",exp = '" + exp + '\'' + 
			"}";
		}
}