package com.srit.collegedesigns.activities.section.homework;
import androidx.lifecycle.MutableLiveData;

import com.srit.collegedesigns.activities.section.schedules.ScheduleModel;
import com.srit.collegedesigns.api.ApiResponse;
import com.srit.collegedesigns.api.ApiServices;
import com.srit.collegedesigns.helpers.RetrofitService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeworkRepository {

    private static HomeworkRepository instance;
    public static HomeworkRepository getInstance(){
        if(instance==null){
            instance= new HomeworkRepository();
        }
        return instance;
    }

    private ApiServices apiServices;
    private HomeworkRepository(){
        apiServices= RetrofitService.cteateServiceWithAuth(ApiServices.class);
    }

    private MutableLiveData<ApiResponse> data;
    MutableLiveData<ApiResponse> getHomework(){

        data = new MutableLiveData<>();

        apiServices.getStudentHomeWork().enqueue(new Callback<List<HomeworkModel>>() {

            @Override
            public void onResponse(@NotNull Call<List<HomeworkModel>> call, @NotNull Response<List<HomeworkModel>> response) {
                if(response.isSuccessful()){
                    data.postValue(new ApiResponse(response.body()));

                }else {
                    data.postValue(new ApiResponse(response.code()+"  "+response.message()));
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<HomeworkModel>> call, @NotNull Throwable t) {
                data.postValue(new ApiResponse(t.getMessage()));
            }
        });
        return data;
    }
}
