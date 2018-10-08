package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class EventBig {

    @Id
    public Long id;
    public String name;
    public String eventBaseString;

    @Generated(hash = 1068853358)
    public EventBig(Long id, String name, String eventBaseString) {
        this.id = id;
        this.name = name;
        this.eventBaseString = eventBaseString;
    }

    @Generated(hash = 1797766626)
    public EventBig() {
    }

    @Override
    public String toString() {
        return "EventBig{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", eventBaseString='" + eventBaseString + '\'' +
                '}';
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEventBaseString() {
        return this.eventBaseString;
    }

    public void setEventBaseString(String eventBaseString) {
        this.eventBaseString = eventBaseString;
    }
}
