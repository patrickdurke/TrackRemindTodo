package com.patrickdurke.trackremindtodo.ui.track.area.record;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class RecordViewModel extends ViewModel {
    private EntryRepository entryRepository;
    private MutableLiveData<String> mText;

    public RecordViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is track area record fragment");
    }

    public void init() {
        entryRepository = new EntryRepository();
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

    public LiveData<List<Entry>> getEntryListLiveData(int selectedItem) {
        return entryRepository.getEntryListLiveData(selectedItem);
    }
}
