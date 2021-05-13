package com.patrickdurke.trackremindtodo.ui.track.area.record;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.patrickdurke.trackremindtodo.ui.track.area.record.entry.EntryListLiveData;

import java.util.Map;

public class EntryRepository {

    private static EntryRepository instance;
    private EntryListLiveData entryListLiveData;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference recordsRef;
    DatabaseReference entriesRef;

    int selectedRecordId = -1;
    int selectedAreaId = -1;

    public static EntryRepository getInstance() {
        if (instance == null)
            instance = new EntryRepository();
        return instance;
    }

    private EntryRepository() {
    }

    public void init(String userId, int selectedAreaId, int selectedRecordId) {
        this.selectedRecordId = selectedRecordId;
        this.selectedAreaId = selectedAreaId;
        recordsRef = database.getReference(userId).child("records");
        entriesRef = recordsRef.child("area_id_" + selectedAreaId).child(selectedRecordId+"").child("recordEntryMap");
        entryListLiveData = new EntryListLiveData(entriesRef);
    }

    public EntryListLiveData getEntryListLiveData() {
        return entryListLiveData;
    }

    public void addEntry(RecordEntry recordEntry, int selectedAreaId){
        selectedRecordId = recordEntry.getRecordId();
        entriesRef = recordsRef.child("area_id_" + selectedAreaId).child(selectedRecordId+"").child("recordEntryMap");

        if(entryListLiveData.getValue() == null)
            recordEntry.setId(0);
        else
            recordEntry.setId(entryListLiveData.getValue().size());

        entriesRef.push().setValue(recordEntry);
    }

    public RecordEntry getEntry(int selectedEntryId) {
        for (Map.Entry mapEntry : entryListLiveData.getValue().entrySet()) {
            RecordEntry recordEntry = (RecordEntry)mapEntry.getValue();
            if (recordEntry.getId() == selectedEntryId)
                return recordEntry;
        }
        return null;
    }
}
