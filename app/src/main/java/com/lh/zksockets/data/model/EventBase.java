package com.lh.zksockets.data.model;

public class EventBase {

    public int eventType;
    public Long eventId;
    public String name;
    public boolean isChecked;
    public String time;

    public EventBase(int eventType, Long eventId, String name, boolean isChecked, String time) {
        this.eventType = eventType;
        this.eventId = eventId;
        this.name = name;
        this.isChecked = isChecked;
        this.time = time;
    }


    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
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
                "eventType=" + eventType +
                ", eventId=" + eventId +
                ", name='" + name + '\'' +
                ", isChecked=" + isChecked +
                ", time='" + time + '\'' +
                '}';
    }
}
