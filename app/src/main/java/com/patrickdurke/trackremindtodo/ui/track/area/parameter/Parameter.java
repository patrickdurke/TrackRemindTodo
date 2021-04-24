package com.patrickdurke.trackremindtodo.ui.track.area.parameter;

public class Parameter {
    private int id;
    private int areaId;
    private String name;
    private String valueType;
    private String unit;

    public Parameter(String name, String valueType, String unit, int areaId) {
        this.name = name;
        this.valueType = valueType;
        this.unit = unit;
        this.areaId = areaId;
    }

    public Parameter() {
    }

    public int getAreaId() {
        return areaId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getValueType() {
        return valueType;
    }

    public String getUnit() {
        return unit;
    }
}
