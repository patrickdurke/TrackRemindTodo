package com.patrickdurke.trackremindtodo.ui.track;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.patrickdurke.trackremindtodo.R;

public class TrackFragment extends Fragment {

    private TrackViewModel trackViewModel;
    private RecyclerView recyclerView;
    private AreaListAdapter areaListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        areaListAdapter = new AreaListAdapter();

        trackViewModel = new ViewModelProvider(this).get(TrackViewModel.class);
        trackViewModel.init();

        //set observer on repository data and ensure update adapter data on changed repository data
        trackViewModel.getTrackingAreaListLiveData().observe(this, trackingAreaList -> {
            if (trackingAreaList != null) {
                areaListAdapter.setAreaList(trackingAreaList);
            }
        });
    }




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        trackViewModel =
                new ViewModelProvider(this).get(TrackViewModel.class);
        View root = inflater.inflate(R.layout.track_fragment, container, false);
        final TextView textView = root.findViewById(R.id.text_track);

        trackViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // create RecyclerView
        recyclerView = root.findViewById(R.id.track_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(root.getContext(), 2));
        recyclerView.setAdapter(areaListAdapter);

        return root;
    }
}