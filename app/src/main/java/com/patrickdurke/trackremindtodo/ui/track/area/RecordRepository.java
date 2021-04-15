package com.patrickdurke.trackremindtodo.ui.track.area;

import androidx.lifecycle.MutableLiveData;

import com.patrickdurke.trackremindtodo.ui.track.area.record.Entry;
import com.patrickdurke.trackremindtodo.ui.track.area.record.EntryRepository;

import java.util.ArrayList;
import java.util.List;

public class RecordRepository {

    private static RecordRepository instance;

    private List<Record> recordList;
    private MutableLiveData<List<Record>> recordListLiveData;
    int latestId;

    public static RecordRepository getInstance() {
        if (instance == null)
            instance = new RecordRepository();
        return instance;
    }

    private RecordRepository() {

        recordList = new ArrayList<>();
        this.recordListLiveData = new MutableLiveData<>();
        latestId = -1;

        //DummyData
        List<Entry> entryList = new ArrayList<>();

        entryList.add(new Entry(0,"10", 0));
        entryList.add(new Entry(1,"200", 0));
        entryList.add(new Entry(2,"It was a nice workout", 0));
        addRecord(new Record("March 22, 17:14", 0, entryList));
        //addRecord(new Record("2021-03-22-09-17-09", 0, entryList));

        entryList = new ArrayList<>();
        entryList.add(new Entry(5,"2", 1));
        addRecord(new Record("March 26, 18:02", 0, entryList));
        //addRecord(new Record("2021-03-25-18-17-44", 1, entryList));

        entryList = new ArrayList<>();
        entryList.add(new Entry(3, "65", 2));
        addRecord(new Record("April 14, 11:01", 0, entryList));
        //addRecord(new Record("2021-03-26-14-17-11", 0, entryList));

        entryList = new ArrayList<>();
        entryList.add(new Entry(5, "1", 1));
        addRecord(new Record("April 15, 11:11", 0, entryList));

        entryList = new ArrayList<>();
        entryList.add(new Entry(0,"7", 0));
        addRecord(new Record("April 15, 12:34", 0, entryList));
        addRecord(new Record("March 15, 13:25", 0, entryList));
        addRecord(new Record("March 16, 14:45", 0, entryList));
        addRecord(new Record("March 17, 12:12", 10, entryList));
        addRecord(new Record("March 18, 12:33", 0, entryList));
        addRecord(new Record("March 19, 11:31", 0, entryList));
        addRecord(new Record("April 20, 12:34", 0, entryList));
        addRecord(new Record("March 21, 13:25", 0, entryList));
        addRecord(new Record("March 22, 14:45", 0, entryList));
        addRecord(new Record("March 23, 12:12", 0, entryList));
        addRecord(new Record("March 24, 12:33", 0, entryList));
        addRecord(new Record("March 25, 11:31", 0, entryList));
        addRecord(new Record("April 26, 12:34", 0, entryList));
        addRecord(new Record("March 27, 13:25", 0, entryList));
        addRecord(new Record("March 28, 14:45", 0, entryList));
        addRecord(new Record("March 29, 12:12", 0, entryList));
        addRecord(new Record("March 30, 12:33", 0, entryList));
        addRecord(new Record("March 31, 11:31", 0, entryList));

        //addRecord(new Record("2021-03-29-15-16-12", 0, new ArrayList<>()));
        //addRecord(new Record("2021-03-29-18-17-36", 0, new ArrayList<>()));

        //

        recordListLiveData.setValue(recordList);
    }

    public MutableLiveData<List<Record>> getRecordListLiveData(int selectedAreaId) {
        List<Record> sortedRecordList = new ArrayList<>();

        for (Record record: recordList)
            if (record.getAreaId() == selectedAreaId)
                sortedRecordList.add(record);

        recordListLiveData.setValue(sortedRecordList);

        return recordListLiveData;
    }

    public void addRecord(Record record){
        record.setId(++latestId); // TODO move logic down
        recordList.add(record); // TODO Save via DAO, return added object from DAO including id
    }

    public int getAreaId(int recordId) {
        for(Record record : recordList)
            if(record.getId() == recordId)
                return record.getAreaId();
        return -1;
    }
}
