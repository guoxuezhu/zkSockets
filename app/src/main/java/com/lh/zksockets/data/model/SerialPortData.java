package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class SerialPortData {

    @Id
    public Long id;
    public String serialPortName;
    public String baudRate;
    public String checkoutBit;
    public String dataBit;
    public String stopBit;
    @Generated(hash = 1989856832)
    public SerialPortData(Long id, String serialPortName, String baudRate,
            String checkoutBit, String dataBit, String stopBit) {
        this.id = id;
        this.serialPortName = serialPortName;
        this.baudRate = baudRate;
        this.checkoutBit = checkoutBit;
        this.dataBit = dataBit;
        this.stopBit = stopBit;
    }
    @Generated(hash = 368164279)
    public SerialPortData() {
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
    public String getBaudRate() {
        return this.baudRate;
    }
    public void setBaudRate(String baudRate) {
        this.baudRate = baudRate;
    }
    public String getCheckoutBit() {
        return this.checkoutBit;
    }
    public void setCheckoutBit(String checkoutBit) {
        this.checkoutBit = checkoutBit;
    }
    public String getDataBit() {
        return this.dataBit;
    }
    public void setDataBit(String dataBit) {
        this.dataBit = dataBit;
    }
    public String getStopBit() {
        return this.stopBit;
    }
    public void setStopBit(String stopBit) {
        this.stopBit = stopBit;
    }

    @Override
    public String toString() {
        return "SerialPortData{" +
                "id=" + id +
                ", serialPortName='" + serialPortName + '\'' +
                ", baudRate='" + baudRate + '\'' +
                ", checkoutBit='" + checkoutBit + '\'' +
                ", dataBit='" + dataBit + '\'' +
                ", stopBit='" + stopBit + '\'' +
                '}';
    }
}
