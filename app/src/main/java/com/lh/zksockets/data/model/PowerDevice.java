package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class PowerDevice {

    public Long chazuoId;

    public String deviceName;

    public int openTime;

    public int closedTime;

    @Generated(hash = 516739837)
    public PowerDevice(Long chazuoId, String deviceName, int openTime,
            int closedTime) {
        this.chazuoId = chazuoId;
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
                "chazuoId=" + chazuoId +
                ", deviceName='" + deviceName + '\'' +
                ", openTime=" + openTime +
                ", closedTime=" + closedTime +
                '}';
    }

    public Long getChazuoId() {
        return this.chazuoId;
    }

    public void setChazuoId(Long chazuoId) {
        this.chazuoId = chazuoId;
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
