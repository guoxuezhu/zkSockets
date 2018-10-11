package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ChazuoData {

    @Id
    public Long id;
    public String name;
    public String bindName;
    public boolean isOk;
    public int openTime;
    public int closedTime;


    @Generated(hash = 892493809)
    public ChazuoData(Long id, String name, String bindName, boolean isOk,
            int openTime, int closedTime) {
        this.id = id;
        this.name = name;
        this.bindName = bindName;
        this.isOk = isOk;
        this.openTime = openTime;
        this.closedTime = closedTime;
    }


    @Generated(hash = 1700423749)
    public ChazuoData() {
    }


    @Override
    public String toString() {
        return "ChazuoData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bindName='" + bindName + '\'' +
                ", isOk=" + isOk +
                ", openTime=" + openTime +
                ", closedTime=" + closedTime +
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


    public String getBindName() {
        return this.bindName;
    }


    public void setBindName(String bindName) {
        this.bindName = bindName;
    }


    public boolean getIsOk() {
        return this.isOk;
    }


    public void setIsOk(boolean isOk) {
        this.isOk = isOk;
    }


    public int getOpenTime() {
        return this.openTime;
    }


    public void setOpenTime(int openTime) {
        this.openTime = openTime;
    }


    public int getClosedTime() {
        return this.closedTime;
    }


    public void setClosedTime(int closedTime) {
        this.closedTime = closedTime;
    }
}
