package com.patrickdurke.trackremindtodo.ui.track.area;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.patrickdurke.trackremindtodo.R;

public class AreaRecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView view;
    private RecyclerView childRecyclerView;

    public AreaRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView.findViewById(R.id.trackAreaRecordText);
        childRecyclerView = itemView.findViewById(R.id.child_recyclerview);
    }

    public TextView getView(){
        return view;
    }

    public RecyclerView getChildRecyclerView() {
        return childRecyclerView;
    }
}
