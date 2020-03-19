package com.srit.collegedesigns.activities.home.advertisement;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.srit.collegedesigns.api.ApiResponse;

public class AdvertisementViewModel extends ViewModel {


    private MutableLiveData<ApiResponse> mutableLiveData;
    private AdvertisementRepository advertisementRepository;

    public void init() {
        if (mutableLiveData != null) {
            return;
        }
        advertisementRepository = AdvertisementRepository.getInstance();
        mutableLiveData = advertisementRepository.getAdvertisements();
    }

    public LiveData<ApiResponse> getAdvertisementRepository() {
        return mutableLiveData;
    }
}
