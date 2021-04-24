package com.patrickdurke.trackremindtodo.ui.track.area;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.patrickdurke.trackremindtodo.ui.UserRepository;
import com.patrickdurke.trackremindtodo.ui.track.Area;
import com.patrickdurke.trackremindtodo.ui.track.AreaRepository;

import java.util.List;
import java.util.Objects;

public class AreaViewModel extends ViewModel {
    private RecordRepository recordRepository;
    private AreaRepository areaRepository;
    private UserRepository userRepository;
    private MutableLiveData<String> mText;
    private int selectedAreaId;

    public AreaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is track area fragment");
        areaRepository = AreaRepository.getInstance();
        userRepository = UserRepository.getInstance();
        recordRepository = RecordRepository.getInstance();
    }

    public void init(int selectedAreaId) {
        this.selectedAreaId = selectedAreaId;
        recordRepository.init(Objects.requireNonNull(userRepository.getCurrentUser().getValue()).getUid(), selectedAreaId);
    }

    public void setTrackingAreaName(String selectedItem) {
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Record>> getRecordListLiveData() {
        return recordRepository.getRecordListLiveData();
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