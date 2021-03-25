package com.patrickdurke.trackremindtodo.ui.track;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.patrickdurke.trackremindtodo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrackingAreaListAdapter extends RecyclerView.Adapter<TrackRecyclerViewHolder> {
    private List<TrackingArea> trackingAreaList = new ArrayList<>();

    @Override
    public int getItemViewType(final int position) {
        return R.layout.track_frame_textview;
    }

    @NonNull
    @Override
    public TrackRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new TrackRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackRecyclerViewHolder holder, int position) {
        holder.getView().setText(trackingAreaList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return trackingAreaList.size();
    }

    public void setTrackingAreaList(List<TrackingArea> trackingAreaList) {
        this.trackingAreaList = trackingAreaList;
        notifyDataSetChanged();
    }
}