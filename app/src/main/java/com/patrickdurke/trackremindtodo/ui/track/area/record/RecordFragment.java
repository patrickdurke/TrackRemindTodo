package com.patrickdurke.trackremindtodo.ui.track.area.record;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.patrickdurke.trackremindtodo.R;

public class RecordFragment extends Fragment {

    private RecordViewModel recordViewModel;
    private RecyclerView recyclerView;
    private EntryListAdapter entryListAdapter;
    private int selectedRecordId;
    private int selectedAreaId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        entryListAdapter = new EntryListAdapter(selectedAreaId);

        recordViewModel = new ViewModelProvider(this).get(RecordViewModel.class);
        recordViewModel.init();

        selectedRecordId = getArguments().getInt("selectedRecordId");
        selectedAreaId = getArguments().getInt("selectedAreaId");
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
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem item = menu.findItem(R.id.action_add_parameter);
        item.setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_parameter) {
            RecordFragmentDirections.ActionTrackAreaRecordFragmentToParameterFragment action
                    = RecordFragmentDirections.actionTrackAreaRecordFragmentToParameterFragment(selectedAreaId);
            NavHostFragment.findNavController(this).navigate(action);
            return true;
        }
        // Default
        return super.onOptionsItemSelected(item);
        /*When you successfully handle a menu item, return true.
        If you don't handle the menu item, you should call the superclass implementation of onOptionsItemSelected()
        (the default implementation returns false).
        https://developer.android.com/guide/topics/ui/menus*/
    }
}