package com.srit.collegedesigns.section.notification;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.srit.collegedesigns.api.ApiServices;
import com.srit.collegedesigns.helpers.RetrofitService;

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
    public NotificationRepository(){
        apiServices= RetrofitService.cteateServiceWithAuth(ApiServices.class);
    }

    private MutableLiveData<List<NotificationModel>> data;
    public MutableLiveData<List<NotificationModel>> getNotifications(){

        data = new MutableLiveData<>();

        apiServices.getStageNotifications().enqueue(new Callback<List<NotificationModel>>() {

            @Override
            public void onResponse(Call<List<NotificationModel>> call, Response<List<NotificationModel>> response) {
                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else {

                    Log.e("notificationError",response.code()+"  "+response.message());
                }
            }

            @Override
            public void onFailure(Call<List<NotificationModel>> call, Throwable t) {
                Log.e("TNotificationError",t.getMessage());
                data.setValue(null);
            }
        });
        return data;
    }
}
