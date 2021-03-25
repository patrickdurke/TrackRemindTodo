package com.patrickdurke.trackremindtodo.ui.track;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class TrackViewModel extends ViewModel {

    private TrackingAreaRepository trackingAreaRepository;

    private MutableLiveData<String> mText;

    public TrackViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is track fragment");
    }

    public void init() {
        trackingAreaRepository = new TrackingAreaRepository();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<TrackingArea>> getTrackingAreaListLiveData() {
        return trackingAreaRepository.getTrackingAreaListLiveData();
    }
}