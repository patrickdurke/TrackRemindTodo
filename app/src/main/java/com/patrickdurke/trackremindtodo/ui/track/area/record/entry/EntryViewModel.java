package com.patrickdurke.trackremindtodo.ui.track.area.record.entry;

import androidx.lifecycle.ViewModel;

import com.patrickdurke.trackremindtodo.ui.track.area.record.RecordEntry;
import com.patrickdurke.trackremindtodo.ui.track.area.record.EntryRepository;

public class EntryViewModel extends ViewModel {
    private EntryRepository entryRepository;

    public void addEntry(RecordEntry recordEntry) {
        entryRepository.addEntry(recordEntry);
    }

    public void init() {
        entryRepository = EntryRepository.getInstance();
    }

    public RecordEntry getEntry(int selectedEntryId) {
        return entryRepository.getEntry(selectedEntryId);
    }
}