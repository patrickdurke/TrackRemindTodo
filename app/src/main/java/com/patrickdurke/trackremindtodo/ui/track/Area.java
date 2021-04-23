package com.patrickdurke.trackremindtodo.ui.track;

public class Area {
    private int id;
    private String name;
    private String color;

    public Area() {
    }

    public Area(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Area{" + "id=" + id + ", name='" + name + '\'' + ", color='" + color + '\'' + '}';
    }
}
