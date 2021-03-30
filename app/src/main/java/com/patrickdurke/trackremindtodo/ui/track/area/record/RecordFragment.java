package com.patrickdurke.trackremindtodo.ui.track.area.record;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.patrickdurke.trackremindtodo.R;

public class RecordFragment extends Fragment {

    private RecordViewModel recordViewModel;
    private RecyclerView recyclerView;
    private EntryListAdapter entryListAdapter;
    private int selectedRecordId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        entryListAdapter = new EntryListAdapter();

        recordViewModel = new ViewModelProvider(this).get(RecordViewModel.class);
        recordViewModel.init();

        selectedRecordId = getArguments().getInt("selectedRecordId");
        //set observer on repository data and ensure update adapter data on changed repository data
      recordViewModel.getEntryListLiveData(selectedRecordId).observe(this, entryList -> {
          if (entryList != null) {
              entryListAdapter.setEntryList(entryList);
          }
      });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        System.out.println("RecordFragment onCreateView qqq");
        int selectedRecordId = getArguments().getInt("selectedRecordId");
        System.out.println("electedRecordId is: gggggg " + selectedRecordId);
        //trackAreaRecordViewModel = new ViewModelProvider(this).get(TrackAreaRecordViewModel.class);
        View root = inflater.inflate(R.layout.track_area_record_fragment, container, false);
        final TextView textView = root.findViewById(R.id.text_track_area_record);
        textView.setText("Record with id " + selectedRecordId + " was chosen but it has no entries");


        // create RecyclerView
        recyclerView = root.findViewById(R.id.track_area_record_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(entryListAdapter);


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }
}