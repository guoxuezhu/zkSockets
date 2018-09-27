package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class PowerDevice {

    public String deviceName;

    public int openTime;

    public int closedTime;

    @Generated(hash = 1969481739)
    public PowerDevice(String deviceName, int openTime, int closedTime) {
        this.deviceName = deviceName;
        this.openTime = openTime;
        this.closedTime = closedTime;
    }

    @Generated(hash = 1030580101)
    public PowerDevice() {
    }

    @Override
    public String toString() {
        return "PowerDevice{" +
                "deviceName='" + deviceName + '\'' +
                ", openTime=" + openTime +
                ", closedTime=" + closedTime +
                '}';
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getOpenTime() {
        return this.openTime;
    }

    public void setOpenTime(int openTime) {
        this.openTime = openTime;
    }

    public int getClosedTime() {
        return this.closedTime;
    }

    public void setClosedTime(int closedTime) {
        this.closedTime = closedTime;
    }
}
