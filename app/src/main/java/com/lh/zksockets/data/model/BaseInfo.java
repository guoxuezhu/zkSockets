package com.lh.zksockets.data.model;

public class BaseInfo {

    public String classRoom;
    public int wifi; //1:静态IP   2:动态IP
    public String IP;
    public String PORT;
    public String zkVersionName;
    public String zkDeviceName;

    @Override
    public String toString() {
        return "BaseInfo{" +
                "classRoom='" + classRoom + '\'' +
                ", wifi=" + wifi +
                ", IP='" + IP + '\'' +
                ", PORT='" + PORT + '\'' +
                ", zkVersionName='" + zkVersionName + '\'' +
                ", zkDeviceName='" + zkDeviceName + '\'' +
                '}';
    }
}
