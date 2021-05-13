package com.patrickdurke.trackremindtodo.ui.track.area.record.entry;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.patrickdurke.trackremindtodo.ui.track.area.record.RecordEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EntryListLiveData extends LiveData<Map<String, RecordEntry>> {
    private static final String TAG = "EntryListLiveData";
    DatabaseReference entriesRef;
    Map<String, RecordEntry> recordEntryMap;

    private final ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            //https://stackoverflow.com/questions/55694354/expected-a-list-while-deserializing-but-got-a-class-java-util-hashmap
            recordEntryMap = new HashMap<>();
            for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                RecordEntry recordEntry = dataSnapshot.getValue(RecordEntry.class);
                recordEntryMap.put(recordEntry.getId()+"", recordEntry);
            }
            setValue(recordEntryMap);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Log.w(TAG, "Failed to read value.", error.toException());
        }
    };

    public EntryListLiveData(DatabaseReference entriesRef) {
        this.entriesRef = entriesRef;
    }


    @Override
    protected void onActive() {
        super.onActive();
        entriesRef.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        entriesRef.removeEventListener(listener);
    }
}
