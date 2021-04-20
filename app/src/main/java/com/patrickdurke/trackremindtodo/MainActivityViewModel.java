package com.patrickdurke.trackremindtodo;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.patrickdurke.trackremindtodo.ui.UserRepository;

public class MainActivityViewModel extends AndroidViewModel {
    private final UserRepository userRepository;
    //private final MessageRepository messageRepository;

    public MainActivityViewModel(Application app){
        super(app);
        userRepository = UserRepository.getInstance(app);
    }

    public void init() {
    // String userId = userRepository.getCurrentUser().getValue().getUid();
    // areaRepository.init(userId); // TODO
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }

    public void signOut() {
        userRepository.signOut();
    }
}

