package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class DoorInfo {

    public String IP;
    public String name;

    @Generated(hash = 100829992)
    public DoorInfo(String IP, String name) {
        this.IP = IP;
        this.name = name;
    }

    @Generated(hash = 1374416615)
    public DoorInfo() {
    }

    @Override
    public String toString() {
        return "DoorInfo{" +
                "IP='" + IP + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getIP() {
        return this.IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
