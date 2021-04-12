package com.patrickdurke.trackremindtodo.ui.track.area;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.patrickdurke.trackremindtodo.R;
import com.patrickdurke.trackremindtodo.ui.track.Area;
import com.patrickdurke.trackremindtodo.ui.track.area.parameter.Parameter;

public class AreaFragment extends Fragment {

    private int selectedAreaId;

    private boolean addModeFlag;

    private AreaViewModel areaViewModel;
    private RecyclerView recyclerViewItemList;
    private RecordListAdapter recordListAdapter;
    private View constraintLayoutItem;

    private EditText areaName;
    private EditText areaColor;
    private Button modifyButton;
    private FloatingActionButton fab;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        recordListAdapter = new RecordListAdapter();

        areaViewModel = new ViewModelProvider(this).get(AreaViewModel.class);
        areaViewModel.init();

        assert getArguments() != null;
        selectedAreaId = getArguments().getInt("selectedAreaId");

        //set observer on repository data and ensure update adapter data on changed repository data
        areaViewModel.getRecordListLiveData(selectedAreaId).observe(this, recordList -> {
            if (recordList != null) {
                recordListAdapter.setRecordList(recordList);
            }
        });

        fab = this.getActivity().findViewById(R.id.fab);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        areaViewModel =
                new ViewModelProvider(this).get(AreaViewModel.class);
        View root = inflater.inflate(R.layout.track_area_fragment, container, false);

        constraintLayoutItem = root.findViewById(R.id.layout_track_modify_area);
        // create RecyclerView
        recyclerViewItemList = root.findViewById(R.id.track_area_recyclerview);
        recyclerViewItemList.setHasFixedSize(true);
        recyclerViewItemList.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerViewItemList.setAdapter(recordListAdapter);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();
        assert view != null;
        areaName = view.findViewById(R.id.text_track_modify_area_name);
        areaColor = view.findViewById(R.id.text_track_modify_area_color);

        modifyButton = view.findViewById(R.id.button_track_area);
        setAddMode(addModeFlag);

        modifyButton.setOnClickListener(v -> {
            String name = areaName.getText().toString();
            String color = areaColor.getText().toString();

            Area area = new Area(name, color);

            if(addModeFlag) {
                areaViewModel.addArea(area);
                Toast.makeText(getActivity(), name + " area was added" , Toast.LENGTH_LONG).show();
                setAddMode(false);
                setOnclickListener(fab);
            } else {
                Toast.makeText(getActivity(), name + " area was edited (NOT IMPLEMENTED)" /* TODO */ , Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        setOnclickListener(fab);

        setAddMode(selectedAreaId == -1);
        if(addModeFlag){
            recyclerViewItemList.setVisibility(View.INVISIBLE);
            constraintLayoutItem.setVisibility(View.VISIBLE);
        } else {
            recyclerViewItemList.setVisibility(View.VISIBLE);
            constraintLayoutItem.setVisibility(View.INVISIBLE);
        }
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
                    = AreaFragmentDirections.actionTrackAreaFragmentToParameterFragment(selectedAreaId, true);
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

    private void setAddMode(Boolean addMode) {
        addModeFlag = addMode;
        if (addModeFlag){
            areaName.setText("");
            areaColor.setText("");
            modifyButton.setText(R.string.add);
            fab.hide();
            fab.setOnClickListener(null);
            recyclerViewItemList.setVisibility(View.INVISIBLE);
            constraintLayoutItem.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(), " in add mode" , Toast.LENGTH_LONG).show();
        }
        else {
            modifyButton.setText(R.string.edit);
            fab.show();
        }
    }

    private void setOnclickListener(FloatingActionButton fab){
        fab.setOnClickListener(v -> {
            if(selectedAreaId == -1)
                setAddMode(true);
            else {
                AreaFragmentDirections.ActionTrackAreaFragmentToTrackAreaRecordFragment action
                        = AreaFragmentDirections.actionTrackAreaFragmentToTrackAreaRecordFragment(-1, selectedAreaId);
                Toast.makeText(AreaFragment.this.getActivity(), " AreaFragment is listening", Toast.LENGTH_LONG).show();

                fab.setOnClickListener(null);

                assert getParentFragment() != null;
                NavHostFragment.findNavController(getParentFragment()).navigate(action);
            }
        });
    }
}