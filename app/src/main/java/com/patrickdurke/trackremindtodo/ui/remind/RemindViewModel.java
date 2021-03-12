package com.patrickdurke.trackremindtodo.ui.remind;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RemindViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RemindViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is remind fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}