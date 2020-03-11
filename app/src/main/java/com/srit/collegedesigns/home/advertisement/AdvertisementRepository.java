package com.srit.collegedesigns.home.advertisement;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.srit.collegedesigns.api.ApiServices;
import com.srit.collegedesigns.helpers.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdvertisementRepository {

    private static AdvertisementRepository instance;
    public static AdvertisementRepository getInstance(){
        if(instance==null){
            instance= new AdvertisementRepository();
        }
        return instance;
    }

    private ApiServices apiServices;
    public AdvertisementRepository(){
        apiServices= RetrofitService.cteateServiceWithAuth(ApiServices.class);
    }

    private MutableLiveData<List<AdvertisemrntModel>> data;
    public MutableLiveData<List<AdvertisemrntModel>> getAdvertisements(){

        data = new MutableLiveData<>();

        apiServices.getAdvertisements().enqueue(new Callback<List<AdvertisemrntModel>>() {

            @Override
            public void onResponse(Call<List<AdvertisemrntModel>> call, Response<List<AdvertisemrntModel>> response) {
                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else {

                    Log.e("loginError",response.code()+"  "+response.message());
                }
            }

            @Override
            public void onFailure(Call<List<AdvertisemrntModel>> call, Throwable t) {
                Log.e("ThrowableloginError",t.getMessage());
                data.setValue(null);
            }
        });
        return data;
    }
}
