package com.patrickdurke.trackremindtodo.ui.track.track_area;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.patrickdurke.trackremindtodo.R;

public class TrackAreaRecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView view;
    public TrackAreaRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView.findViewById(R.id.trackAreaRecordText);
    }

    public TextView getView(){
        return view;
    }
}
