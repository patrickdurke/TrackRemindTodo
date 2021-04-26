package com.patrickdurke.trackremindtodo.ui.track.area;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.patrickdurke.trackremindtodo.R;
import com.patrickdurke.trackremindtodo.ui.track.area.parameter.Parameter;
import com.patrickdurke.trackremindtodo.ui.track.area.parameter.ParameterRepository;
import com.patrickdurke.trackremindtodo.ui.track.area.record.RecordEntry;

import java.util.List;

public class RecordListChildAdapter extends RecyclerView.Adapter<RecordListChildAdapter.ChildViewHolder> {

    private final List<RecordEntry> recordEntryList;

    ParameterRepository parameterRepository = ParameterRepository.getInstance();
    RecordRepository recordRepository = RecordRepository.getInstance();

    RecordListChildAdapter(List<RecordEntry> recordEntryList) {
        this.recordEntryList = recordEntryList;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.child_item, viewGroup, false);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder childViewHolder, int position) {

        RecordEntry recordEntry = recordEntryList.get(position);
        Parameter parameter = parameterRepository.getParameter(recordEntry.getParameterId());
        String entryString = parameter.getName() + ": " + recordEntry.getValue() + " " + parameter.getUnit();

        childViewHolder.ChildItemTitle.setText(entryString);

        int areaId = recordRepository.getAreaId(recordEntry.getRecordId());
        AreaTabFragmentDirections.ActionAreaTabFragmentToTrackAreaRecordFragment action
                = AreaTabFragmentDirections.actionAreaTabFragmentToTrackAreaRecordFragment(recordEntry.getRecordId(), areaId);

        childViewHolder.itemView.setOnClickListener(v -> Navigation.findNavController(v).navigate(action));

    }

    @Override
    public int getItemCount() {
        if (recordEntryList != null)
            return recordEntryList.size();

        return 0;
    }

    class ChildViewHolder extends RecyclerView.ViewHolder {
        TextView ChildItemTitle;

        ChildViewHolder(View itemView) {
            super(itemView);
            ChildItemTitle = itemView.findViewById(R.id.text_child_item);
        }
    }
}