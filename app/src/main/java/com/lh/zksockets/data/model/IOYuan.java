package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class IOYuan {

    @Id
    public Long id;
    public String name;
    public boolean isOK;
    public int outId;
    public String outName;
    public int sendTime;

    @Generated(hash = 1662427066)
    public IOYuan(Long id, String name, boolean isOK, int outId, String outName,
            int sendTime) {
        this.id = id;
        this.name = name;
        this.isOK = isOK;
        this.outId = outId;
        this.outName = outName;
        this.sendTime = sendTime;
    }

    @Generated(hash = 1488061952)
    public IOYuan() {
    }

    @Override
    public String toString() {
        return "IOYuan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isOK=" + isOK +
                ", outId=" + outId +
                ", outName='" + outName + '\'' +
                ", sendTime=" + sendTime +
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

    public boolean getIsOK() {
        return this.isOK;
    }

    public void setIsOK(boolean isOK) {
        this.isOK = isOK;
    }

    public int getOutId() {
        return this.outId;
    }

    public void setOutId(int outId) {
        this.outId = outId;
    }

    public String getOutName() {
        return this.outName;
    }

    public void setOutName(String outName) {
        this.outName = outName;
    }

    public int getSendTime() {
        return this.sendTime;
    }

    public void setSendTime(int sendTime) {
        this.sendTime = sendTime;
    }
}
