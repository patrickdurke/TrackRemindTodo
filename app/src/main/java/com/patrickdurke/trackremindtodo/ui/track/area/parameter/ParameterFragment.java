package com.patrickdurke.trackremindtodo.ui.track.area.parameter;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.patrickdurke.trackremindtodo.MainActivity;
import com.patrickdurke.trackremindtodo.R;
import com.patrickdurke.trackremindtodo.ui.track.TrackFragment;
import com.patrickdurke.trackremindtodo.ui.track.TrackFragmentDirections;

public class ParameterFragment extends Fragment {

    private int selectedAreaId;

    private boolean addModeFlag;

    private ParameterViewModel parameterViewModel;

    private EditText parameterName;
    private EditText parameterType;
    private EditText parameterUnit;
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

        return inflater.inflate(R.layout.track_area_parameter_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        parameterViewModel = new ViewModelProvider(this).get(ParameterViewModel.class);

        View view = getView();

        assert view != null; //https://stackoverflow.com/questions/33671216/findviewbyid-may-produce-nullpointerexception
        parameterName = view.findViewById(R.id.text_track_area_parameter_modify_name);
        parameterType = view.findViewById(R.id.text_track_area_parameter_modify_type);
        parameterUnit = view.findViewById(R.id.text_track_area_parameter_modify_unit);

        modifyButton = view.findViewById(R.id.button_track_area_parameter);
        setAddMode(addModeFlag);

        modifyButton.setOnClickListener(v -> {
            String name = parameterName.getText().toString();
            String type = parameterType.getText().toString();
            String unit = parameterUnit.getText().toString();
            Parameter parameter = new Parameter(name, type, unit, selectedAreaId);

            if(addModeFlag) {
                parameterViewModel.addParameter(parameter);
                Toast.makeText(getActivity(), name + " parameter was added" , Toast.LENGTH_LONG).show();
                setAddMode(false);
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
            parameterType.setText("");
            parameterUnit.setText("");
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
        fab.setOnClickListener(v -> setAddMode(true));
    }

}