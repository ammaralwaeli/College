package com.srit.collegedesigns.activities.section.notification;

import androidx.lifecycle.MutableLiveData;

import com.srit.collegedesigns.api.ApiResponse;
import com.srit.collegedesigns.api.ApiServices;
import com.srit.collegedesigns.helpers.RetrofitService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationRepository {


    private static NotificationRepository instance;
    public static NotificationRepository getInstance(){
        if(instance==null){
            instance= new NotificationRepository();
        }
        return instance;
    }

    private ApiServices apiServices;
    private NotificationRepository(){
        apiServices= RetrofitService.cteateServiceWithAuth(ApiServices.class);
    }

    private MutableLiveData<ApiResponse> data;
     MutableLiveData<ApiResponse> getStageNotifications(){

        data = new MutableLiveData<>();

        apiServices.getStageNotifications().enqueue(new Callback<List<NotificationModel>>() {

            @Override
            public void onResponse(@NotNull Call<List<NotificationModel>> call, @NotNull Response<List<NotificationModel>> response) {
                if(response.isSuccessful()){
                    data.postValue(new ApiResponse(response.body()));

                }else {
                    data.postValue(new ApiResponse(response.code()+"  "+response.message()));
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<NotificationModel>> call, @NotNull Throwable t) {
                data.postValue(new ApiResponse(t.getMessage()));
            }
        });
        return data;
    }

     MutableLiveData<ApiResponse> getStudentNotifications(){




        data = new MutableLiveData<>();

        apiServices.getStudentNotifications().enqueue(new Callback<List<NotificationModel>>() {

            @Override
            public void onResponse(@NotNull Call<List<NotificationModel>> call, @NotNull Response<List<NotificationModel>> response) {
                if(response.isSuccessful()){
                    data.postValue(new ApiResponse(response.body()));

                }else {
                    data.postValue(new ApiResponse(response.code()+"  "+response.message()));
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<NotificationModel>> call, @NotNull Throwable t) {
                data.postValue(new ApiResponse(t.getMessage()));
            }
        });
        return data;
    }
}
