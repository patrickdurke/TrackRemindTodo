package com.patrickdurke.trackremindtodo.ui.track.track_area;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class TrackAreaViewModel extends ViewModel {
    private TrackingAreaRecordRepository trackingAreaRecordRepository;
    private MutableLiveData<String> mText;

    public TrackAreaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is track area fragment");
    }

    public void init() {
        trackingAreaRecordRepository = new TrackingAreaRecordRepository();
    }

    public void setTrackingAreaName(String selectedItem) {
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<TrackingAreaRecord>> getTrackingAreaRecordListLiveData(int selectedItem) {
        return trackingAreaRecordRepository.getTrackingAreaRecordListLiveData(selectedItem);
    }
}