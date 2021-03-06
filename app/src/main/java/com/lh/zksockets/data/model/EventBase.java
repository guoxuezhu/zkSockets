package com.lh.zksockets.data.model;

public class EventBase {

    public int eventType;
    public Long eventId;
    public String name;
    public int status;
    public boolean isChecked;
    public int time;


    public EventBase(int eventType, Long eventId, String name, int status, boolean isChecked, int time) {
        this.eventType = eventType;
        this.eventId = eventId;
        this.name = name;
        this.status = status;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "EventBase{" +
                "eventType=" + eventType +
                ", eventId=" + eventId +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", isChecked=" + isChecked +
                ", time=" + time +
                '}';
    }
}
