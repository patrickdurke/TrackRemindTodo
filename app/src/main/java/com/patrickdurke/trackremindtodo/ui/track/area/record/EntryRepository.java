package com.patrickdurke.trackremindtodo.ui.track.area.record;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class EntryRepository {

    private List<Entry> entryList;
    private MutableLiveData<List<Entry>> entryListLiveData;
    int latestId;

    public EntryRepository() {
        entryList = new ArrayList<>();
        this.entryListLiveData = new MutableLiveData<>();
        latestId = -1;
        //DummyData
        addEntry(new Entry(0,"10", 0));
        addEntry(new Entry(1,"200", 0));
        addEntry(new Entry(5,"2", 1));
        addEntry(new Entry(2,"It was a nice workout", 0));
        addEntry(new Entry(3, "65", 2));
        entryListLiveData.setValue(entryList);
    }

    public MutableLiveData<List<Entry>> getEntryListLiveData(int selectedRecordId) {
        List<Entry> sortedEntryList = new ArrayList<>();

        for (Entry entry: entryList)
            if (entry.getRecordId() == selectedRecordId) sortedEntryList.add(entry);

        entryListLiveData.setValue(sortedEntryList);

        return entryListLiveData;
    }

    public void addEntry(Entry entry){
        entry.setId(++latestId); // TODO Should happen deeper down later
        entryList.add(entry); //TODO Save via DAO, return added object from DAO including id
    }

}
