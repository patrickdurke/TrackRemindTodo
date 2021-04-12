package com.patrickdurke.trackremindtodo.ui.track.area.record.entry;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.patrickdurke.trackremindtodo.ui.track.area.parameter.Parameter;
import com.patrickdurke.trackremindtodo.ui.track.area.parameter.ParameterRepository;
import com.patrickdurke.trackremindtodo.ui.track.area.record.Entry;
import com.patrickdurke.trackremindtodo.ui.track.area.record.EntryRepository;

import java.util.List;

public class EntryViewModel extends ViewModel {
    private EntryRepository entryRepository;

    public LiveData<List<Entry>> getEntryListLiveData(int selectedRecordId) {
        return entryRepository.getEntryListLiveData(selectedRecordId);
    }

    public void addEntry(Entry entry) {
        entryRepository.addEntry(entry);
    }

    public void init() {
        entryRepository = EntryRepository.getInstance();
    }

    public Entry getEntry(int selectedEntryId) {
        return entryRepository.getEntry(selectedEntryId);
    }
}