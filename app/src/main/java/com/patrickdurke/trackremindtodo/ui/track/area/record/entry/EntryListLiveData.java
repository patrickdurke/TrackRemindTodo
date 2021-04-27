package com.patrickdurke.trackremindtodo.ui.track.area.record.entry;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.patrickdurke.trackremindtodo.ui.track.area.record.RecordEntry;

import java.util.ArrayList;
import java.util.List;

public class EntryListLiveData extends LiveData<List<RecordEntry>> {
    private static final String TAG = "EntryListLiveData";
    DatabaseReference entriesRef;
    List<RecordEntry> recordEntryList;

    GenericTypeIndicator<List<RecordEntry>> t = new GenericTypeIndicator<List<RecordEntry>>() {};

    private final ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            recordEntryList = snapshot.getValue(t);
            setValue(recordEntryList);
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
