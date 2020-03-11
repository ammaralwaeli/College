package com.srit.collegedesigns.home.advertisement;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class AdvertisementViewModel extends ViewModel {


    private MutableLiveData<List<AdvertisemrntModel>> mutableLiveData;
    private AdvertisementRepository advertisementRepository;

    public void init() {
        if (mutableLiveData != null) {
            return;
        }
        advertisementRepository = AdvertisementRepository.getInstance();
        mutableLiveData = advertisementRepository.getAdvertisements();
    }

    public LiveData<List<AdvertisemrntModel>> getAdvertisementRepository() {
        return mutableLiveData;
    }
}
