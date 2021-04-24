package com.patrickdurke.trackremindtodo.ui.track;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.patrickdurke.trackremindtodo.ui.UserRepository;
import com.patrickdurke.trackremindtodo.ui.track.area.Record;
import com.patrickdurke.trackremindtodo.ui.track.area.record.Entry;

import java.util.ArrayList;
import java.util.List;

//https://firebase.googleblog.com/2017/12/using-android-architecture-components.html
//https://medium.com/androiddevelopers/lifecycle-aware-data-loading-with-android-architecture-components-f95484159de4
//https://developer.android.com/topic/libraries/architecture/livedata.html#extend_livedata
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

    private List<Area> getAreaDummyData() {
        int id = 0;
        List<Area> areaDummyData = new ArrayList<>();
        areaDummyData.add(new Area("Fitness", "Green"));
        areaDummyData.add(new Area("Mind", "Blue"));
        areaDummyData.add(new Area("Food", "Purple"));
        areaDummyData.add(new Area("Medication", "Red"));
        areaDummyData.add(new Area("RandomNotes", "Orange"));

        for (Area area : areaDummyData)
            area.setId(id++);

        return areaDummyData;
    }

    public AreaListLiveData(DatabaseReference areasRef) {
        this.areasRef = areasRef;
        this.areasRef.get().addOnSuccessListener(dataSnapshot -> {
            if(!dataSnapshot.hasChildren()) {
                this.areasRef.setValue(getAreaDummyData());
                addRecordDummyData();
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

    private void addRecordDummyData() {

        String userId = UserRepository.getInstance().getCurrentUser().getValue().getUid();
        DatabaseReference recordsRef = FirebaseDatabase.getInstance().getReference(userId + "").child("records");

        List<String> textPart1 = new ArrayList<>();
        textPart1.add("This is my first record about ");
        textPart1.add("Just tracking some ");
        textPart1.add("My third ");
        textPart1.add("This is my fourth record about ");
        textPart1.add("Tracking ");

        List<String> textPart2 = new ArrayList<>();
        textPart2.add("");
        textPart2.add("");
        textPart2.add(" note");
        textPart2.add("");
        textPart2.add(" stuff again");

        List<String> timeStampStringList = new ArrayList<>();
        timeStampStringList.add("March 15, 13:25");
        timeStampStringList.add("March 16, 14:45");
        timeStampStringList.add("March 17, 12:12");
        timeStampStringList.add("March 18, 12:12");
        timeStampStringList.add("March 19, 12:12");

        for (Area area : getAreaDummyData()) {
            int areaId = area.getId();
            String areaName = area.getName();
            List<Record> recordList = new ArrayList<>();

            for (int i = 0; i < timeStampStringList.size() ; i++) {

                String text = textPart1.get(i) + areaName + textPart2.get(i);
                String timeStamp = timeStampStringList.get(i);

                List<Entry> entryList = getEntryDummyData(text, i);
                recordList.add(new Record(timeStamp, areaId, entryList));
            }

            int recordId = 0;
            for (Record record : recordList)
                record.setId(recordId++);
            recordsRef.child(areaId+"").setValue(recordList);
        }
    }

    private List<Entry> getEntryDummyData(String text, int i) {
        List<Entry> entryList = new ArrayList<>();
        entryList.add(new Entry(0, 5 + i + "", 0));
        entryList.add(new Entry(1, 200 + i*100 + "", 0));
        entryList.add(new Entry(2, text.toLowerCase(), 0));

        int id = 0;
        for (Entry entry : entryList)
            entry.setId(id++);

        return entryList;
    }

}
