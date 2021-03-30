package com.patrickdurke.trackremindtodo.ui.track.area.record.entry;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.patrickdurke.trackremindtodo.R;

public class EntryFragment extends Fragment {

    private EntryViewModel entryViewModel;
    private int selectedEntryId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        entryViewModel = new ViewModelProvider(this).get(EntryViewModel.class);
        //entryViewModel.init();

        selectedEntryId = getArguments().getInt("selectedEntryId");
        //set observer on repository data and ensure update adapter data on changed repository data
      //  entryViewModel.setEntry(selectedItemId);
      // entryViewModel.getEntryLiveData(selectedItemId).observe(this, entry -> {
      //      if (entry != null) {
      //          entryAdapter.setEntry(entryList);
      //      }
      //  });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        System.out.println("EntryFragment onCreateView qqq");
        selectedEntryId = getArguments().getInt("selectedEntryId");
        View root = inflater.inflate(R.layout.track_area_record_entry_fragment, container, false);
       final TextView textView = root.findViewById(R.id.text_track_area_record_entry);
       textView.setText("Entry with id " + selectedEntryId + " was chosen");
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        entryViewModel = new ViewModelProvider(this).get(EntryViewModel.class);
        // TODO: Use the ViewModel
    }

}