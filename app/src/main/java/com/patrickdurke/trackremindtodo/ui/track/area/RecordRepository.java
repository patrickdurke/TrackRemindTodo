package com.patrickdurke.trackremindtodo.ui.track.area;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class RecordRepository {

    private List<Record> recordList;
    private MutableLiveData<List<Record>> recordListLiveData;

    public RecordRepository() {
        recordList = new ArrayList<>();
        this.recordListLiveData = new MutableLiveData<>();
        //DummyData
        recordList.add(new Record(1, "2021-03-22-09-17-09", 1));
        recordList.add(new Record(2, "2021-03-25-18-17-44", 1));
        recordList.add(new Record(3, "2021-03-26-14-17-11", 1));
        recordList.add(new Record(4, "2021-03-29-15-16-12", 1));
        recordList.add(new Record(5, "2021-03-29-18-17-36", 1));

        recordListLiveData.setValue(recordList);
    }

    public MutableLiveData<List<Record>> getRecordListLiveData(int selectedAreaId) {
        List<Record> sortedRecordList = new ArrayList<>();

        for (Record record: recordList)
            if (record.getParentId() == selectedAreaId) sortedRecordList.add(record);

        recordListLiveData.setValue(sortedRecordList);

        return recordListLiveData;
    }
}
