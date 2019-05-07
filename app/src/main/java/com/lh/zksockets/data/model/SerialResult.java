package com.lh.zksockets.data.model;

import java.util.List;

public class SerialResult {

    public SerialPortData serialPortData;

    public List<SerialCommand> serialCommandList;

    public SerialResult(SerialPortData serialPortData, List<SerialCommand> serialCommandList) {
        this.serialPortData = serialPortData;
        this.serialCommandList = serialCommandList;
    }

    @Override
    public String toString() {
        return "SerialResult{" +
                "serialPortData=" + serialPortData +
                ", serialCommandList=" + serialCommandList +
                '}';
    }
}
