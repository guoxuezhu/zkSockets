package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Projector {

    @Id
    public Long id;
    public String name;
    public String baudRate;
    public int baudRateId;
    public String checkoutBit;
    public int checkoutBitId;
    public String dataBit;
    public int dataBitId;
    public String stopBit;
    public int stopBitId;
    public String type;
    public int typeId;
    public String openCommand;
    public String closedCommand;
    public String VGACommand;
    public String HDMICommand;
    public String jiChazuo;
    public int jiChazuoId;
    public String buChazuo;
    public int buChazuoId;

    @Generated(hash = 954992496)
    public Projector(Long id, String name, String baudRate, int baudRateId,
                     String checkoutBit, int checkoutBitId, String dataBit, int dataBitId,
                     String stopBit, int stopBitId, String type, int typeId,
                     String openCommand, String closedCommand, String VGACommand,
                     String HDMICommand, String jiChazuo, int jiChazuoId, String buChazuo,
                     int buChazuoId) {
        this.id = id;
        this.name = name;
        this.baudRate = baudRate;
        this.baudRateId = baudRateId;
        this.checkoutBit = checkoutBit;
        this.checkoutBitId = checkoutBitId;
        this.dataBit = dataBit;
        this.dataBitId = dataBitId;
        this.stopBit = stopBit;
        this.stopBitId = stopBitId;
        this.type = type;
        this.typeId = typeId;
        this.openCommand = openCommand;
        this.closedCommand = closedCommand;
        this.VGACommand = VGACommand;
        this.HDMICommand = HDMICommand;
        this.jiChazuo = jiChazuo;
        this.jiChazuoId = jiChazuoId;
        this.buChazuo = buChazuo;
        this.buChazuoId = buChazuoId;
    }

    @Generated(hash = 487975139)
    public Projector() {
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

    public String getBaudRate() {
        return this.baudRate;
    }

    public void setBaudRate(String baudRate) {
        this.baudRate = baudRate;
    }

    public int getBaudRateId() {
        return this.baudRateId;
    }

    public void setBaudRateId(int baudRateId) {
        this.baudRateId = baudRateId;
    }

    public String getCheckoutBit() {
        return this.checkoutBit;
    }

    public void setCheckoutBit(String checkoutBit) {
        this.checkoutBit = checkoutBit;
    }

    public int getCheckoutBitId() {
        return this.checkoutBitId;
    }

    public void setCheckoutBitId(int checkoutBitId) {
        this.checkoutBitId = checkoutBitId;
    }

    public String getDataBit() {
        return this.dataBit;
    }

    public void setDataBit(String dataBit) {
        this.dataBit = dataBit;
    }

    public int getDataBitId() {
        return this.dataBitId;
    }

    public void setDataBitId(int dataBitId) {
        this.dataBitId = dataBitId;
    }

    public String getStopBit() {
        return this.stopBit;
    }

    public void setStopBit(String stopBit) {
        this.stopBit = stopBit;
    }

    public int getStopBitId() {
        return this.stopBitId;
    }

    public void setStopBitId(int stopBitId) {
        this.stopBitId = stopBitId;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTypeId() {
        return this.typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getOpenCommand() {
        return this.openCommand;
    }

    public void setOpenCommand(String openCommand) {
        this.openCommand = openCommand;
    }

    public String getClosedCommand() {
        return this.closedCommand;
    }

    public void setClosedCommand(String closedCommand) {
        this.closedCommand = closedCommand;
    }

    public String getVGACommand() {
        return this.VGACommand;
    }

    public void setVGACommand(String VGACommand) {
        this.VGACommand = VGACommand;
    }

    public String getHDMICommand() {
        return this.HDMICommand;
    }

    public void setHDMICommand(String HDMICommand) {
        this.HDMICommand = HDMICommand;
    }

    public String getJiChazuo() {
        return this.jiChazuo;
    }

    public void setJiChazuo(String jiChazuo) {
        this.jiChazuo = jiChazuo;
    }

    public int getJiChazuoId() {
        return this.jiChazuoId;
    }

    public void setJiChazuoId(int jiChazuoId) {
        this.jiChazuoId = jiChazuoId;
    }

    public String getBuChazuo() {
        return this.buChazuo;
    }

    public void setBuChazuo(String buChazuo) {
        this.buChazuo = buChazuo;
    }

    public int getBuChazuoId() {
        return this.buChazuoId;
    }

    public void setBuChazuoId(int buChazuoId) {
        this.buChazuoId = buChazuoId;
    }

    @Override
    public String toString() {
        return "Projector{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", baudRate='" + baudRate + '\'' +
                ", baudRateId=" + baudRateId +
                ", checkoutBit='" + checkoutBit + '\'' +
                ", checkoutBitId=" + checkoutBitId +
                ", dataBit='" + dataBit + '\'' +
                ", dataBitId=" + dataBitId +
                ", stopBit='" + stopBit + '\'' +
                ", stopBitId=" + stopBitId +
                ", type='" + type + '\'' +
                ", typeId=" + typeId +
                ", openCommand='" + openCommand + '\'' +
                ", closedCommand='" + closedCommand + '\'' +
                ", VGACommand='" + VGACommand + '\'' +
                ", HDMICommand='" + HDMICommand + '\'' +
                ", jiChazuo='" + jiChazuo + '\'' +
                ", jiChazuoId=" + jiChazuoId +
                ", buChazuo='" + buChazuo + '\'' +
                ", buChazuoId=" + buChazuoId +
                '}';
    }
}
