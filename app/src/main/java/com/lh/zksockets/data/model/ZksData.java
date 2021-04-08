package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class ZksData {

    @Id
    public Long id;

    public String name;

    public String strMsg;

    public int intMsg;

    @Generated(hash = 1323382305)
    public ZksData(Long id, String name, String strMsg, int intMsg) {
        this.id = id;
        this.name = name;
        this.strMsg = strMsg;
        this.intMsg = intMsg;
    }

    @Generated(hash = 16952545)
    public ZksData() {
    }

    @Override
    public String toString() {
        return "ZksData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", strMsg='" + strMsg + '\'' +
                ", intMsg=" + intMsg +
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

    public String getStrMsg() {
        return this.strMsg;
    }

    public void setStrMsg(String strMsg) {
        this.strMsg = strMsg;
    }

    public int getIntMsg() {
        return this.intMsg;
    }

    public void setIntMsg(int intMsg) {
        this.intMsg = intMsg;
    }
}