package com.patrickdurke.trackremindtodo.ui.track;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.patrickdurke.trackremindtodo.R;

import java.util.ArrayList;
import java.util.List;

public class AreaListAdapter extends RecyclerView.Adapter<TrackRecyclerViewHolder> {
    private List<Area> areaList = new ArrayList<>();

    @Override
    public int getItemViewType(final int position) {
        return R.layout.track_area_frame_textview;
    }

    @NonNull
    @Override
    public TrackRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new TrackRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackRecyclerViewHolder holder, int position) {
       String areaName = areaList.get(position).getName();
        holder.getView().setText(areaName);
        int selectedAreaId = areaList.get(position).getId();

        NavDirections navDirections = TrackFragmentDirections.actionNavTrackToAreaTabFragment(selectedAreaId, areaName);

        holder.itemView.setOnClickListener(v -> Navigation.findNavController(v).navigate(navDirections));
    }

    @Override
    public int getItemCount() {
        return areaList.size();
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
        notifyDataSetChanged();
    }
}