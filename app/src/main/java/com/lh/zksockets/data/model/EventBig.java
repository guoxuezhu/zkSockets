package com.lh.zksockets.data.model;

import java.util.List;

public class EventBig {

    public int id;
    public String name;
    public List<EventBase> eventBases;

    public EventBig(int id, String name, List<EventBase> eventBases) {
        this.id = id;
        this.name = name;
        this.eventBases = eventBases;
    }

    @Override
    public String toString() {
        return "EventBig{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", eventBases=" + eventBases +
                '}';
    }
}
