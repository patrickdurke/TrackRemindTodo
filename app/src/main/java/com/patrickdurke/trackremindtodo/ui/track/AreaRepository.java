package com.patrickdurke.trackremindtodo.ui.track;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class AreaRepository {

    private List<Area> areaList;
    private MutableLiveData<List<Area>> areaListLiveData;

    public AreaRepository() {
        areaList = new ArrayList<>();
        this.areaListLiveData = new MutableLiveData<>();
        //DummyData
        areaList.add(new Area(1,"Fitness", "Green"));
        areaList.add(new Area(2,"Mood", "Blue"));
        areaList.add(new Area(3,"Medication", "Red"));
        areaList.add(new Area(4,"Meditation", "Purple"));
        areaList.add(new Area(5,"RandomNotes", "Orange"));
        areaListLiveData.setValue(areaList);
    }

    public MutableLiveData<List<Area>> getAreaListLiveData() {
        return areaListLiveData;
    }
}
