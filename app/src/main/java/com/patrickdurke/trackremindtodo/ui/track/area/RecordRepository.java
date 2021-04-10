package com.patrickdurke.trackremindtodo.ui.track.area;

import androidx.lifecycle.MutableLiveData;

import com.patrickdurke.trackremindtodo.ui.track.area.record.Entry;

import java.util.ArrayList;
import java.util.List;

public class RecordRepository {

    private List<Record> recordList;
    private MutableLiveData<List<Record>> recordListLiveData;
    int latestId;

    public RecordRepository() {
        recordList = new ArrayList<>();
        this.recordListLiveData = new MutableLiveData<>();
        latestId = -1;
        //DummyData
        addRecord(new Record("2021-03-22-09-17-09", 0));
        addRecord(new Record("2021-03-25-18-17-44", 1));
        addRecord(new Record("2021-03-26-14-17-11", 0));
        addRecord(new Record("2021-03-29-15-16-12", 0));
        addRecord(new Record("2021-03-29-18-17-36", 0));
        recordListLiveData.setValue(recordList);
    }

    public MutableLiveData<List<Record>> getRecordListLiveData(int selectedAreaId) {
        List<Record> sortedRecordList = new ArrayList<>();

        for (Record record: recordList)
            if (record.getAreaId() == selectedAreaId) sortedRecordList.add(record);

        recordListLiveData.setValue(sortedRecordList);

        return recordListLiveData;
    }

    public void addRecord(Record record){
        record.setId(++latestId); // TODO move logic down
        recordList.add(record); //TODO Save via DAO, return added object from DAO including id
    }
}
