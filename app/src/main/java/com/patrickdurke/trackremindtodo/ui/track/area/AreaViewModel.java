package com.patrickdurke.trackremindtodo.ui.track.area;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.patrickdurke.trackremindtodo.ui.track.Area;
import com.patrickdurke.trackremindtodo.ui.track.AreaRepository;

import java.util.List;

public class AreaViewModel extends ViewModel {
    private RecordRepository recordRepository;
    private AreaRepository areaRepository;
    private MutableLiveData<String> mText;
    private int selectedAreaId;

    public AreaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is track area fragment");
    }

    public void init() {
        recordRepository = RecordRepository.getInstance();
        areaRepository = AreaRepository.getInstance();
    }

    public void setTrackingAreaName(String selectedItem) {
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Record>> getRecordListLiveData(int selectedAreaId) {
        return recordRepository.getRecordListLiveData(selectedAreaId);
    }

    public void setSelectedAreaId(int selectedAreaId) {
        this.selectedAreaId = selectedAreaId;
    }

    public int getSelectedAreaId() {
        return selectedAreaId;
    }

    public void addArea(Area area) {
        areaRepository.addArea(area);
    }
}