package com.srit.collegedesigns.login;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.srit.collegedesigns.api.ApiServices;
import com.srit.collegedesigns.helpers.RetrofitService;

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
    public LoginRepository(){
        apiServices= RetrofitService.cteateService(ApiServices.class);
    }

    private MutableLiveData<JsonObject> data;
    public MutableLiveData<JsonObject> login(String password, String username){

        data = new MutableLiveData<>();

        apiServices.login(password, username).enqueue(new Callback<JsonObject>() {

            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else {
                    Log.e("loginError",response.code()+"  "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("loginError",t.getMessage());
                data.setValue(null);
            }
        });
        return data;
    }
}
