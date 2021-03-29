package com.patrickdurke.trackremindtodo.ui.track.track_area.track_area_record;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TrackAreaRecordViewModel extends ViewModel {
    // NEXT LEVEL private TrackingAreaRecordEntry trackingAreaRecordEntry;
    // NEXT LEVEL private TrackingAreaRecordEntryRepository trackingAreaRecordEntryRepository;
    private MutableLiveData<String> mText;

    public TrackAreaRecordViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is track area record fragment");
    }

    public void init() {
        // NEXT LEVEL trackingAreaRecordEntryRepository = new TrackingAreaRecordEntryRepository();
    }

     // NEXT LEVEL    public TrackingAreaRecordEntry getTrackingAreaRecordEntry() {
     // NEXT LEVEL        return trackingAreaRecordEntry;
     // NEXT LEVEL    }

     // NEXT LEVEL    public void setTrackingAreaRecordEntry(TrackingAreaRecordEntry trackingAreaRecordEntry) {
     // NEXT LEVEL        this.trackingAreaRecordEntry = trackingAreaRecordEntry;
     // NEXT LEVEL    }

    public void setTrackingAreaRecordName(String selectedItem) {
    }

    public LiveData<String> getText() {
        return mText;
    }

     // NEXT LEVEL    public LiveData<List<TrackingAreaRecordEntry>> getTrackingAreaRecordEntryListLiveData() {
     // NEXT LEVEL        return trackingAreaRecordEntryRepository.getTrackingAreaRecordEntryListLiveData();
     // NEXT LEVEL    }
}
