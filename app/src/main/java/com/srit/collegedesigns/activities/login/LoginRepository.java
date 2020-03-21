package com.srit.collegedesigns.activities.login;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.srit.collegedesigns.api.ApiServices;
import com.srit.collegedesigns.helpers.RetrofitService;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginRepository {

    private static LoginRepository instance;
    public static LoginRepository getInstance(){
        if(instance==null){
            instance= new LoginRepository();
        }
        return instance;
    }

    private ApiServices apiServices;
    private LoginRepository(){
        apiServices= RetrofitService.cteateService(ApiServices.class);
    }

    private MutableLiveData<MyResponse> data;
    MutableLiveData<MyResponse> login(String password, String username){

        data = new MutableLiveData<>();

        apiServices.login(password, username).enqueue(new Callback<JsonObject>() {

            @Override
            public void onResponse(@NotNull Call<JsonObject> call, @NotNull Response<JsonObject> response) {
                if(response.isSuccessful()){
                    data.setValue(new MyResponse(response.body()));
                }else {
                    data.setValue(new MyResponse(response.code()+"  "+response.message()));
                    Log.e("loginError",response.code()+"  "+response.message());
                }
            }

            @Override
            public void onFailure(@NotNull Call<JsonObject> call, @NotNull Throwable t) {
                Log.e("loginError", Objects.requireNonNull(t.getMessage()));
                data.setValue(new MyResponse(Objects.requireNonNull(t.getMessage())));
            }
        });
        return data;
    }
}
