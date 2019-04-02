package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class IoPortData {

    @Id
    public Long id;

    public String name;

    public int ioOutStatus;//1 高      0 低

    public int time;

    @Generated(hash = 1170404985)
    public IoPortData(Long id, String name, int ioOutStatus, int time) {
        this.id = id;
        this.name = name;
        this.ioOutStatus = ioOutStatus;
        this.time = time;
    }

    @Generated(hash = 1124049858)
    public IoPortData() {
    }

    @Override
    public String toString() {
        return "IoPortData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ioOutStatus=" + ioOutStatus +
                ", time=" + time +
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

    public int getIoOutStatus() {
        return this.ioOutStatus;
    }

    public void setIoOutStatus(int ioOutStatus) {
        this.ioOutStatus = ioOutStatus;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
