package com.patrickdurke.trackremindtodo.ui.track.area.record.entry;

import androidx.lifecycle.ViewModel;

import com.patrickdurke.trackremindtodo.ui.track.AreaRepository;
import com.patrickdurke.trackremindtodo.ui.track.area.Record;
import com.patrickdurke.trackremindtodo.ui.track.area.RecordRepository;
import com.patrickdurke.trackremindtodo.ui.track.area.parameter.Parameter;
import com.patrickdurke.trackremindtodo.ui.track.area.parameter.ParameterRepository;
import com.patrickdurke.trackremindtodo.ui.track.area.record.RecordEntry;
import com.patrickdurke.trackremindtodo.ui.track.area.record.EntryRepository;

import java.time.Instant;
import java.util.List;

public class EntryViewModel extends ViewModel {
    private EntryRepository entryRepository;
    private RecordRepository recordRepository;
    private ParameterRepository parameterRepository;

    public void addEntry(RecordEntry recordEntry, int selectedAreaId) {

        if(recordEntry.getRecordId() == -1) {
            Record record = new Record();
            record.setTimeStamp(Instant.now().getEpochSecond());
            record.setAreaId(selectedAreaId);
            int recordId = recordRepository.addRecord(record);
            recordEntry.setRecordId(recordId);
        }

        entryRepository.addEntry(recordEntry, selectedAreaId);
    }

    public void init() {
        entryRepository = EntryRepository.getInstance();
        recordRepository = RecordRepository.getInstance();
        parameterRepository = ParameterRepository.getInstance();
    }

    public RecordEntry getEntry(int selectedEntryId) {
        return entryRepository.getEntry(selectedEntryId);
    }

    public List<Parameter> getParameters(int selectedAreaId) {
        return parameterRepository.getParameterList(selectedAreaId);
    }
}