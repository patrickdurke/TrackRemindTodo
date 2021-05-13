package com.patrickdurke.trackremindtodo.ui.track.area.record.entry;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.patrickdurke.trackremindtodo.R;
import com.patrickdurke.trackremindtodo.ui.track.TrackFragmentDirections;
import com.patrickdurke.trackremindtodo.ui.track.area.parameter.Parameter;
import com.patrickdurke.trackremindtodo.ui.track.area.record.RecordEntry;

import java.util.ArrayList;
import java.util.List;

public class EntryFragment extends Fragment {

    private EntryViewModel entryViewModel;
    private int selectedAreaId;
    private int selectedRecordId;
    private int selectedEntryId;

    private EditText entryValue;
    private int entryParameterId;
    private Button modifyButton;
    private boolean addModeFlag;
    private FloatingActionButton fab;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        Bundle arguments = getArguments();
        assert arguments != null;
        selectedAreaId = arguments.getInt(getString(R.string.selectedAreaId));
        selectedRecordId = arguments.getInt(getString(R.string.selectedRecordId)); //-1 for none
        selectedEntryId = arguments.getInt(getString(R.string.selectedEntryId)); //-1 for none

        fab = this.getActivity().findViewById(R.id.fab);

        entryViewModel = new ViewModelProvider(this).get(EntryViewModel.class);
        entryViewModel.init();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.track_area_record_entry_fragment, container, false);

        Spinner parameterTypeSpinner = root.findViewById(R.id.spinner_track_area_record_entry_modify_type);

        List<Parameter> parameterList = entryViewModel.getParameters(selectedAreaId);

        List<String> parameterNames = new ArrayList<>();
        for (Parameter parameter : parameterList)
            parameterNames.add(parameter.getName() + ": " + parameter.getUnit());
        parameterNames.add("Add new parameter");

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.simple_spinner_item, parameterNames);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        parameterTypeSpinner.setAdapter(adapter);

        parameterTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                if (position == parameterList.size()) { //The last element of parameterList is the "Add new parameter" option
                    NavDirections navDirections = EntryFragmentDirections.actionEntryFragmentToParameterFragment(selectedAreaId, true);
                    Navigation.findNavController(v).navigate(navDirections);
                } else {
                    entryParameterId = parameterList.get(position).getId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        assert getArguments() != null;
        selectedEntryId = getArguments().getInt(getString(R.string.selectedEntryId));
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        entryViewModel = new ViewModelProvider(this).get(EntryViewModel.class);

        View view = getView();

        entryValue = view.findViewById(R.id.text_track_area_record_entry_modify_value);

        modifyButton = view.findViewById(R.id.button_track_area_record_entry);
        setAddMode(selectedEntryId == -1);

        modifyButton.setOnClickListener(v -> {
            String value = entryValue.getText().toString();
            RecordEntry recordEntry = new RecordEntry(entryParameterId, value, selectedRecordId);

            entryViewModel.addEntry(recordEntry, selectedAreaId);
            setAddMode(false);

            //Sending user back to wherever user came from
            NavHostFragment.findNavController(this).popBackStack();
        });
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
            EntryFragmentDirections.ActionEntryFragmentToParameterFragment action
                    = EntryFragmentDirections.actionEntryFragmentToParameterFragment(selectedAreaId, true);
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
            entryValue.setText("");
            modifyButton.setText(R.string.add);
            fab.hide();
            fab.setOnClickListener(null);
        }
        else {
            modifyButton.setText(R.string.edit);

            fab.show();
            setOnclickListener(fab);
        }
    }

    private void setOnclickListener(FloatingActionButton fab){
        fab.setOnClickListener(v -> setAddMode(true));
    }
}