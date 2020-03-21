package com.srit.collegedesigns.activities.section.homework;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.srit.collegedesigns.api.ApiResponse;

class HomeworkViewModel extends ViewModel {


    private MutableLiveData<ApiResponse> mutableLiveData = new MutableLiveData<>();


    void init() {
        HomeworkRepository homeworkRepository = HomeworkRepository.getInstance();

        mutableLiveData = homeworkRepository.getHomework();

    }

    LiveData<ApiResponse> getHomeworkRepository() {
        return mutableLiveData;
    }
}
