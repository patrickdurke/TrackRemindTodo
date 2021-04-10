package com.patrickdurke.trackremindtodo.ui.track.area.parameter;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class ParameterRepository {

    private List<Parameter> parameterList;
    private MutableLiveData<List<Parameter>> parameterListLiveData;
    int latestId;

    public ParameterRepository() {
        parameterList = new ArrayList<>();
        this.parameterListLiveData = new MutableLiveData<>();
        latestId = -1;
        //DummyData
        addParameter(new Parameter("Running", "Number", "km", 0));
        addParameter(new Parameter("Swimming", "Number", "m", 0));
        addParameter(new Parameter("Note", "Text", "", 0));
        addParameter(new Parameter("Weight", "Number", "kg", 0));
        addParameter(new Parameter("Mood", "Number", "", 1));
        addParameter(new Parameter("Meditation", "Duration", "hours", 1));
        addParameter(new Parameter("Calories", "Number", "kcal", 2));
        parameterListLiveData.setValue(parameterList);
    }

    public MutableLiveData<List<Parameter>> getParameterListLiveData(int selectedAreaId) {
        List<Parameter> sortedParameterList = new ArrayList<>();

        for (Parameter parameter: parameterList)
            if (parameter.getAreaId() == selectedAreaId) sortedParameterList.add(parameter);

        parameterListLiveData.setValue(sortedParameterList);

        return parameterListLiveData;
    }

    public void addParameter(Parameter parameter) {
        parameter.setId(++latestId); // TODO Should happen deeper down later
        parameterList.add(parameter); //TODO Save via DAO, return added object from DAO including id
  }
}
