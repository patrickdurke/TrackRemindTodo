package com.patrickdurke.trackremindtodo.ui.track.track_area;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.patrickdurke.trackremindtodo.R;

public class TrackAreaFragment extends Fragment {

    private TrackAreaViewModel mViewModel;

    public static TrackAreaFragment newInstance() {
        return new TrackAreaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.track_area_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TrackAreaViewModel.class);
        // TODO: Use the ViewModel
    }

}