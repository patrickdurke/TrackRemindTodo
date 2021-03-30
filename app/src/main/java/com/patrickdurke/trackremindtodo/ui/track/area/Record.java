package com.patrickdurke.trackremindtodo.ui.track.area;

public class Record {
    private final int id;
    private final int parentId;
    private String timeStampString;
    // NEXT LEVEL TODO: List of record entry (id + type + value + unit)

    public Record(int id, String timeStampString, int parentId) {
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

    public int getParentId() {
        return parentId;
    }
}
