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
        parameterList.add(new Parameter("Running", "numberValueType", "km", 0));
        parameterList.add(new Parameter("Swimming", "numberValueType", "m", 0));
        parameterList.add(new Parameter("Note", "textValueType", "", 0));
        parameterList.add(new Parameter("Weight", "numberValueType", "kg", 0));
        parameterList.add(new Parameter("Mood", "numberValueType", "", 1));
        parameterList.add(new Parameter("Meditation", "durationValueType", "hours", 1));
        parameterList.add(new Parameter("Calories", "numberValueType", "kcal", 2));

        int id = 0;
        for (Parameter parameter : parameterList)
            parameter.setId(id++);
        return parameterList;

    }

    public MutableLiveData<List<Parameter>> getParameterListLiveData() {
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

    public String[] getQuantitativeParameterStrings(int selectedAreaId) {
        List<String> allParameterStringsForArea = new ArrayList<>();
        for (Parameter parameter: parameterList)
            if (parameter.getAreaId() == selectedAreaId && !parameter.getValueType().equals("textValueType"))
                allParameterStringsForArea.add(parameter.getName());


        return allParameterStringsForArea.toArray(new String[0]);
    }

    public int getParameterId(String parameterName) {

        for (Parameter parameter: parameterList)
            if (parameter.getName().equals(parameterName))
                return parameter.getId();

        return -1;
    }

    public List<Parameter> getParameterList(int selectedAreaId){
        List<Parameter> allParametersForArea = new ArrayList<>();
        for (Parameter parameter: parameterList)
            if (parameter.getAreaId() == selectedAreaId) {
        allParametersForArea.add(parameter);
    }
        return allParametersForArea;
}
}
