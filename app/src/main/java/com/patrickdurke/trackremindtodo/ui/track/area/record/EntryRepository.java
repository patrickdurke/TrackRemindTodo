package com.patrickdurke.trackremindtodo.ui.track.area.record;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.patrickdurke.trackremindtodo.ui.track.area.record.entry.EntryListLiveData;

import java.util.ArrayList;
import java.util.List;

public class EntryRepository {

    private static EntryRepository instance;
    private EntryListLiveData entryListLiveData;
    int latestId;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference entriesRef;

    public static EntryRepository getInstance() {
        if (instance == null)
            instance = new EntryRepository();
        return instance;
    }

    private EntryRepository() {
    }

    public void init(String userId, int selectedAreaId, int selectedRecordId) {
        entriesRef = database.getReference(userId).child("records").child(selectedAreaId+"").child(selectedRecordId+"").child("entryList");
        entryListLiveData = new EntryListLiveData(entriesRef);

    }

    public EntryListLiveData getEntryListLiveData() {
        return entryListLiveData;
    }

    public void addEntry(Entry entry){
        int id = 0;
        if (entryListLiveData.getValue() != null)
            id = entryListLiveData.getValue().size();
        
        entry.setId(id);
        DatabaseReference childRef = entriesRef.child(entry.getId()+"");
        childRef.setValue(entry);
    }

    public Entry getEntry(int selectedEntryId) {
        for (Entry entry : entryListLiveData.getValue()) {
            if (entry.getId() == selectedEntryId)
                return entry;
        }
        return null;
    }
}
