package com.srit.collegedesigns.api;

import com.google.gson.JsonObject;
import com.srit.collegedesigns.home.advertisement.AdvertisemrntModel;
import com.srit.collegedesigns.section.notification.NotificationModel;

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

}
