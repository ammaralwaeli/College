package com.srit.collegedesigns.api;

import com.google.gson.JsonObject;
import com.srit.collegedesigns.activities.home.advertisement.AdvertisemrntModel;
import com.srit.collegedesigns.activities.section.notification.NotificationModel;
import com.srit.collegedesigns.activities.section.schedules.daySchedule.DayModel;
import com.srit.collegedesigns.activities.section.schedules.ScheduleModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServices {
    @POST("/public/api/studentLogin")
    Call<JsonObject> login(@Query("password")String password,
                                  @Query("username")String username);



    @GET("/public/api/getAdvertisements")
    Call<List<AdvertisemrntModel>> getAdvertisements();

    @GET("/public/api/getStageNotifications")
    Call<List<NotificationModel>> getStageNotifications();

    @GET("/public/api/getStudentNotifications")
    Call<List<NotificationModel>> getStudentNotifications();

    @GET("/public/api/getSchedule")
    Call<List<DayModel>> getSchedule();

    @GET("/public/api/getDaySchedule")
    Call<List<ScheduleModel>> getSchedules(@Query("id") int id);

}
