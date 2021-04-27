package com.patrickdurke.trackremindtodo.ui.track.area.record;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.patrickdurke.trackremindtodo.ui.UserRepository;
import com.patrickdurke.trackremindtodo.ui.track.area.record.entry.EntryListLiveData;

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
        entriesRef = recordsRef.child("area_id_" + selectedAreaId).child(selectedRecordId+"").child("recordEntryList");
        entryListLiveData = new EntryListLiveData(entriesRef);
    }

    public EntryListLiveData getEntryListLiveData() {
        return entryListLiveData;
    }

    public void addEntry(RecordEntry recordEntry){
        int id = 0;
        /*if (entryListLiveData != null)
            id = entryListLiveData.getLatestId();*/
        recordEntry.setId(id);
        selectedRecordId = recordEntry.getRecordId();

        entriesRef = recordsRef.child("area_id_" + selectedAreaId).child(selectedRecordId+"").child("recordEntryList");
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
