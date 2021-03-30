package com.patrickdurke.trackremindtodo.ui.track.area.record;

public class Entry {
    private final int id;
    private final int parentId;
    private String valueUnitString;


    public Entry(int id, String valueUnitString, int parentId) {
        this.id = id;
        this.valueUnitString = valueUnitString;
        this.parentId = parentId;
    }

    public int getId() {
        return id;
    }

    public String getValueUnitString() {
        return valueUnitString;
    }

    public void setValueUnitString(String valueUnitString) {
        this.valueUnitString = valueUnitString;
    }

    public int getParentId() {
        return parentId;
    }
}
