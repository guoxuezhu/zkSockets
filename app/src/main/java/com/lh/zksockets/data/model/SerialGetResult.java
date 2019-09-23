package com.lh.zksockets.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SerialGetResult {

    @SerializedName("id")
    public Long id;
    @SerializedName("deviceName")
    public String deviceName;
    @SerializedName("jinZhi")
    public int jinZhi;
    @SerializedName("baudRate")
    public String baudRate;
    @SerializedName("baudRateId")
    public int baudRateId;
    @SerializedName("checkoutBit")
    public String checkoutBit;
    @SerializedName("checkoutBitId")
    public int checkoutBitId;
    @SerializedName("dataBit")
    public String dataBit;
    @SerializedName("dataBitId")
    public int dataBitId;
    @SerializedName("stopBit")
    public String stopBit;
    @SerializedName("stopBitId")
    public int stopBitId;

    @SerializedName("command")
    public List<SerialCommand> command;

    @Override
    public String toString() {
        return "SerialGetResult{" +
                "id=" + id +
                ", deviceName='" + deviceName + '\'' +
                ", jinZhi=" + jinZhi +
                ", baudRate='" + baudRate + '\'' +
                ", baudRateId=" + baudRateId +
                ", checkoutBit='" + checkoutBit + '\'' +
                ", checkoutBitId=" + checkoutBitId +
                ", dataBit='" + dataBit + '\'' +
                ", dataBitId=" + dataBitId +
                ", stopBit='" + stopBit + '\'' +
                ", stopBitId=" + stopBitId +
                ", command=" + command +
                '}';
    }

}
