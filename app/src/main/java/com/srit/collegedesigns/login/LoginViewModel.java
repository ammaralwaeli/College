package com.srit.collegedesigns.login;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonObject;

public class LoginViewModel extends ViewModel {


    private MutableLiveData<JsonObject> mutableLiveData;
    private LoginRepository loginRepository;

    public void init(String password, String username) {
        if (mutableLiveData != null) {
            return;
        }
        loginRepository = LoginRepository.getInstance();
        mutableLiveData = loginRepository.login(password, username);
    }

    public LiveData<JsonObject> getLoginRepository() {
        return mutableLiveData;
    }
}
