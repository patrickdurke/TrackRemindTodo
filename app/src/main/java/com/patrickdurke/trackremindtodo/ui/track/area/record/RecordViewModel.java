package com.patrickdurke.trackremindtodo.ui.track.area.record;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.patrickdurke.trackremindtodo.ui.track.area.Record;
import com.patrickdurke.trackremindtodo.ui.track.area.RecordRepository;

import java.util.List;

public class RecordViewModel extends ViewModel {
    private EntryRepository entryRepository;
    private RecordRepository recordRepository;
    private MutableLiveData<String> mText;

    public RecordViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is track area record fragment");
    }

    public void init() {
        entryRepository = EntryRepository.getInstance();
        recordRepository = RecordRepository.getInstance();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Entry>> getEntryListLiveData(int selectedRecordId) {
        return entryRepository.getEntryListLiveData(selectedRecordId);
    }

    public void addRecord(Record record) {
        recordRepository.addRecord(record);
    }
}
