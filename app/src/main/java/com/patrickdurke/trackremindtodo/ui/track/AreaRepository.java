package com.patrickdurke.trackremindtodo.ui.track;
import androidx.lifecycle.MutableLiveData;

import com.patrickdurke.trackremindtodo.ui.track.area.Record;

import java.util.ArrayList;
import java.util.List;

public class AreaRepository {

    private List<Area> areaList;
    private MutableLiveData<List<Area>> areaListLiveData;
    int latestId;

    public AreaRepository() {
        areaList = new ArrayList<>();
        this.areaListLiveData = new MutableLiveData<>();
        latestId = -1;
        //DummyData
        addArea(new Area("Fitness", "Green"));
        addArea(new Area("Mind", "Blue"));
        addArea(new Area("Food", "Purple"));
        addArea(new Area("Medication", "Red"));
        addArea(new Area("RandomNotes", "Orange"));
        areaListLiveData.setValue(areaList);
    }

    public MutableLiveData<List<Area>> getAreaListLiveData() {
        return areaListLiveData;
    }

    public void addArea(Area area){
        area.setId(++latestId);  // TODO move logic down
        areaList.add(area); //TODO Save via DAO, return added object from DAO including id
    }
}
