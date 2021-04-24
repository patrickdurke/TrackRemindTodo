package com.patrickdurke.trackremindtodo.ui.track.area;

import com.patrickdurke.trackremindtodo.ui.track.area.record.Entry;

import java.util.List;

public class Record {
    private int id;
    private int areaId;
    private String timeStampString;

    private List<Entry> entryList;

    public Record() {
    }

    public Record(String timeStampString, int areaId, List<Entry> entryList) {
        this.timeStampString = timeStampString;
        this.areaId = areaId;
        this.entryList = entryList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimeStampString() {
        return timeStampString;
    }

    public void setTimeStampString(String timeStampString) {
        this.timeStampString = timeStampString;
    }

    public int getAreaId() {
        return areaId;
    }

    public List<Entry> getEntryList() {
   return entryList;
    }

    public void setEntryList(List<Entry> entryList) {
        this.entryList = entryList;
    }
}
