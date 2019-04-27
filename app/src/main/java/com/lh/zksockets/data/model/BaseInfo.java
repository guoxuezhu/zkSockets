package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class BaseInfo {

    public String classRoom;

    public String uuid;

    @Generated(hash = 641942860)
    public BaseInfo(String classRoom, String uuid) {
        this.classRoom = classRoom;
        this.uuid = uuid;
    }

    @Generated(hash = 1463957903)
    public BaseInfo() {
    }

    @Override
    public String toString() {
        return "BaseInfo{" +
                "classRoom='" + classRoom + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }

    public String getClassRoom() {
        return this.classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
