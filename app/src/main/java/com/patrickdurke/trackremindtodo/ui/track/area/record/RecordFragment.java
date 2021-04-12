package com.patrickdurke.trackremindtodo.ui.track.area.record;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.patrickdurke.trackremindtodo.MainActivity;
import com.patrickdurke.trackremindtodo.R;
import com.patrickdurke.trackremindtodo.ui.track.area.AreaFragment;
import com.patrickdurke.trackremindtodo.ui.track.area.AreaFragmentDirections;
import com.patrickdurke.trackremindtodo.ui.track.area.Record;
import com.patrickdurke.trackremindtodo.ui.track.area.parameter.Parameter;

public class RecordFragment extends Fragment {

    private int selectedAreaId;
    private int selectedRecordId;

    private boolean addModeFlag;

    private RecordViewModel recordViewModel;
    private RecyclerView recyclerViewItemList;
    private EntryListAdapter entryListAdapter;
    private View constraintLayoutItem;
    private EditText recordTimestamp;

    private Button modifyButton;
    private FloatingActionButton fab;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        entryListAdapter = new EntryListAdapter(selectedRecordId);

        recordViewModel = new ViewModelProvider(this).get(RecordViewModel.class);
        recordViewModel.init();

        assert getArguments() != null;
        selectedRecordId = getArguments().getInt("selectedRecordId");
        selectedAreaId = getArguments().getInt("selectedAreaId");

        addModeFlag = selectedRecordId == -1;

        //set observer on repository data and ensure update adapter data on changed repository data
        recordViewModel.getEntryListLiveData(selectedRecordId).observe(this, entryList -> {
          if (entryList != null) {
              entryListAdapter.setEntryList(entryList);
          }
      });

        assert getActivity() != null;
        fab = this.getActivity().findViewById(R.id.fab);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.track_area_record_fragment, container, false);

        constraintLayoutItem = root.findViewById(R.id.layout_track_area_modify_record);
        // create RecyclerView
        recyclerViewItemList = root.findViewById(R.id.track_area_record_recyclerview);
        recyclerViewItemList.setHasFixedSize(true);
        recyclerViewItemList.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerViewItemList.setAdapter(entryListAdapter);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();
        assert view != null;
        recordTimestamp = view.findViewById(R.id.text_track_area_modify_record_timestamp);
        modifyButton = view.findViewById(R.id.button_track_area_record);

        modifyButton.setOnClickListener(v -> {
            String timeStamp = recordTimestamp.getText().toString();

            Record record = new Record(timeStamp, selectedAreaId);

            if(addModeFlag) {
                recordViewModel.addRecord(record);
                Toast.makeText(getActivity(), timeStamp + "record was added" , Toast.LENGTH_LONG).show();
                setAddMode(false);
                setOnclickListener(fab);
            } else {
                Toast.makeText(getActivity(), timeStamp + "record was edited (NOT IMPLEMENTED)" /* TODO*/ , Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        setOnclickListener(fab);

        setAddMode(selectedRecordId == -1);
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

    private void setAddMode(Boolean addMode) {
        addModeFlag = addMode;
        if (addModeFlag){
            recordTimestamp.setText("");
            modifyButton.setText(R.string.add);
            fab.hide();
            fab.setOnClickListener(null);
            recyclerViewItemList.setVisibility(View.INVISIBLE);
            constraintLayoutItem.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(), "in add mode", Toast.LENGTH_LONG).show();
        }
        else {
            modifyButton.setText(R.string.edit);
            fab.show();
        }
    }

    private void setOnclickListener(FloatingActionButton fab){
        fab.setOnClickListener(v -> {
            if(selectedRecordId == -1)
                setAddMode(true);
            else {
                RecordFragmentDirections.ActionTrackAreaRecordFragmentToEntryFragment action
                        = RecordFragmentDirections.actionTrackAreaRecordFragmentToEntryFragment(-1, selectedAreaId, selectedRecordId);
                Toast.makeText(RecordFragment.this.getActivity(), "RecordFragment is listening", Toast.LENGTH_LONG).show();

                fab.setOnClickListener(null);

                assert getParentFragment() != null;
                NavHostFragment.findNavController(getParentFragment()).navigate(action);
            }
        });
    }
}