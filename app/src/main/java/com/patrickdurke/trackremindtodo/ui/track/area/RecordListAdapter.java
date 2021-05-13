package com.patrickdurke.trackremindtodo.ui.track.area;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.patrickdurke.trackremindtodo.R;
import com.patrickdurke.trackremindtodo.ui.track.area.record.RecordEntry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class RecordListAdapter extends RecyclerView.Adapter<AreaRecyclerViewHolder> { //ParentItemAdapter
    private List<Record> recordList = new ArrayList<>();
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private final SimpleDateFormat mFormat = new SimpleDateFormat("dd MMM yyy HH.mm.ss", Locale.ENGLISH);

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

        long millis = (long) recordList.get(position).getTimeStamp() * 1000L;
        String format = mFormat.format(new Date(millis));

        holder.getView().setText(format);
        Record selectedRecord = recordList.get(position);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(holder.getChildRecyclerView().getContext(), LinearLayoutManager.VERTICAL, false);

        List<RecordEntry> recordEntryList = new ArrayList<>();
        Map<String, RecordEntry> recordEntryMap = (Map<String, RecordEntry>) selectedRecord.getRecordEntryMap();

        for (Map.Entry mapEntry : recordEntryMap.entrySet())
            recordEntryList.add((RecordEntry)mapEntry.getValue());

        layoutManager.setInitialPrefetchItemCount(recordEntryList.size());

        RecordListChildAdapter recordListChildAdapter = new RecordListChildAdapter(recordEntryList);
        holder.getChildRecyclerView().setLayoutManager(layoutManager);

        holder.getChildRecyclerView().setAdapter(recordListChildAdapter);
        holder.getChildRecyclerView().setRecycledViewPool(viewPool);

        AreaTabFragmentDirections.ActionAreaTabFragmentToTrackAreaRecordFragment action
                = AreaTabFragmentDirections.actionAreaTabFragmentToTrackAreaRecordFragment(selectedRecord.getId(), selectedRecord.getAreaId());
                /*  "In general, you should strongly prefer passing only the minimal amount of data between destinations.
                For example, you should pass a key to retrieve an object rather than passing the object itself,
                as the total space for all saved states is limited on Android."
                https://developer.android.com/guide/navigation/navigation-pass-data */

       holder.itemView.setOnClickListener(v -> Navigation.findNavController(v).navigate(action));

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
