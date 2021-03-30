package com.patrickdurke.trackremindtodo.ui.track.area.record;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.patrickdurke.trackremindtodo.R;

public class RecordRecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView view;
    public RecordRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView.findViewById(R.id.trackAreaRecordEntryText);
    }

    public TextView getView(){
        return view;
    }
}

