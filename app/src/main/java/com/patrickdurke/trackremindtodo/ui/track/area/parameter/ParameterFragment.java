package com.patrickdurke.trackremindtodo.ui.track.area.parameter;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
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

public class ParameterFragment extends Fragment {

    private int selectedAreaId;

    private boolean addModeFlag;

    private ParameterViewModel parameterViewModel;

    private EditText parameterName;
    private EditText parameterUnit;
    private String parameterType;
    private Button modifyButton;
    private FloatingActionButton fab;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parameterViewModel = new ViewModelProvider(this).get(ParameterViewModel.class);
        parameterViewModel.init();

        Bundle arguments = getArguments();
        assert arguments != null;
        selectedAreaId = arguments.getInt(getString(R.string.selectedAreaId));
        addModeFlag = arguments.getBoolean(getString(R.string.addNewParameter));

        fab = this.getActivity().findViewById(R.id.fab);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.track_area_parameter_fragment, container, false);
        Spinner parameterTypeSpinner = (Spinner) root.findViewById(R.id.spinner_track_area_parameter_modify_type);

        String[] items = new String[] { "number", "text" }; //TODO: add "duration" later

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.simple_spinner_item, items);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        parameterTypeSpinner.setAdapter(adapter);

        parameterTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parameterType = items[position]+"ValueType";
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        parameterViewModel = new ViewModelProvider(this).get(ParameterViewModel.class);

        View view = getView();

        assert view != null; //https://stackoverflow.com/questions/33671216/findviewbyid-may-produce-nullpointerexception
        parameterName = view.findViewById(R.id.text_track_area_parameter_modify_name);
        parameterUnit = view.findViewById(R.id.text_track_area_parameter_modify_unit);

        modifyButton = view.findViewById(R.id.button_track_area_parameter);
        setAddMode(addModeFlag);

        modifyButton.setOnClickListener(v -> {
            String name = parameterName.getText().toString();
            String unit = parameterUnit.getText().toString();
            Parameter parameter = new Parameter(name, parameterType, unit, selectedAreaId);

            if(addModeFlag) {
                parameterViewModel.addParameter(parameter);
                Toast.makeText(getActivity(), name + " parameter was added" , Toast.LENGTH_LONG).show();
                setAddMode(false);

                //Sending user back to wherever user came from
                NavHostFragment.findNavController(this).popBackStack();

            } else {
                Toast.makeText(getActivity(), name + " parameter was edited (NOT IMPLEMENTED)" /* TODO */ , Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        setOnclickListener(fab);
    }

    private void setAddMode(Boolean addMode) {
        addModeFlag = addMode;
        if (addModeFlag){
            parameterName.setText("");
            parameterUnit.setText("");
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