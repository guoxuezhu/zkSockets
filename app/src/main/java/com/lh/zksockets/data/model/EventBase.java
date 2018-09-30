package com.lh.zksockets.data.model;

public class EventBase {


    public int id;
    public String name;

    public EventBase(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "EventBase{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
