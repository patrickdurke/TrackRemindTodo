package com.patrickdurke.trackremindtodo.ui.track;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.patrickdurke.trackremindtodo.ui.UserRepository;

import java.util.List;
import java.util.Objects;

public class TrackViewModel extends ViewModel {

    private AreaRepository areaRepository;
    private UserRepository userRepository;

    private MutableLiveData<String> mText;

    public TrackViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is track fragment");
        areaRepository = AreaRepository.getInstance();
        userRepository = UserRepository.getInstance();
    }

    public void init() {
        areaRepository.init(Objects.requireNonNull(userRepository.getCurrentUser().getValue()).getUid());
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Area>> getTrackingAreaListLiveData() {
        return areaRepository.getAreaListLiveData();
    }
}