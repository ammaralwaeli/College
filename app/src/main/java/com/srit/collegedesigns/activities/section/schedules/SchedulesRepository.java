package com.srit.collegedesigns.activities.section.schedules;
import androidx.lifecycle.MutableLiveData;

import com.srit.collegedesigns.api.ApiResponse;
import com.srit.collegedesigns.api.ApiServices;
import com.srit.collegedesigns.helpers.RetrofitService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SchedulesRepository {

    private static SchedulesRepository instance;
    public static SchedulesRepository getInstance(){
        if(instance==null){
            instance= new SchedulesRepository();
        }
        return instance;
    }

    private ApiServices apiServices;
    private SchedulesRepository(){
        apiServices= RetrofitService.cteateServiceWithAuth(ApiServices.class);
    }

    private MutableLiveData<ApiResponse> data;
    MutableLiveData<ApiResponse> getSchedules(int id){

        data = new MutableLiveData<>();

        apiServices.getSchedules(id).enqueue(new Callback<List<ScheduleModel>>() {

            @Override
            public void onResponse(@NotNull Call<List<ScheduleModel>> call, @NotNull Response<List<ScheduleModel>> response) {
                if(response.isSuccessful()){
                    data.postValue(new ApiResponse(response.body()));

                }else {
                    data.postValue(new ApiResponse(response.code()+"  "+response.message()));
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<ScheduleModel>> call, @NotNull Throwable t) {
                data.postValue(new ApiResponse(t.getMessage()));
            }
        });
        return data;
    }
}
