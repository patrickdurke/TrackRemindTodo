package com.patrickdurke.trackremindtodo.ui.track.track_area;

public class TrackingAreaRecord {
    private final int id;
    private String timeStampString;
    private final int parentId;
    // NEXT LEVEL TODO: List of record entry (id + type + value + unit)

    public TrackingAreaRecord(int id, String timeStampString, int parentId) {
        this.id = id;
        this.timeStampString = timeStampString;
        this.parentId = parentId;
    }

    public int getId() {
        return id;
    }

    public String getTimeStampString() {
        return timeStampString;
    }

    public void setTimeStampString(String timeStampString) {
        this.timeStampString = timeStampString;
    }
}
