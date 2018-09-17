package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class PowerDevice {

    public String deviceName;

    public String openTime;

    public String closedTime;

    @Generated(hash = 552878418)
    public PowerDevice(String deviceName, String openTime, String closedTime) {
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
                ", openTime='" + openTime + '\'' +
                ", closedTime='" + closedTime + '\'' +
                '}';
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getOpenTime() {
        return this.openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getClosedTime() {
        return this.closedTime;
    }

    public void setClosedTime(String closedTime) {
        this.closedTime = closedTime;
    }
}
