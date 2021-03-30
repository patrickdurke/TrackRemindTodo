package com.patrickdurke.trackremindtodo.ui.track.track_area;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.patrickdurke.trackremindtodo.R;

public class TrackAreaFragment extends Fragment {

    private TrackAreaViewModel trackAreaViewModel;
    private RecyclerView recyclerView;
    private TrackingAreaRecordListAdapter trackingAreaRecordListAdapter;
    private int selectedItemId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        trackingAreaRecordListAdapter = new TrackingAreaRecordListAdapter();

        trackAreaViewModel = new ViewModelProvider(this).get(TrackAreaViewModel.class);
        trackAreaViewModel.init();
        //trackAreaViewModel.setTrackingAreaName(selectedItem);
        selectedItemId = getArguments().getInt("selectedItemId");
        //set observer on repository data and ensure update adapter data on changed repository data
        trackAreaViewModel.getTrackingAreaRecordListLiveData(selectedItemId).observe(this, trackingAreaRecordList -> {
            if (trackingAreaRecordList != null) {
                trackingAreaRecordListAdapter.setTrackingAreaRecordList(trackingAreaRecordList);
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        trackAreaViewModel =
                new ViewModelProvider(this).get(TrackAreaViewModel.class);
        View root = inflater.inflate(R.layout.track_area_fragment, container, false);
        final TextView textView = root.findViewById(R.id.text_track_area);

        trackAreaViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // create RecyclerView
        recyclerView = root.findViewById(R.id.track_area_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(trackingAreaRecordListAdapter);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }
}