package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class EventShangke {

    public int eventType;
    public Long eventId;
    public String name;
    public int status;
    public boolean isChecked;
    public int time;

    @Generated(hash = 1597002131)
    public EventShangke(int eventType, Long eventId, String name, int status,
                        boolean isChecked, int time) {
        this.eventType = eventType;
        this.eventId = eventId;
        this.name = name;
        this.status = status;
        this.isChecked = isChecked;
        this.time = time;
    }

    @Generated(hash = 2085549123)
    public EventShangke() {
    }

    public int getEventType() {
        return this.eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public Long getEventId() {
        return this.eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean getIsChecked() {
        return this.isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "EventShangke{" +
                "eventType=" + eventType +
                ", eventId=" + eventId +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", isChecked=" + isChecked +
                ", time=" + time +
                '}';
    }
}
