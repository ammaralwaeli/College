package com.srit.collegedesigns.section.notification;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class NotificationsViewModel extends ViewModel {


    private MutableLiveData<List<NotificationModel>> mutableLiveData;
    private NotificationRepository notificationRepository;

    public void init() {
        if (mutableLiveData != null) {
            return;
        }
        notificationRepository = NotificationRepository.getInstance();
        mutableLiveData = notificationRepository.getNotifications();
    }

    public LiveData<List<NotificationModel>> getNotificationRepository() {
        return mutableLiveData;
    }
}
