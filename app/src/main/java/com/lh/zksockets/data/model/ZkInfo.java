package com.lh.zksockets.data.model;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ZkInfo {

    @SerializedName("title")
    public String zkname;

    @SerializedName("ip")
    public String zkip;

    @SerializedName("version")
    public String zkVersion;

    @SerializedName("data_version")
    public String geendaoVersion;

    @SerializedName("video_num")
    public int hudongVIDnum;

    @SerializedName("uuid")
    public String uuid;

    @SerializedName("show")
    public int ismqttStart;


    @Generated(hash = 438018825)
    public ZkInfo(String zkname, String zkip, String zkVersion,
            String geendaoVersion, int hudongVIDnum, String uuid, int ismqttStart) {
        this.zkname = zkname;
        this.zkip = zkip;
        this.zkVersion = zkVersion;
        this.geendaoVersion = geendaoVersion;
        this.hudongVIDnum = hudongVIDnum;
        this.uuid = uuid;
        this.ismqttStart = ismqttStart;
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
                ", hudongVIDnum=" + hudongVIDnum +
                ", uuid='" + uuid + '\'' +
                ", ismqttStart=" + ismqttStart +
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


    public int getHudongVIDnum() {
        return this.hudongVIDnum;
    }


    public void setHudongVIDnum(int hudongVIDnum) {
        this.hudongVIDnum = hudongVIDnum;
    }


    public String getUuid() {
        return this.uuid;
    }


    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    public int getIsmqttStart() {
        return this.ismqttStart;
    }


    public void setIsmqttStart(int ismqttStart) {
        this.ismqttStart = ismqttStart;
    }
}
