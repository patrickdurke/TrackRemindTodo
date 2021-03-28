package com.patrickdurke.trackremindtodo.ui.track.track_area;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.patrickdurke.trackremindtodo.ui.track.TrackingArea;

public class TrackAreaViewModel extends ViewModel {
    private TrackingArea trackingArea;
    private MutableLiveData<String> mText;

    public TrackAreaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is track area fragment");
    }

    public TrackingArea getTrackingArea() {
        return trackingArea;
    }

    public void setTrackingArea(TrackingArea trackingArea) {
        this.trackingArea = trackingArea;
    }

    public void setTrackingAreaName(String selectedItem) {
    }

    public LiveData<String> getText() {
        return mText;
    }
}