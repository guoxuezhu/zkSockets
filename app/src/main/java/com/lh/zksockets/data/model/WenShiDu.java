package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class WenShiDu {

    public String CO2;
    public String VOC;
    public String HCHO;
    public String PM25;
    public String wenStr;
    public String shiStr;
    public String PM10;

    public int timeStr;
    public String serialportML;


    @Generated(hash = 1807470164)
    public WenShiDu(String CO2, String VOC, String HCHO, String PM25, String wenStr,
            String shiStr, String PM10, int timeStr, String serialportML) {
        this.CO2 = CO2;
        this.VOC = VOC;
        this.HCHO = HCHO;
        this.PM25 = PM25;
        this.wenStr = wenStr;
        this.shiStr = shiStr;
        this.PM10 = PM10;
        this.timeStr = timeStr;
        this.serialportML = serialportML;
    }


    @Generated(hash = 960354638)
    public WenShiDu() {
    }


    @Override
    public String toString() {
        return "WenShiDu{" +
                "CO2='" + CO2 + '\'' +
                ", VOC='" + VOC + '\'' +
                ", HCHO='" + HCHO + '\'' +
                ", PM25='" + PM25 + '\'' +
                ", wenStr='" + wenStr + '\'' +
                ", shiStr='" + shiStr + '\'' +
                ", PM10='" + PM10 + '\'' +
                ", timeStr=" + timeStr +
                ", serialportML='" + serialportML + '\'' +
                '}';
    }


    public String getCO2() {
        return this.CO2;
    }


    public void setCO2(String CO2) {
        this.CO2 = CO2;
    }


    public String getVOC() {
        return this.VOC;
    }


    public void setVOC(String VOC) {
        this.VOC = VOC;
    }


    public String getHCHO() {
        return this.HCHO;
    }


    public void setHCHO(String HCHO) {
        this.HCHO = HCHO;
    }


    public String getPM25() {
        return this.PM25;
    }


    public void setPM25(String PM25) {
        this.PM25 = PM25;
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


    public String getPM10() {
        return this.PM10;
    }


    public void setPM10(String PM10) {
        this.PM10 = PM10;
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
