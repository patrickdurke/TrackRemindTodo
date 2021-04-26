package com.patrickdurke.trackremindtodo.ui.track.area.record;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.patrickdurke.trackremindtodo.ui.track.area.record.entry.EntryListLiveData;

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
        entriesRef = database.getReference(userId).child("records").child(selectedAreaId+"").child(selectedRecordId+"").child("recordEntryList");
        entryListLiveData = new EntryListLiveData(entriesRef);

    }

    public EntryListLiveData getEntryListLiveData() {
        return entryListLiveData;
    }

    public void addEntry(RecordEntry recordEntry){
        int id = 0;
        if (entryListLiveData.getValue() != null)
            id = entryListLiveData.getValue().size();
        
        recordEntry.setId(id);
        DatabaseReference childRef = entriesRef.child(recordEntry.getId()+"");
        childRef.setValue(recordEntry);
    }

    public RecordEntry getEntry(int selectedEntryId) {
        for (RecordEntry recordEntry : entryListLiveData.getValue()) {
            if (recordEntry.getId() == selectedEntryId)
                return recordEntry;
        }
        return null;
    }
}
