package com.patrickdurke.trackremindtodo.ui.track.area;

public class Record {
    private int id;
    private final int areaId;
    private String timeStampString;

    public Record(String timeStampString, int areaId) {
        this.timeStampString = timeStampString;
        this.areaId = areaId;
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
}
