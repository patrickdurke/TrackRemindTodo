package com.patrickdurke.trackremindtodo.ui.track.area;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.patrickdurke.trackremindtodo.R;
import com.patrickdurke.trackremindtodo.ui.track.TrackFragmentDirections;

import java.util.ArrayList;
import java.util.List;

public class RecordListAdapter extends RecyclerView.Adapter<AreaRecyclerViewHolder> {
    private List<Record> recordList = new ArrayList<>();

    @Override
    public int getItemViewType(final int position) {
        return R.layout.track_area_record_frame_textview;
    }

    @NonNull
    @Override
    public AreaRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new AreaRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AreaRecyclerViewHolder holder, int position) {
        holder.getView().setText(recordList.get(position).getTimeStampString());
        //String selectedItem = recordList.get(position).getTimeStampString();
        int selectedRecordId = recordList.get(position).getId();
        AreaFragmentDirections.ActionTrackAreaFragmentToTrackAreaRecordFragment action = AreaFragmentDirections.actionTrackAreaFragmentToTrackAreaRecordFragment(selectedRecordId);
        holder.itemView.setOnClickListener(v ->
        Navigation.findNavController(v).navigate(action));
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
        notifyDataSetChanged();
    }
}
