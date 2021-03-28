package com.patrickdurke.trackremindtodo.ui.track.track_area;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.patrickdurke.trackremindtodo.R;

public class TrackAreaFragment extends Fragment {

    //private TrackAreaViewModel trackAreaViewModel;

    public static TrackAreaFragment newInstance() {
        return new TrackAreaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        String selectedItem = getArguments().getString("selectedItem");
        //trackAreaViewModel = new ViewModelProvider(this).get(TrackAreaViewModel.class);
        View root = inflater.inflate(R.layout.track_area_fragment, container, false);
        final TextView textView = root.findViewById(R.id.text_track_area);
        textView.setText(selectedItem);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }
}