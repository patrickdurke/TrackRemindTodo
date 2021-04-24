package com.patrickdurke.trackremindtodo.ui.track.area.parameter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.patrickdurke.trackremindtodo.ui.UserRepository;
import com.patrickdurke.trackremindtodo.ui.track.Area;
import com.patrickdurke.trackremindtodo.ui.track.area.record.EntryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParameterRepository {

    private static ParameterRepository instance;
    private List<Parameter> parameterList;
    private MutableLiveData<List<Parameter>> parameterListLiveData;

    private static final String TAG = "ParameterRepository";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference parametersRef;

    public static ParameterRepository getInstance() {
        if (instance == null)
            init();
        return instance;
    }

    public static void init(){
        instance = new ParameterRepository();
    }

    private ParameterRepository() {
        parametersRef = database.getReference(Objects.requireNonNull(UserRepository.getInstance().getCurrentUser().getValue()).getUid()).child("parameters");
        parameterList = new ArrayList<>();
        this.parameterListLiveData = new MutableLiveData<>();

        GenericTypeIndicator<List<Parameter>> t = new GenericTypeIndicator<List<Parameter>>() {};

        parametersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                parameterList = snapshot.getValue(t);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        parametersRef.get().addOnSuccessListener(dataSnapshot -> {
            if(!dataSnapshot.hasChildren()) {
                this.parametersRef.setValue(getParameterDummyData());
            }
        });
    }

    private List<Parameter> getParameterDummyData() {
        List<Parameter> parameterList = new ArrayList<>();
        parameterList.add(new Parameter("Running", "Number", "km", 0));
        parameterList.add(new Parameter("Swimming", "Number", "m", 0));
        parameterList.add(new Parameter("Note", "Text", "", 0));
        parameterList.add(new Parameter("Weight", "Number", "kg", 0));
        parameterList.add(new Parameter("Mood", "Number", "", 1));
        parameterList.add(new Parameter("Meditation", "Duration", "hours", 1));
        parameterList.add(new Parameter("Calories", "Number", "kcal", 2));

        int id = 0;
        for (Parameter parameter : parameterList)
            parameter.setId(id++);
        return parameterList;

    }

    public MutableLiveData<List<Parameter>> getParameterListLiveData(int selectedAreaId) {
        return parameterListLiveData;
    }

  public void addParameter(Parameter parameter) {
      int id = parameterList.size();
      parameter.setId(id);
      DatabaseReference childRef = parametersRef.child(parameter.getId() + "");
      childRef.setValue(parameter);
  }

    public Parameter getParameter(int parameterId) {
        for (Parameter parameter: parameterList)
            if (parameter.getId() == parameterId)
                return parameter;
    return null;
    }
}
