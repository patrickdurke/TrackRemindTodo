package com.patrickdurke.trackremindtodo.ui.track.area.record;

public class Entry {
    private int id;
    private final int recordId;
    private int parameterId;
    private String value;

    public Entry(int parameterId, String value, int recordId) {
        this.parameterId = parameterId;
        this.recordId = recordId;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecordId() {
        return recordId;
    }

    public int getParameterId() {
        return parameterId;
    }

    public String getValue() {
        return value;
    }
}
