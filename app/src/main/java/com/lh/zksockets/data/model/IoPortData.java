package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class IoPortData {

    @Id
    public Long id;

    public String name;

    public String deviceName;

    public int ioOutStatus;//1 高      0 低

    public int time;


    @Generated(hash = 1144577080)
    public IoPortData(Long id, String name, String deviceName, int ioOutStatus,
            int time) {
        this.id = id;
        this.name = name;
        this.deviceName = deviceName;
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
                ", deviceName='" + deviceName + '\'' +
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


    public String getDeviceName() {
        return this.deviceName;
    }


    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
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
