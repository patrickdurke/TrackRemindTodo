package com.patrickdurke.trackremindtodo.ui.track.track_area;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.patrickdurke.trackremindtodo.R;

import java.util.ArrayList;
import java.util.List;

public class TrackingAreaRecordListAdapter extends RecyclerView.Adapter<TrackAreaRecyclerViewHolder>  {
    private List<TrackingAreaRecord> trackingAreaRecordList = new ArrayList<>();

    @Override
    public int getItemViewType(final int position) {
        return R.layout.track_area_record_frame_textview;
    }

    @NonNull
    @Override
    public TrackAreaRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new TrackAreaRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackAreaRecyclerViewHolder holder, int position) {
        holder.getView().setText(trackingAreaRecordList.get(position).getTimeStampString());
        String selectedItem = trackingAreaRecordList.get(position).getTimeStampString();
        // NEXT LEVEL Initialize action
        // NEXT LEVEL holder.itemView.setOnClickListener(v ->
        // NEXT LEVEL Navigation.findNavController(v).navigate(action));
    }

    @Override
    public int getItemCount() {
        return trackingAreaRecordList.size();
    }

    public void setTrackingAreaRecordList(List<TrackingAreaRecord> trackingAreaRecordList) {
        this.trackingAreaRecordList = trackingAreaRecordList;
        notifyDataSetChanged();
    }
}
