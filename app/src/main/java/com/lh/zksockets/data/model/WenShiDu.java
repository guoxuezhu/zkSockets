package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class WenShiDu {

    public String wenStr;
    public String shiStr;
    public int timeStr;
    public String serialportML;

    @Generated(hash = 453718648)
    public WenShiDu(String wenStr, String shiStr, int timeStr,
            String serialportML) {
        this.wenStr = wenStr;
        this.shiStr = shiStr;
        this.timeStr = timeStr;
        this.serialportML = serialportML;
    }

    @Generated(hash = 960354638)
    public WenShiDu() {
    }

    @Override
    public String toString() {
        return "WenShiDu{" +
                "wenStr='" + wenStr + '\'' +
                ", shiStr='" + shiStr + '\'' +
                ", timeStr=" + timeStr +
                ", serialportML='" + serialportML + '\'' +
                '}';
    }

    public String getWenStr() {
        return this.wenStr;
    }

    public void setWenStr(String wenStr) {
        this.wenStr = wenStr;
    }

    public String getShiStr() {
        return this.shiStr;
    }

    public void setShiStr(String shiStr) {
        this.shiStr = shiStr;
    }

    public int getTimeStr() {
        return this.timeStr;
    }

    public void setTimeStr(int timeStr) {
        this.timeStr = timeStr;
    }

    public String getSerialportML() {
        return this.serialportML;
    }

    public void setSerialportML(String serialportML) {
        this.serialportML = serialportML;
    }
}
