package com.patrickdurke.trackremindtodo.ui.track.area.record;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.patrickdurke.trackremindtodo.ui.track.area.Record;

import java.util.List;

public class RecordListLiveData extends LiveData<List<Record>> {
    private static final String TAG = "RecordListLiveData";
    DatabaseReference recordsRef;
    List<Record> recordList;
    int selectedAreaId;

    GenericTypeIndicator<List<Record>> t = new GenericTypeIndicator<List<Record>>() {
    };

    private final ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            recordList = snapshot.getValue(t);
            setValue(recordList);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Log.w(TAG, "Failed to read value.", error.toException());
        }
    };

    public RecordListLiveData(DatabaseReference ref, int selectedAreaId) {
        this.selectedAreaId = selectedAreaId;
        recordsRef = ref;
    }

    @Override
    protected void onActive() {
        super.onActive();
        recordsRef.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        recordsRef.removeEventListener(listener);
    }


    public int getLatestId() {
        return recordList.get(recordList.size()-1).getId();
    }

    public int getAreaId(int recordId) {
        for(Record record : recordList)
            if(record.getId() == recordId)
                return record.getAreaId();
        return -1;
    }


}
