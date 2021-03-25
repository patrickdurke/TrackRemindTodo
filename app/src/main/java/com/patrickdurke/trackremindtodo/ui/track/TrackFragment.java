package com.patrickdurke.trackremindtodo.ui.track;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.patrickdurke.trackremindtodo.R;

public class TrackFragment extends Fragment {

    private TrackViewModel trackViewModel;

    // add RecyclerView member
    private RecyclerView recyclerView;

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
        recyclerView.setAdapter(new TrackingAreaListAdapter());

        return root;
    }
}