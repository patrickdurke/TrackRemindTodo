package com.patrickdurke.trackremindtodo.ui.track.area;

import com.github.mikephil.charting.data.Entry;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.patrickdurke.trackremindtodo.ui.track.area.parameter.ParameterRepository;
import com.patrickdurke.trackremindtodo.ui.track.area.record.RecordEntry;
import com.patrickdurke.trackremindtodo.ui.track.area.record.RecordListLiveData;

import java.util.ArrayList;
import java.util.List;

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

    public int getAreaId(long recordId) {
        return recordListLiveData.getAreaId(recordId);
    }

    public ArrayList<Entry> getChartEntries(String parameterName) {

        ArrayList<Entry> chartEntries = new ArrayList<>();

        int parameterId = ParameterRepository.getInstance().getParameterId(parameterName);
        float x;
        float y;

        for (Record record : getRecordListLiveData().getValue()){
            for (RecordEntry recordEntry : record.getRecordEntryList()){
                if (recordEntry.getParameterId() == parameterId) {
                    x = record.getTimeStamp();
                    y = Float.parseFloat(recordEntry.getValue());
                    if (y != 0f) {
                        chartEntries.add(new Entry(x, y));
                    }
                }
            }
        }
        return chartEntries;
    }
}
