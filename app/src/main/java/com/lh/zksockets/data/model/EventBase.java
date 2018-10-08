package com.lh.zksockets.data.model;

public class EventBase {

    public int id;
    public String name;
    public boolean isChecked;
    public String time;

    public EventBase(int id, String name, boolean isChecked, String time) {
        this.id = id;
        this.name = name;
        this.isChecked = isChecked;
        this.time = time;
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

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "EventBase{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isChecked=" + isChecked +
                ", time='" + time + '\'' +
                '}';
    }
}
