package com.patrickdurke.trackremindtodo.ui.track.area.record.entry;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.patrickdurke.trackremindtodo.MainActivity;
import com.patrickdurke.trackremindtodo.R;
import com.patrickdurke.trackremindtodo.ui.track.area.AreaFragment;
import com.patrickdurke.trackremindtodo.ui.track.area.AreaFragmentDirections;
import com.patrickdurke.trackremindtodo.ui.track.area.record.Entry;

public class EntryFragment extends Fragment {

    private EntryViewModel entryViewModel;
    private int selectedAreaId;
    private int selectedRecordId;
    private int selectedEntryId;

    private EditText entryValue;
    private EditText entryParameter;
    private int entryParameterId;

    private Button modifyButton;
    private boolean addModeFlag;
    private FloatingActionButton fab;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        entryViewModel = new ViewModelProvider(this).get(EntryViewModel.class);
        entryViewModel.init();

        assert getArguments() != null;
        selectedEntryId = getArguments().getInt("selectedEntryId"); //-1 for none
        selectedAreaId = getArguments().getInt("selectedAreaId");
        selectedRecordId = getArguments().getInt("selectedRecordId");

        fab = this.getActivity().findViewById(R.id.fab);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        assert getArguments() != null;
        selectedEntryId = getArguments().getInt("selectedEntryId");
        return inflater.inflate(R.layout.track_area_record_entry_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        entryViewModel = new ViewModelProvider(this).get(EntryViewModel.class);

        View view = getView();

        entryValue = view.findViewById(R.id.text_track_area_record_entry_modify_value);
        entryParameter = view.findViewById(R.id.text_track_area_record_entry_modify_parameter); // TODO dropdown something _ based on ID...

        modifyButton = view.findViewById(R.id.button_track_area_record_entry);
        setAddMode(selectedEntryId == -1);

        if(!addModeFlag){
            Entry entry = entryViewModel.getEntry(selectedEntryId);
            entryParameterId = entry.getParameterId();

            entryValue.setText(entry.getValue() + "");
            entryParameter.setText(entry.getParameterId() + "");
        }

        modifyButton.setOnClickListener(v -> {
            String value = entryValue.getText().toString();
            //entryParameterId = entryParameter.getText().toString();  // TODO dropdown something _ based on ID...
            Entry entry = new Entry(entryParameterId, value, selectedRecordId);

            if(addModeFlag) {
                entryViewModel.addEntry(entry);
                Toast.makeText(getActivity(), value + " entry value was added" , Toast.LENGTH_LONG).show();
                setAddMode(false);
            } else {
                Toast.makeText(getActivity(), value + " entry value was edited (NOT IMPLEMENTED)" /* TODO */ , Toast.LENGTH_LONG).show();
            }
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
            entryParameter.setText("");
            modifyButton.setText(R.string.add);

            fab.hide();
            fab.setOnClickListener(null);

            Toast.makeText(getActivity(), "in add mode", Toast.LENGTH_LONG).show();
        }
        else {
            modifyButton.setText(R.string.edit);

            fab.show();
            setOnclickListener(fab);
        }
    }

    private void setOnclickListener(FloatingActionButton fab){
        fab.setOnClickListener(v -> {
            setAddMode(true);
        });
    }
}