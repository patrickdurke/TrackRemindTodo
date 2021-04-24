package com.patrickdurke.trackremindtodo.ui.track.area.record;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.patrickdurke.trackremindtodo.ui.UserRepository;
import com.patrickdurke.trackremindtodo.ui.track.area.Record;
import com.patrickdurke.trackremindtodo.ui.track.area.RecordRepository;

import java.util.List;
import java.util.Objects;

public class RecordViewModel extends ViewModel {
    private EntryRepository entryRepository;
    private RecordRepository recordRepository;
    private UserRepository userRepository;
    private MutableLiveData<String> mText;
    private int selectedAreaId;

    public RecordViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is track area record fragment");
        userRepository = UserRepository.getInstance();
        entryRepository = EntryRepository.getInstance();
        recordRepository = RecordRepository.getInstance();
    }

    public void init(int selectedAreaId, int selectedRecordId) {
        this.selectedAreaId = selectedAreaId;
        entryRepository.init(Objects.requireNonNull(userRepository.getCurrentUser().getValue()).getUid(), selectedAreaId, selectedRecordId);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Entry>> getEntryListLiveData() {
        return entryRepository.getEntryListLiveData();
    }

    public void addRecord(Record record) {
        recordRepository.addRecord(record);
    }
}
