package com.patrickdurke.trackremindtodo.ui.track.area.parameter;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.patrickdurke.trackremindtodo.R;

public class ParameterFragment extends Fragment {

    private ParameterViewModel parameterViewModel;
    private EditText parameterName;
    private EditText parameterType;
    private EditText parameterUnit;
    private Button addButton;
    private int selectedAreaId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parameterViewModel = new ViewModelProvider(this).get(ParameterViewModel.class);
        parameterViewModel.init();
        selectedAreaId = getArguments().getInt("selectedAreaId");
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

        parameterName = getView().findViewById(R.id.text_track_area_parameter_name);
        parameterType = getView().findViewById(R.id.text_track_area_parameter_type);
        parameterUnit = getView().findViewById(R.id.text_track_area_parameter_unit);
        addButton = getView().findViewById(R.id.button_track_area_parameter_add);

        addButton.setOnClickListener(v -> {
            String name = parameterName.getText().toString();
            String type = parameterType.getText().toString();
            String unit = parameterUnit.getText().toString();
            Parameter parameter = new Parameter(name, type, unit, selectedAreaId);
            parameterViewModel.addParameter(parameter);
            Toast.makeText(getActivity(), name + " parameter was added" , Toast.LENGTH_LONG).show();

        });

    }

}