package com.patrickdurke.trackremindtodo.ui.track.area.record;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.patrickdurke.trackremindtodo.R;
import com.patrickdurke.trackremindtodo.ui.track.area.Record;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecordFragment extends Fragment {

    private int selectedAreaId;
    private int selectedRecordId;

    private RecordViewModel recordViewModel;
    private RecyclerView recyclerViewItemList;
    private EntryListAdapter entryListAdapter;

    private FloatingActionButton fab;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        entryListAdapter = new EntryListAdapter(selectedRecordId);

        Bundle arguments = getArguments();
        assert arguments != null;
        selectedRecordId = arguments.getInt(getString(R.string.selectedRecordId));
        selectedAreaId = arguments.getInt(getString(R.string.selectedAreaId));

        recordViewModel = new ViewModelProvider(this).get(RecordViewModel.class);
        recordViewModel.init(selectedAreaId, selectedRecordId);

        //set observer on repository data and ensure update adapter data on changed repository data
        recordViewModel.getEntryListLiveData().observe(this, entryMap -> {
          if (entryMap != null) {
              List<RecordEntry> recordEntryList = new ArrayList<>();
              for (Map.Entry mapEntry : entryMap.entrySet()) {
                  recordEntryList.add((((RecordEntry)mapEntry.getValue())));
              }
              entryListAdapter.setRecordEntryList(recordEntryList);
          }
      });

        assert getActivity() != null;
        fab = this.getActivity().findViewById(R.id.fab);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.track_area_record_fragment, container, false);

        // create RecyclerView
        recyclerViewItemList = root.findViewById(R.id.track_area_record_recyclerview);
        recyclerViewItemList.setHasFixedSize(true);
        recyclerViewItemList.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerViewItemList.setAdapter(entryListAdapter);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        setOnclickListener(fab);
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
                    = RecordFragmentDirections.actionTrackAreaRecordFragmentToParameterFragment(selectedAreaId, true);
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

    private void setOnclickListener(FloatingActionButton fab){
        fab.setOnClickListener(v -> {
                RecordFragmentDirections.ActionTrackAreaRecordFragmentToEntryFragment action
                        = RecordFragmentDirections.actionTrackAreaRecordFragmentToEntryFragment(-1, selectedAreaId, selectedRecordId);
                fab.setOnClickListener(null);

                assert getParentFragment() != null;
                NavHostFragment.findNavController(getParentFragment()).navigate(action);
        });
    }
}