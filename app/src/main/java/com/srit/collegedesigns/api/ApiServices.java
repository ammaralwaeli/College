package com.srit.collegedesigns.api;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServices {
    @POST("/public/api/studentLogin")
    public Call<JsonObject> login(@Query("password")String password,
                                  @Query("username")String username);
}
