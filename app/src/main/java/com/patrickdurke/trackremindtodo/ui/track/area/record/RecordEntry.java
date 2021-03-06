package com.patrickdurke.trackremindtodo.ui.track.area.record;

public class RecordEntry {
    private int id;
    private int recordId;
    private int parameterId;
    private String value;

    public RecordEntry() {}

    public RecordEntry(int parameterId, String value, int recordId) {
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

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }
}
