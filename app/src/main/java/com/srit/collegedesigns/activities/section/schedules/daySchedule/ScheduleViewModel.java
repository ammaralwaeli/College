package com.srit.collegedesigns.activities.section.schedules.daySchedule;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.srit.collegedesigns.api.ApiResponse;

class ScheduleViewModel extends ViewModel {

    private MutableLiveData<ApiResponse> mutableLiveData = new MutableLiveData<>();


    void init() {
        ScheduleRepository scheduleRepository = ScheduleRepository.getInstance();
            mutableLiveData = scheduleRepository.getSchedules();

    }

    LiveData<ApiResponse> getScheduleRepository() {
        return mutableLiveData;
    }
}
