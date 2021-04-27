package com.patrickdurke.trackremindtodo.ui.track.area.record;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.patrickdurke.trackremindtodo.R;
import com.patrickdurke.trackremindtodo.ui.track.area.parameter.Parameter;
import com.patrickdurke.trackremindtodo.ui.track.area.parameter.ParameterRepository;

import java.util.ArrayList;
import java.util.List;

public class EntryListAdapter extends RecyclerView.Adapter<RecordRecyclerViewHolder> {
    private List<RecordEntry> recordEntryList = new ArrayList<>();
    private int selectedAreaId;

    ParameterRepository parameterRepository = ParameterRepository.getInstance();

    public EntryListAdapter(int selectedAreaId) {
        this.selectedAreaId = selectedAreaId;
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.track_area_record_entry_frame_textview;
    }

    @NonNull
    @Override
    public RecordRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new RecordRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordRecyclerViewHolder holder, int position) {
        RecordEntry recordEntry = recordEntryList.get(position);
        Parameter parameter = parameterRepository.getParameter(recordEntry.getParameterId());
        String entryString = parameter.getName() + ": " + recordEntry.getValue() + " " + parameter.getUnit();
        holder.getView().setText(entryString);
    }

    @Override
    public int getItemCount() {
        return recordEntryList.size();
    }

    public void setRecordEntryList(List<RecordEntry> recordEntryList) {
        this.recordEntryList = recordEntryList;
        notifyDataSetChanged();
    }
}
