package com.patrickdurke.trackremindtodo.ui.track;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

//https://medium.com/androiddevelopers/lifecycle-aware-data-loading-with-android-architecture-components-f95484159de4
public class AreaListLiveData extends LiveData<List<Area>> {

    private static final String TAG = "AreaListLiveData";
    DatabaseReference areasRef;
    List<Area> areaList;

    //https://firebase.google.com/docs/reference/android/com/google/firebase/database/GenericTypeIndicator
    GenericTypeIndicator<List<Area>> t = new GenericTypeIndicator<List<Area>>() {};

    private final ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            areaList = snapshot.getValue(t);
            setValue(areaList);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Log.w(TAG, "Failed to read value.", error.toException());
        }
    };

    private List<Area> getDummyData() {
        int id = 0;
        List<Area> areaDummyData = new ArrayList<>();
        areaDummyData.add(new Area("Fitness2", "Green"));
        areaDummyData.add(new Area("Mind", "Blue"));
        areaDummyData.add(new Area("Food", "Purple"));
        areaDummyData.add(new Area("Medication", "Red"));
        areaDummyData.add(new Area("RandomNotes", "Orange"));

        for (Area area : areaDummyData)
            area.setId(id++);

        return areaDummyData;
    }

    public AreaListLiveData(DatabaseReference ref) {
        areasRef = ref;
        areasRef.get().addOnSuccessListener(dataSnapshot -> {
            if(!dataSnapshot.hasChildren()) {
                areasRef.setValue(getDummyData());
            }
        });
    }

    @Override
    protected void onActive() {
        super.onActive();
        areasRef.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        areasRef.removeEventListener(listener);
    }


    public int getLatestId() {
        return areaList.get(areaList.size()-1).getId();
    }
}