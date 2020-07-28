package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UDPInfo {

    public String wlip;
    public String diannaoIP;
    public int diannaoPort;
    public String diannaoMl;

    @Generated(hash = 29889984)
    public UDPInfo(String wlip, String diannaoIP, int diannaoPort,
            String diannaoMl) {
        this.wlip = wlip;
        this.diannaoIP = diannaoIP;
        this.diannaoPort = diannaoPort;
        this.diannaoMl = diannaoMl;
    }

    @Generated(hash = 363659646)
    public UDPInfo() {
    }

    @Override
    public String toString() {
        return "UDPInfo{" +
                "wlip='" + wlip + '\'' +
                ", diannaoIP='" + diannaoIP + '\'' +
                ", diannaoPort=" + diannaoPort +
                ", diannaoMl='" + diannaoMl + '\'' +
                '}';
    }

    public String getWlip() {
        return this.wlip;
    }

    public void setWlip(String wlip) {
        this.wlip = wlip;
    }

    public String getDiannaoIP() {
        return this.diannaoIP;
    }

    public void setDiannaoIP(String diannaoIP) {
        this.diannaoIP = diannaoIP;
    }

    public int getDiannaoPort() {
        return this.diannaoPort;
    }

    public void setDiannaoPort(int diannaoPort) {
        this.diannaoPort = diannaoPort;
    }

    public String getDiannaoMl() {
        return this.diannaoMl;
    }

    public void setDiannaoMl(String diannaoMl) {
        this.diannaoMl = diannaoMl;
    }
}
