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
    public String checkedNameStr;
    public String eventBaseString;

    @Generated(hash = 1597635617)
    public EventBig(Long id, String name, String checkedNameStr,
            String eventBaseString) {
        this.id = id;
        this.name = name;
        this.checkedNameStr = checkedNameStr;
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
                ", checkedNameStr='" + checkedNameStr + '\'' +
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

    public String getCheckedNameStr() {
        return this.checkedNameStr;
    }

    public void setCheckedNameStr(String checkedNameStr) {
        this.checkedNameStr = checkedNameStr;
    }

    public String getEventBaseString() {
        return this.eventBaseString;
    }

    public void setEventBaseString(String eventBaseString) {
        this.eventBaseString = eventBaseString;
    }
}
