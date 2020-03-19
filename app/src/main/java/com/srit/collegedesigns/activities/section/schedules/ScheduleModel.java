package com.srit.collegedesigns.activities.section.schedules;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class ScheduleModel implements Serializable {


	private String lesson,hour;
	private int id;

	public String getLesson() {
		return lesson;
	}

	public String getHour() {
		return hour;
	}

	public int getId() {
		return id;
	}


	@NotNull
	@Override
	public String toString() {
		return "ScheduleModel{" +
				"lesson='" + lesson + '\'' +
				", hour='" + hour + '\'' +
				", id=" + id +
				'}';
	}
}