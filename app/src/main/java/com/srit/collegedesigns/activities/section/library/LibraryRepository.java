package com.srit.collegedesigns.activities.section.library;
import androidx.lifecycle.MutableLiveData;

import com.srit.collegedesigns.activities.section.homework.HomeworkModel;
import com.srit.collegedesigns.api.ApiResponse;
import com.srit.collegedesigns.api.ApiServices;
import com.srit.collegedesigns.helpers.RetrofitService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LibraryRepository {

    private static LibraryRepository instance;
    public static LibraryRepository getInstance(){
        if(instance==null){
            instance= new LibraryRepository();
        }
        return instance;
    }

    private ApiServices apiServices;
    private LibraryRepository(){
        apiServices= RetrofitService.cteateServiceWithAuth(ApiServices.class);
    }

    private MutableLiveData<ApiResponse> data;
    MutableLiveData<ApiResponse> getLibrary(){

        data = new MutableLiveData<>();

        apiServices.getLibrary().enqueue(new Callback<List<LibraryModel>>() {

            @Override
            public void onResponse(@NotNull Call<List<LibraryModel>> call, @NotNull Response<List<LibraryModel>> response) {
                if(response.isSuccessful()){
                    data.postValue(new ApiResponse(response.body()));

                }else {
                    data.postValue(new ApiResponse(response.code()+"  "+response.message()));
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<LibraryModel>> call, @NotNull Throwable t) {
                data.postValue(new ApiResponse(t.getMessage()));
            }
        });
        return data;
    }
}
