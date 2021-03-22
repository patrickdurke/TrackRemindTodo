package com.patrickdurke.trackremindtodo.ui.track;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.patrickdurke.trackremindtodo.R;

public class TrackRecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView view;
    public TrackRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView.findViewById(R.id.randomText);
    }

    public TextView getView(){
        return view;
    }
}
