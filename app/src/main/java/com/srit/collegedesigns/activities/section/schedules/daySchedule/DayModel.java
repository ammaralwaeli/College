package com.srit.collegedesigns.activities.section.schedules.daySchedule;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class DayModel implements Serializable {
	private String day;
	private int id;

	public String getDay(){
		return day;
	}

	public int getId(){
		return id;
	}

	@NotNull
	@Override
 	public String toString(){
		return 
			"DayModel{" + 
			"day = '" + day + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}