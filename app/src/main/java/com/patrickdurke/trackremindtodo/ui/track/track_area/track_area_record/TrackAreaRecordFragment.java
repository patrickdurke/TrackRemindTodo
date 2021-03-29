package com.patrickdurke.trackremindtodo.ui.track.track_area.track_area_record;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.patrickdurke.trackremindtodo.R;

public class TrackAreaRecordFragment extends Fragment {

    public static TrackAreaRecordFragment newInstance() {
        return new TrackAreaRecordFragment();
    }
    private TrackAreaRecordViewModel trackAreaRecordViewModel;
    private RecyclerView recyclerView;
    // NEXT LEVEL    private TrackingAreaRecordEntryListAdapter trackingAreaRecordEntryListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //trackingAreaRecordListAdapter = new TrackingAreaRecordListAdapter();

        trackAreaRecordViewModel = new ViewModelProvider(this).get(TrackAreaRecordViewModel.class);
        trackAreaRecordViewModel.init();

        //set observer on repository data and ensure update adapter data on changed repository data
      // NEXT LEVEL  trackAreaRecordViewModel.getTrackingAreaRecordEntryListLiveData().observe(this, trackingAreaRecordEntryList -> {
      // NEXT LEVEL      if (trackingAreaRecordEntryList != null) {
      // NEXT LEVEL          trackingAreaRecordEntryListAdapter.setTrackingAreaRecordEntryList(trackingAreaRecordEntryList);
      // NEXT LEVEL      }
      // NEXT LEVEL  });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        String selectedItem = getArguments().getString("selectedItem");
        //trackAreaRecordViewModel = new ViewModelProvider(this).get(TrackAreaRecordViewModel.class);
        View root = inflater.inflate(R.layout.track_area_record_fragment, container, false);
        final TextView textView = root.findViewById(R.id.text_track_area_record);
        textView.setText(selectedItem);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }
}