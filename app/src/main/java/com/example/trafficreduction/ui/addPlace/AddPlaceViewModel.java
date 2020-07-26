package com.example.trafficreduction.ui.addPlace;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddPlaceViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AddPlaceViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Add a new place");
    }

    public LiveData<String> getText() {
        return mText;
    }
}