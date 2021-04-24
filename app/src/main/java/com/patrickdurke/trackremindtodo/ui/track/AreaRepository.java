package com.patrickdurke.trackremindtodo.ui.track;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.patrickdurke.trackremindtodo.ui.track.area.parameter.ParameterRepository;

public class AreaRepository {

    private static AreaRepository instance;
    private AreaListLiveData areaListLiveData;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference areasRef;

    public static AreaRepository getInstance() {
        if (instance == null)
            instance = new AreaRepository();
        return instance;
    }

    private AreaRepository() {
    }

    public void init(String userId) {
        areasRef = database.getReference(userId).child("areas");
        areaListLiveData = new AreaListLiveData(areasRef);
        ParameterRepository.init(); //Waking up ParameterRepository, so it is ready for use
    }

    public AreaListLiveData getAreaListLiveData(){
        return areaListLiveData;
    }

    public void addArea(Area area){
        int id = areaListLiveData.getLatestId() + 1;
        area.setId(id);
        DatabaseReference childRef = areasRef.child(area.getId() + "");
        childRef.setValue(area);
    }

}
