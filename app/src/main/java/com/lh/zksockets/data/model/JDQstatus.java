package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class JDQstatus {


    @Id
    public Long id;

    public String name;

    public String deviceName;

    public int jdqStatus;//0 关     1 开

    public int time;


    @Generated(hash = 1338266180)
    public JDQstatus(Long id, String name, String deviceName, int jdqStatus,
            int time) {
        this.id = id;
        this.name = name;
        this.deviceName = deviceName;
        this.jdqStatus = jdqStatus;
        this.time = time;
    }


    @Generated(hash = 1434542256)
    public JDQstatus() {
    }


    @Override
    public String toString() {
        return "JDQstatus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", jdqStatus=" + jdqStatus +
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


    public int getJdqStatus() {
        return this.jdqStatus;
    }


    public void setJdqStatus(int jdqStatus) {
        this.jdqStatus = jdqStatus;
    }


    public int getTime() {
        return this.time;
    }


    public void setTime(int time) {
        this.time = time;
    }
}
