package com.patrickdurke.trackremindtodo.ui.track;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
