package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class BaseInfo {

    public String classRoom;
    public String mqttuser;
    public String mqttpassword;
    public String uuid;


    @Generated(hash = 734515474)
    public BaseInfo(String classRoom, String mqttuser, String mqttpassword,
            String uuid) {
        this.classRoom = classRoom;
        this.mqttuser = mqttuser;
        this.mqttpassword = mqttpassword;
        this.uuid = uuid;
    }


    @Generated(hash = 1463957903)
    public BaseInfo() {
    }


    @Override
    public String toString() {
        return "BaseInfo{" +
                "classRoom='" + classRoom + '\'' +
                ", mqttuser='" + mqttuser + '\'' +
                ", mqttpassword='" + mqttpassword + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }


    public String getClassRoom() {
        return this.classRoom;
    }


    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }


    public String getMqttuser() {
        return this.mqttuser;
    }


    public void setMqttuser(String mqttuser) {
        this.mqttuser = mqttuser;
    }


    public String getMqttpassword() {
        return this.mqttpassword;
    }


    public void setMqttpassword(String mqttpassword) {
        this.mqttpassword = mqttpassword;
    }


    public String getUuid() {
        return this.uuid;
    }


    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
