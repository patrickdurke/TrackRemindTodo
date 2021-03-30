package com.patrickdurke.trackremindtodo.ui.track.area;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class AreaViewModel extends ViewModel {
    private RecordRepository recordRepository;
    private MutableLiveData<String> mText;

    public AreaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is track area fragment");
    }

    public void init() {
        recordRepository = new RecordRepository();
    }

    public void setTrackingAreaName(String selectedItem) {
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Record>> getRecordListLiveData(int selectedItem) {
        return recordRepository.getRecordListLiveData(selectedItem);
    }
}