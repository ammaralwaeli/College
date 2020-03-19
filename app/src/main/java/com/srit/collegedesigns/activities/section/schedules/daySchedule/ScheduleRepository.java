package com.srit.collegedesigns.activities.section.schedules.daySchedule;
import androidx.lifecycle.MutableLiveData;

import com.srit.collegedesigns.api.ApiResponse;
import com.srit.collegedesigns.api.ApiServices;
import com.srit.collegedesigns.helpers.RetrofitService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleRepository {

    private static ScheduleRepository instance;
    public static ScheduleRepository getInstance(){
        if(instance==null){
            instance= new ScheduleRepository();
        }
        return instance;
    }

    private ApiServices apiServices;
    private ScheduleRepository(){
        apiServices= RetrofitService.cteateServiceWithAuth(ApiServices.class);
    }

    private MutableLiveData<ApiResponse> data;
    MutableLiveData<ApiResponse> getSchedules(){

        data = new MutableLiveData<>();

        apiServices.getSchedule().enqueue(new Callback<List<DayModel>>() {

            @Override
            public void onResponse(@NotNull Call<List<DayModel>> call, @NotNull Response<List<DayModel>> response) {
                if(response.isSuccessful()){
                    data.postValue(new ApiResponse(response.body()));

                }else {
                    data.postValue(new ApiResponse(response.code()+"  "+response.message()));
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<DayModel>> call, @NotNull Throwable t) {
                data.postValue(new ApiResponse(t.getMessage()));
            }
        });
        return data;
    }
}
