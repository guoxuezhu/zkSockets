package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ZkInfo {

    public String zkname;
    public String zkip;
    public String zkVersion;
    public String geendaoVersion;

    @Generated(hash = 1510729296)
    public ZkInfo(String zkname, String zkip, String zkVersion,
            String geendaoVersion) {
        this.zkname = zkname;
        this.zkip = zkip;
        this.zkVersion = zkVersion;
        this.geendaoVersion = geendaoVersion;
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
                ", geendaoVersion='" + geendaoVersion + '\'' +
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

    public String getGeendaoVersion() {
        return this.geendaoVersion;
    }

    public void setGeendaoVersion(String geendaoVersion) {
        this.geendaoVersion = geendaoVersion;
    }
}
