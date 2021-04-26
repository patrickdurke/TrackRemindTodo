package com.patrickdurke.trackremindtodo.ui.track.area;

import androidx.lifecycle.ViewModel;

import com.github.mikephil.charting.data.Entry;
import com.patrickdurke.trackremindtodo.ui.track.area.parameter.ParameterRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AreaChartViewModel extends ViewModel {
    ParameterRepository parameterRepository = ParameterRepository.getInstance();
    RecordRepository recordRepository = RecordRepository.getInstance();

    public String[] getQuantitativeParameterStrings(int selectedAreaId) {
        return parameterRepository.getQuantitativeParameterStrings(selectedAreaId);
    }

    public ArrayList<Entry> getChartEntries(String parameterName) {

        ArrayList<Entry> chartEntries = recordRepository.getChartEntries(parameterName);

        Collections.sort(chartEntries, (entry1, entry2) -> Float.compare(entry1.getX(), entry2.getX()));

        return chartEntries;
    }
}