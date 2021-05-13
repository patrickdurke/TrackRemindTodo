package com.patrickdurke.trackremindtodo.ui.track.area;

import com.patrickdurke.trackremindtodo.ui.track.area.record.RecordEntry;

import java.util.Map;

public class Record {
    private int id;
    private int areaId;
    private long timeStamp;

    private Map<String,RecordEntry> recordEntryMap;

    public Record() {
    }

    public Record(long timeStamp, int areaId, Map<String, RecordEntry> recordEntryMap) {
        this.timeStamp = timeStamp;
        this.areaId = areaId;
        this.recordEntryMap = recordEntryMap;
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

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public Map<String, RecordEntry> getRecordEntryMap() {
        return recordEntryMap;
    }

    public void setRecordEntryMap(Map<String, RecordEntry> recordEntryMap) {
        this.recordEntryMap = recordEntryMap;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}
