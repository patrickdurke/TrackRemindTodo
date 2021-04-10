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
import android.widget.TextView;

import com.patrickdurke.trackremindtodo.R;
import com.patrickdurke.trackremindtodo.ui.track.area.record.RecordFragmentDirections;

public class EntryFragment extends Fragment {

    private EntryViewModel entryViewModel;
    private int selectedEntryId;
    private int selectedAreaId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        entryViewModel = new ViewModelProvider(this).get(EntryViewModel.class);
        //entryViewModel.init();

        selectedEntryId = getArguments().getInt("selectedEntryId");
        selectedAreaId = getArguments().getInt("selectedAreaId");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
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
                    = EntryFragmentDirections.actionEntryFragmentToParameterFragment(selectedAreaId);
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