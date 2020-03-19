package com.srit.collegedesigns.activities.home.advertisement;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.srit.collegedesigns.api.ApiResponse;
import com.srit.collegedesigns.api.ApiServices;
import com.srit.collegedesigns.helpers.RetrofitService;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdvertisementRepository {

/*
*

    public LiveData<ApiResponse> getPosts() {
        final MutableLiveData<ApiResponse> apiResponse = new MutableLiveData<>();
        Call<List<ApiModel>> call = endpoints.getDataFromApi();
        call.enqueue(new Callback<List<ApiModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<ApiModel>> call, @NonNull Response<List<ApiModel>> response) {
                if (response.isSuccessful()) {
                    apiResponse.postValue(new ApiResponse(response.body()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ApiModel>> call, @NonNull Throwable t) {
                apiResponse.postValue(new ApiResponse(t));
            }
        });

        return apiResponse;
    }
* */
    private static AdvertisementRepository instance;
    public static AdvertisementRepository getInstance(){
        if(instance==null){
            instance= new AdvertisementRepository();
        }
        return instance;
    }

    private ApiServices apiServices;
    private AdvertisementRepository(){
        apiServices= RetrofitService.cteateServiceWithAuth(ApiServices.class);
    }

    private MutableLiveData<ApiResponse> data;
    MutableLiveData<ApiResponse> getAdvertisements(){

        data = new MutableLiveData<>();

        apiServices.getAdvertisements().enqueue(new Callback<List<AdvertisemrntModel>>() {

            @Override
            public void onResponse(@NotNull Call<List<AdvertisemrntModel>> call, @NotNull Response<List<AdvertisemrntModel>> response) {
                if(response.isSuccessful()){
                    data.postValue(new ApiResponse(response.body()));

                }else {
                    data.postValue(new ApiResponse(response.code()+"  "+response.message()));
                    Log.e("loginError",response.code()+"  "+response.message());
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<AdvertisemrntModel>> call, @NotNull Throwable t) {
                data.postValue(new ApiResponse(t.getMessage()));
                Log.e("ThrowableloginError", Objects.requireNonNull(t.getMessage()));

            }
        });
        return data;
    }
}
