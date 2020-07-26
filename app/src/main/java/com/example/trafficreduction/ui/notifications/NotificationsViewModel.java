package com.example.trafficreduction.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<String> mNameText;

    public NotificationsViewModel() {
        mNameText = new MutableLiveData<>();
        mNameText.setValue("Daniel Yim");
    }

    public LiveData<String> getName() {
        return mNameText;
    }
}