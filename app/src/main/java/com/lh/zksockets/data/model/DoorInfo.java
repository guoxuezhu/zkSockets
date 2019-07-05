package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class DoorInfo {

    public String IP;
    public String name;
    public int isStart; // 1 网络  0 继电器


    @Generated(hash = 365645744)
    public DoorInfo(String IP, String name, int isStart) {
        this.IP = IP;
        this.name = name;
        this.isStart = isStart;
    }


    @Generated(hash = 1374416615)
    public DoorInfo() {
    }


    @Override
    public String toString() {
        return "DoorInfo{" +
                "IP='" + IP + '\'' +
                ", name='" + name + '\'' +
                ", isStart=" + isStart +
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


    public int getIsStart() {
        return this.isStart;
    }


    public void setIsStart(int isStart) {
        this.isStart = isStart;
    }
}
