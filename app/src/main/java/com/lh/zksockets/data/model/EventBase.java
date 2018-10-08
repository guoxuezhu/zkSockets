package com.lh.zksockets.data.model;

public class EventBase {

    public int id;
    public String name;
    public String time;

    public EventBase(int id, String name, String time) {
        this.id = id;
        this.name = name;
        this.time = time;
    }

    @Override
    public String toString() {
        return "EventBase{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
