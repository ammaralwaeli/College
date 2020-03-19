package com.srit.collegedesigns.activities.section.schedules;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.srit.collegedesigns.api.ApiResponse;

class SchedulesViewModel extends ViewModel {

    private MutableLiveData<ApiResponse> mutableLiveData = new MutableLiveData<>();
    private SchedulesRepository scheduleRepository;


    void init(int id) {
        scheduleRepository = SchedulesRepository.getInstance();
            mutableLiveData = scheduleRepository.getSchedules(id);

    }

    LiveData<ApiResponse> getScheduleRepository() {
        return mutableLiveData;
    }
}
