package com.patrickdurke.trackremindtodo.ui.track.area;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.patrickdurke.trackremindtodo.ui.track.area.record.RecordListLiveData;

public class RecordRepository {

    private static RecordRepository instance;
    private RecordListLiveData recordListLiveData;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference recordsRef;

    public static RecordRepository getInstance() {
        if (instance == null)
            instance = new RecordRepository();
        return instance;
    }

    private RecordRepository() {
    }

    public void init(String userId, int selectedAreaId) {
        recordsRef = database.getReference(userId).child("records").child(selectedAreaId+"");
        recordListLiveData = new RecordListLiveData(recordsRef, selectedAreaId);

    }

    public RecordListLiveData getRecordListLiveData() {
        return recordListLiveData;
    }

    public void addRecord(Record record){
        int id = recordListLiveData.getLatestId() + 1;
        record.setId(id);
        DatabaseReference childRef = recordsRef.child(record.getId() + "");
        childRef.setValue(record);
    }


    public int getAreaId(int recordId) {
        return recordListLiveData.getAreaId(recordId);
    }

}
