package com.patrickdurke.trackremindtodo.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        String userName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        String firstName = userName.split(" ")[0];
        mText = new MutableLiveData<>();
        mText.setValue("Welcome,\n" + firstName);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setText(MutableLiveData<String> mText) {
        this.mText = mText;
    }
}