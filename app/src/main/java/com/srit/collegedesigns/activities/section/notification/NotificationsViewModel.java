package com.srit.collegedesigns.activities.section.notification;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.srit.collegedesigns.api.ApiResponse;
import com.srit.collegedesigns.myTypes.NotificationType;

class NotificationsViewModel extends ViewModel {


    private MutableLiveData<ApiResponse> mutableLiveData = new MutableLiveData<>();


    void init(NotificationType notificationType) {
        NotificationRepository notificationRepository = NotificationRepository.getInstance();
        if (notificationType.equals(NotificationType.SECTION)) {
            mutableLiveData = notificationRepository.getStageNotifications();
        } else {
            mutableLiveData = notificationRepository.getStudentNotifications();
        }
    }

    LiveData<ApiResponse> getNotificationRepository() {
        return mutableLiveData;
    }
}
