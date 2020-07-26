package com.example.trafficreduction.ui.addPlace;

import androidx.lifecycle.MutableLiveData;

public class Place {
    private String name;
    private String id;
    private String address;
    private boolean added;
    private MutableLiveData<PopularTimes> popTimes;
    public Place(String name, String id, String address) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.added = false;
        this.popTimes = new MutableLiveData<>();
    }

    public String getName() {
        return name;
    }
    public String getId() { return id; }
    public String getAddress() { return address; }
    public void setAdded(boolean bool) { this.added = bool;}
    public boolean getAdded() { return added; }
    public void setPopTimes(PopularTimes popTimes) {this.popTimes.setValue(popTimes); }
    public MutableLiveData<PopularTimes> getPopTimes() { return popTimes;}
}
