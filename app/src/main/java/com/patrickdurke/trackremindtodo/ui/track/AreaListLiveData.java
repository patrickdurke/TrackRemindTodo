package com.patrickdurke.trackremindtodo.ui.track;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.github.mikephil.charting.data.Entry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.patrickdurke.trackremindtodo.ui.UserRepository;
import com.patrickdurke.trackremindtodo.ui.track.area.Record;
import com.patrickdurke.trackremindtodo.ui.track.area.record.RecordEntry;

import java.time.Instant;
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

        List<Long> timeStampList = new ArrayList<>();

        long timeStamp = Instant.now().getEpochSecond();
        for (int i = 0; i < 5 ; i++) {
            timeStamp = timeStamp + i * 86400+3600+312;
            timeStampList.add(timeStamp);
        }

        for (Area area : getAreaDummyData()) {
            int areaId = area.getId();
            String areaName = area.getName();
            List<Record> recordList = new ArrayList<>();

            int sign = 1;
            for (int i = 0; i < timeStampList.size() ; i++) {
                String text = textPart1.get(i) + areaName + textPart2.get(i);

                List<RecordEntry> recordEntryList = getEntryDummyData(text, i*sign);
                recordList.add(new Record(timeStampList.get(i), areaId, recordEntryList));

                if(sign > 0)
                    sign = -1/(i+1);
                else
                    sign = 1;
            }

            int recordId = 0;
            for (Record record : recordList)
                record.setId(recordId++);
            recordsRef.child(areaId+"").setValue(recordList);
        }
    }

    private List<RecordEntry> getEntryDummyData(String text, int i) {
        List<RecordEntry> recordEntryList = new ArrayList<>();
        recordEntryList.add(new RecordEntry(0, 5 + i + "", 0));
        recordEntryList.add(new RecordEntry(1, 200 + i*100 + "", 0));
        recordEntryList.add(new RecordEntry(2, text.toLowerCase(), 0));

        int id = 0;
        for (RecordEntry recordEntry : recordEntryList)
            recordEntry.setId(id++);

        return recordEntryList;
    }

}
