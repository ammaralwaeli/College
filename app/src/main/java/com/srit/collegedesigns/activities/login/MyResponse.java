package com.srit.collegedesigns.activities.login;

import com.google.gson.JsonObject;


public class MyResponse {

    private JsonObject posts;
    private String error;

    MyResponse(JsonObject posts) {
        this.posts = posts;
        this.error = null;
    }

    MyResponse(String error) {
        this.error = error;
        this.posts = null;
    }

    public JsonObject getPosts() {
        return posts;
    }

    String getError() {
        return error;
    }
}
