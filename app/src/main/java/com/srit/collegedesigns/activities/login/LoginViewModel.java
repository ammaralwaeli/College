package com.srit.collegedesigns.activities.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonObject;

public class LoginViewModel extends ViewModel {


    private MutableLiveData<MyResponse> mutableLiveData;

    public void init(String password, String username) {
        if (mutableLiveData != null) {
            return;
        }
        LoginRepository loginRepository = LoginRepository.getInstance();
        mutableLiveData = loginRepository.login(password, username);
    }

    LiveData<MyResponse> getLoginRepository() {
        return mutableLiveData;
    }
}
