package com.patrickdurke.trackremindtodo.ui.track;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class TrackingAreaRepository {

    private List<TrackingArea> trackingAreaList;
    private MutableLiveData<List<TrackingArea>> trackingAreaListLiveData;

    public TrackingAreaRepository() {
        trackingAreaList = new ArrayList<>();
        this.trackingAreaListLiveData = new MutableLiveData<>();
        //DummyData
        trackingAreaList.add(new TrackingArea(1,"Fitness", "Green"));
        trackingAreaList.add(new TrackingArea(2,"Mood", "Blue"));
        trackingAreaList.add(new TrackingArea(3,"Medication", "Red"));
        trackingAreaList.add(new TrackingArea(4,"Meditation", "Purple"));
        trackingAreaList.add(new TrackingArea(5,"RandomNotes", "Orange"));
        trackingAreaListLiveData.setValue(trackingAreaList);
    }

    public MutableLiveData<List<TrackingArea>> getTrackingAreaListLiveData() {
        return trackingAreaListLiveData;
    }
}
