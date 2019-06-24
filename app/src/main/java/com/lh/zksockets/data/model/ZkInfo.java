package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ZkInfo {

    public String zkname;
    public String zkip;
    public String zkVersion;


    @Generated(hash = 1643599967)
    public ZkInfo(String zkname, String zkip, String zkVersion) {
        this.zkname = zkname;
        this.zkip = zkip;
        this.zkVersion = zkVersion;
    }


    @Generated(hash = 632246380)
    public ZkInfo() {
    }


    @Override
    public String toString() {
        return "ZkInfo{" +
                "zkname='" + zkname + '\'' +
                ", zkip='" + zkip + '\'' +
                ", zkVersion='" + zkVersion + '\'' +
                '}';
    }


    public String getZkname() {
        return this.zkname;
    }


    public void setZkname(String zkname) {
        this.zkname = zkname;
    }


    public String getZkip() {
        return this.zkip;
    }


    public void setZkip(String zkip) {
        this.zkip = zkip;
    }


    public String getZkVersion() {
        return this.zkVersion;
    }


    public void setZkVersion(String zkVersion) {
        this.zkVersion = zkVersion;
    }
}
