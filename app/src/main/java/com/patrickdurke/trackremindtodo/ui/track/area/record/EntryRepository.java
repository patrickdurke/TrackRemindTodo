package com.patrickdurke.trackremindtodo.ui.track.area.record;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class EntryRepository {

    private List<Entry> entryList;
    private MutableLiveData<List<Entry>> entryListLiveData;

    public EntryRepository() {
        entryList = new ArrayList<>();
        this.entryListLiveData = new MutableLiveData<>();
        //DummyData
        entryList.add(new Entry(1, "10 km", 1));
        entryList.add(new Entry(2, "200 m", 1));
        entryList.add(new Entry(3, "2 hours ", 1));
        entryList.add(new Entry(4, "It was a nice workout", 1));
        entryList.add(new Entry(5, "65 kg", 1));

        entryListLiveData.setValue(entryList);
    }

    public MutableLiveData<List<Entry>> getEntryListLiveData(int selectedRecordId) {
        List<Entry> sortedEntryList = new ArrayList<>();

        for (Entry entry: entryList)
            if (entry.getParentId() == selectedRecordId) sortedEntryList.add(entry);

        entryListLiveData.setValue(sortedEntryList);

        return entryListLiveData;
    }

}
