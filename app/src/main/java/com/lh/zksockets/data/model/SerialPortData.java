package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class SerialPortData {

    @Id
    public Long id;
    public String serialPortName;
    public String deviceName;

    public int baudRateId;
    public String baudRate;

    public int checkoutBitId;
    public String checkoutBit;

    public int dataBitId;
    public String dataBit;

    public int stopBitId;
    public String stopBit;

    public int jinZhi;//10 进制   16 进制


    @Generated(hash = 870971160)
    public SerialPortData(Long id, String serialPortName, String deviceName,
            int baudRateId, String baudRate, int checkoutBitId, String checkoutBit,
            int dataBitId, String dataBit, int stopBitId, String stopBit,
            int jinZhi) {
        this.id = id;
        this.serialPortName = serialPortName;
        this.deviceName = deviceName;
        this.baudRateId = baudRateId;
        this.baudRate = baudRate;
        this.checkoutBitId = checkoutBitId;
        this.checkoutBit = checkoutBit;
        this.dataBitId = dataBitId;
        this.dataBit = dataBit;
        this.stopBitId = stopBitId;
        this.stopBit = stopBit;
        this.jinZhi = jinZhi;
    }


    @Generated(hash = 368164279)
    public SerialPortData() {
    }


    @Override
    public String toString() {
        return "SerialPortData{" +
                "id=" + id +
                ", serialPortName='" + serialPortName + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", baudRateId=" + baudRateId +
                ", baudRate='" + baudRate + '\'' +
                ", checkoutBitId=" + checkoutBitId +
                ", checkoutBit='" + checkoutBit + '\'' +
                ", dataBitId=" + dataBitId +
                ", dataBit='" + dataBit + '\'' +
                ", stopBitId=" + stopBitId +
                ", stopBit='" + stopBit + '\'' +
                ", jinZhi=" + jinZhi +
                '}';
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getSerialPortName() {
        return this.serialPortName;
    }


    public void setSerialPortName(String serialPortName) {
        this.serialPortName = serialPortName;
    }


    public String getDeviceName() {
        return this.deviceName;
    }


    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }


    public int getBaudRateId() {
        return this.baudRateId;
    }


    public void setBaudRateId(int baudRateId) {
        this.baudRateId = baudRateId;
    }


    public String getBaudRate() {
        return this.baudRate;
    }


    public void setBaudRate(String baudRate) {
        this.baudRate = baudRate;
    }


    public int getCheckoutBitId() {
        return this.checkoutBitId;
    }


    public void setCheckoutBitId(int checkoutBitId) {
        this.checkoutBitId = checkoutBitId;
    }


    public String getCheckoutBit() {
        return this.checkoutBit;
    }


    public void setCheckoutBit(String checkoutBit) {
        this.checkoutBit = checkoutBit;
    }


    public int getDataBitId() {
        return this.dataBitId;
    }


    public void setDataBitId(int dataBitId) {
        this.dataBitId = dataBitId;
    }


    public String getDataBit() {
        return this.dataBit;
    }


    public void setDataBit(String dataBit) {
        this.dataBit = dataBit;
    }


    public int getStopBitId() {
        return this.stopBitId;
    }


    public void setStopBitId(int stopBitId) {
        this.stopBitId = stopBitId;
    }


    public String getStopBit() {
        return this.stopBit;
    }


    public void setStopBit(String stopBit) {
        this.stopBit = stopBit;
    }


    public int getJinZhi() {
        return this.jinZhi;
    }


    public void setJinZhi(int jinZhi) {
        this.jinZhi = jinZhi;
    }
}
