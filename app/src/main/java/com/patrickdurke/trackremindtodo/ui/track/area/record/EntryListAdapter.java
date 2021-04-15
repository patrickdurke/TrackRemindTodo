package com.patrickdurke.trackremindtodo.ui.track.area.record;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.patrickdurke.trackremindtodo.R;
import com.patrickdurke.trackremindtodo.ui.track.area.parameter.Parameter;
import com.patrickdurke.trackremindtodo.ui.track.area.parameter.ParameterRepository;

import java.util.ArrayList;
import java.util.List;

public class EntryListAdapter extends RecyclerView.Adapter<RecordRecyclerViewHolder> {
    private List<Entry> entryList = new ArrayList<>();
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
        Entry entry = entryList.get(position);
        Parameter parameter = parameterRepository.getParameter(entry.getParameterId());
        String entryString = parameter.getName() + ": " + entry.getValue() + " " + parameter.getUnit();
        holder.getView().setText(entryString);


        RecordFragmentDirections.ActionTrackAreaRecordFragmentToEntryFragment action
                = RecordFragmentDirections.actionTrackAreaRecordFragmentToEntryFragment(entry.getId(), selectedAreaId, entry.getRecordId());
        holder.itemView.setOnClickListener(v -> Navigation.findNavController(v).navigate(action));
    }

    @Override
    public int getItemCount() {
        return entryList.size();
    }

    public void setEntryList(List<Entry> entryList) {
        this.entryList = entryList;
        notifyDataSetChanged();
    }
}
