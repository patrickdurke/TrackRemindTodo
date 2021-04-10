package com.patrickdurke.trackremindtodo.ui.track.area;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.patrickdurke.trackremindtodo.R;

public class AreaFragment extends Fragment {

    private AreaViewModel areaViewModel;
    private RecyclerView recyclerView;
    private RecordListAdapter recordListAdapter;
    private int selectedAreaId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        recordListAdapter = new RecordListAdapter();

        areaViewModel = new ViewModelProvider(this).get(AreaViewModel.class);
        areaViewModel.init();
        selectedAreaId = getArguments().getInt("selectedAreaId");
        //set observer on repository data and ensure update adapter data on changed repository data
        areaViewModel.getRecordListLiveData(selectedAreaId).observe(this, recordList -> {
            if (recordList != null) {
                recordListAdapter.setRecordList(recordList);
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        areaViewModel =
                new ViewModelProvider(this).get(AreaViewModel.class);
        View root = inflater.inflate(R.layout.track_area_fragment, container, false);
        final TextView textView = root.findViewById(R.id.text_track_area);

        areaViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // create RecyclerView
        recyclerView = root.findViewById(R.id.track_area_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(recordListAdapter);

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
            AreaFragmentDirections.ActionTrackAreaFragmentToParameterFragment action
                    = AreaFragmentDirections.actionTrackAreaFragmentToParameterFragment(selectedAreaId);
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