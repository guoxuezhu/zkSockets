package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class Lamp {

    @Id
    public Long id;

    public String name;

    public int workType;

    public int ioSelectId;

    public String ioSelectName;

    public int serialPortSelectId;

    public String serialPortSelectName;

    public int baudRateSelectId;

    public String baudRateSelectName;

    public int checkoutBitSelectId;

    public String checkoutBitSelectName;

    public int dataBitSelectId;

    public String dataBitSelectName;

    public int stopBitSelectId;

    public String stopBitSelectName;

    @Generated(hash = 1220713375)
    public Lamp(Long id, String name, int workType, int ioSelectId,
            String ioSelectName, int serialPortSelectId,
            String serialPortSelectName, int baudRateSelectId,
            String baudRateSelectName, int checkoutBitSelectId,
            String checkoutBitSelectName, int dataBitSelectId,
            String dataBitSelectName, int stopBitSelectId,
            String stopBitSelectName) {
        this.id = id;
        this.name = name;
        this.workType = workType;
        this.ioSelectId = ioSelectId;
        this.ioSelectName = ioSelectName;
        this.serialPortSelectId = serialPortSelectId;
        this.serialPortSelectName = serialPortSelectName;
        this.baudRateSelectId = baudRateSelectId;
        this.baudRateSelectName = baudRateSelectName;
        this.checkoutBitSelectId = checkoutBitSelectId;
        this.checkoutBitSelectName = checkoutBitSelectName;
        this.dataBitSelectId = dataBitSelectId;
        this.dataBitSelectName = dataBitSelectName;
        this.stopBitSelectId = stopBitSelectId;
        this.stopBitSelectName = stopBitSelectName;
    }

    @Generated(hash = 1277261474)
    public Lamp() {
    }

    @Override
    public String toString() {
        return "Lamp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", workType=" + workType +
                ", ioSelectId=" + ioSelectId +
                ", ioSelectName='" + ioSelectName + '\'' +
                ", serialPortSelectId=" + serialPortSelectId +
                ", serialPortSelectName='" + serialPortSelectName + '\'' +
                ", baudRateSelectId=" + baudRateSelectId +
                ", baudRateSelectName='" + baudRateSelectName + '\'' +
                ", checkoutBitSelectId=" + checkoutBitSelectId +
                ", checkoutBitSelectName='" + checkoutBitSelectName + '\'' +
                ", dataBitSelectId=" + dataBitSelectId +
                ", dataBitSelectName='" + dataBitSelectName + '\'' +
                ", stopBitSelectId=" + stopBitSelectId +
                ", stopBitSelectName='" + stopBitSelectName + '\'' +
                '}';
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWorkType() {
        return this.workType;
    }

    public void setWorkType(int workType) {
        this.workType = workType;
    }

    public int getIoSelectId() {
        return this.ioSelectId;
    }

    public void setIoSelectId(int ioSelectId) {
        this.ioSelectId = ioSelectId;
    }

    public String getIoSelectName() {
        return this.ioSelectName;
    }

    public void setIoSelectName(String ioSelectName) {
        this.ioSelectName = ioSelectName;
    }

    public int getSerialPortSelectId() {
        return this.serialPortSelectId;
    }

    public void setSerialPortSelectId(int serialPortSelectId) {
        this.serialPortSelectId = serialPortSelectId;
    }

    public String getSerialPortSelectName() {
        return this.serialPortSelectName;
    }

    public void setSerialPortSelectName(String serialPortSelectName) {
        this.serialPortSelectName = serialPortSelectName;
    }

    public int getBaudRateSelectId() {
        return this.baudRateSelectId;
    }

    public void setBaudRateSelectId(int baudRateSelectId) {
        this.baudRateSelectId = baudRateSelectId;
    }

    public String getBaudRateSelectName() {
        return this.baudRateSelectName;
    }

    public void setBaudRateSelectName(String baudRateSelectName) {
        this.baudRateSelectName = baudRateSelectName;
    }

    public int getCheckoutBitSelectId() {
        return this.checkoutBitSelectId;
    }

    public void setCheckoutBitSelectId(int checkoutBitSelectId) {
        this.checkoutBitSelectId = checkoutBitSelectId;
    }

    public String getCheckoutBitSelectName() {
        return this.checkoutBitSelectName;
    }

    public void setCheckoutBitSelectName(String checkoutBitSelectName) {
        this.checkoutBitSelectName = checkoutBitSelectName;
    }

    public int getDataBitSelectId() {
        return this.dataBitSelectId;
    }

    public void setDataBitSelectId(int dataBitSelectId) {
        this.dataBitSelectId = dataBitSelectId;
    }

    public String getDataBitSelectName() {
        return this.dataBitSelectName;
    }

    public void setDataBitSelectName(String dataBitSelectName) {
        this.dataBitSelectName = dataBitSelectName;
    }

    public int getStopBitSelectId() {
        return this.stopBitSelectId;
    }

    public void setStopBitSelectId(int stopBitSelectId) {
        this.stopBitSelectId = stopBitSelectId;
    }

    public String getStopBitSelectName() {
        return this.stopBitSelectName;
    }

    public void setStopBitSelectName(String stopBitSelectName) {
        this.stopBitSelectName = stopBitSelectName;
    }
}