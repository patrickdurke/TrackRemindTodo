package com.patrickdurke.trackremindtodo.ui.track.area;

import com.patrickdurke.trackremindtodo.ui.track.area.record.RecordEntry;

import java.util.List;

public class Record {
    private int id;
    private int areaId;
    private long timeStamp;

    private List<RecordEntry> recordEntryList;

    public Record() {
    }

    public Record(long timeStamp, int areaId, List<RecordEntry> recordEntryList) {
        this.timeStamp = timeStamp;
        this.areaId = areaId;
        this.recordEntryList = recordEntryList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getAreaId() {
        return areaId;
    }

    public List<RecordEntry> getRecordEntryList() {
   return recordEntryList;
    }

    public void setRecordEntryList(List<RecordEntry> recordEntryList) {
        this.recordEntryList = recordEntryList;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}
