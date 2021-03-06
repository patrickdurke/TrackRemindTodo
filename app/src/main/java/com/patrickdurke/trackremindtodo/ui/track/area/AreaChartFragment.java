package com.patrickdurke.trackremindtodo.ui.track.area;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.patrickdurke.trackremindtodo.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AreaChartFragment extends Fragment {

    public static final String ARG_SELECTED_AREA_ID = "selectedAreaId";
    private int selectedAreaId;

    private AreaChartViewModel areaChartViewModel;
    private LineChart lineChart;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        selectedAreaId = args.getInt(ARG_SELECTED_AREA_ID);

        ((FloatingActionButton) this.getActivity().findViewById(R.id.fab)).hide();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.area_chart_fragment, container, false);
        lineChart = root.findViewById(R.id.chart_line_track_area);
        configureLineChart();

        areaChartViewModel = new ViewModelProvider(this).get(AreaChartViewModel.class);

        Spinner spinner = (Spinner) root.findViewById(R.id.spinner_track_area_parameters_chart);

        String[] parameterNames = areaChartViewModel.getQuantitativeParameterStrings(selectedAreaId);

        setLineData(parameterNames[0]); //parameter at index 0 is currently the default parameter for the chart

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, parameterNames);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.simple_spinner_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setLineData(parameterNames[position]);
                lineChart.invalidate();
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        return root;
    }

    //https://learntodroid.com/how-to-display-a-line-chart-in-your-android-app/
    private void setLineData(String parameterName) {
        ArrayList<Entry> chartEntries = areaChartViewModel.getChartEntries(parameterName);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        LineDataSet lineDataSet = new LineDataSet(chartEntries, parameterName + "");
        lineDataSet.setDrawCircles(true);
        lineDataSet.setCircleRadius(4);
        lineDataSet.setDrawValues(false);
        lineDataSet.setLineWidth(3);
        lineDataSet.setColor(Color.BLACK);
        lineDataSet.setCircleColor(Color.BLUE);
        dataSets.add(lineDataSet);

        LineData lineData = new LineData(dataSets);

        if (chartEntries.size() > 0)
            lineChart.setData(lineData);
        else lineChart.setData(null);

        lineChart.setNoDataText("No entries for " + parameterName.toLowerCase() + " yet");
        lineChart.setNoDataTextColor(ContextCompat.getColor(getActivity(), R.color.black));
        Paint paint = lineChart.getPaint(Chart.PAINT_INFO);
        paint.setTextSize(60f);
    }

    private void configureLineChart() {
        /*Description description = new Description();
        description.setText("Stock Price History");
        description.setTextSize(28);
        lineChart.setDescription(description);*/
        lineChart.setDescription(null);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            private final SimpleDateFormat mFormat = new SimpleDateFormat("dd MMM", Locale.ENGLISH);

            @Override
            public String getFormattedValue(float value) {
                long millis = (long) value * 1000L;
                return mFormat.format(new Date(millis));
            }
        });
    }

}