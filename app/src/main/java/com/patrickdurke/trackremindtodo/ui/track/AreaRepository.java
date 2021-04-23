package com.patrickdurke.trackremindtodo.ui.track;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.patrickdurke.trackremindtodo.ui.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class AreaRepository {

    private static AreaRepository instance;
    private List<Area> areaList;
    private MutableLiveData<List<Area>> areaListLiveData;
    int latestId;
    private static final String TAG = "AreaRepository";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = database.getReference(UserRepository.getInstance().getCurrentUser().getValue().getUid());
    DatabaseReference areasRef = rootRef.child("areas");

    public static AreaRepository getInstance() {
        if (instance == null)
            instance = new AreaRepository();
        return instance;
    }

    private AreaRepository() {
        areaList = new ArrayList<>();
        this.areaListLiveData = new MutableLiveData<>();

        areasRef.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.hasChildren())
                    areasRef.setValue(getDummyData());
                areasRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        areaListLiveData.setValue(areaList);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
            }
        });

        areasRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Area area = snapshot.getValue(Area.class);
                latestId = area.getId(); //To ensure that if an area gets added from another device, it will not be overwritten by mistake
                areaList.add(area);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private List<Area> getDummyData() {
        List<Area> areaDummyData = new ArrayList<>();
        areaDummyData.add(new Area("Fitness", "Green"));
        areaDummyData.add(new Area("Mind", "Blue"));
        areaDummyData.add(new Area("Food", "Purple"));
        areaDummyData.add(new Area("Medication", "Red"));
        areaDummyData.add(new Area("RandomNotes", "Orange"));

        for (Area area : areaDummyData)
            area.setId(latestId++);

        return areaDummyData;
    }

    public MutableLiveData<List<Area>> getAreaListLiveData(){
        return areaListLiveData;
    }

    public void addArea(Area area){
        area.setId(++latestId);
        DatabaseReference child = areasRef.child(area.getId() + "");
        child.setValue(area);
    }

}
