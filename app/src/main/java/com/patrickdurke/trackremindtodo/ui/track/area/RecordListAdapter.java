package com.patrickdurke.trackremindtodo.ui.track.area;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.patrickdurke.trackremindtodo.R;

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

        Record selectedRecord = recordList.get(position);

        AreaFragmentDirections.ActionTrackAreaFragmentToTrackAreaRecordFragment action
                = AreaFragmentDirections.actionTrackAreaFragmentToTrackAreaRecordFragment(selectedRecord.getId(), selectedRecord.getAreaId());
                /*  "In general, you should strongly prefer passing only the minimal amount of data between destinations.
                For example, you should pass a key to retrieve an object rather than passing the object itself, as the total space for all saved states is limited on Android."
                    https://developer.android.com/guide/navigation/navigation-pass-data */

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
