package com.patrickdurke.trackremindtodo.ui.track.area.parameter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ParameterViewModel extends ViewModel {
    private ParameterRepository parameterRepository;

    public LiveData<List<Parameter>> getParameterListLiveData(int selectedAreaId) {
        return parameterRepository.getParameterListLiveData(selectedAreaId);
    }

    public void addParameter(Parameter parameter) {
        parameterRepository.addParameter(parameter);
    }

    public void init() {
        parameterRepository = new ParameterRepository();
    }

}