package com.patrickdurke.trackremindtodo.ui.track;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class TrackViewModel extends ViewModel {

    private AreaRepository areaRepository;

    private MutableLiveData<String> mText;

    public TrackViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is track fragment");
    }

    public void init() {
        areaRepository = AreaRepository.getInstance();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Area>> getTrackingAreaListLiveData() {
        return areaRepository.getAreaListLiveData();
    }
}