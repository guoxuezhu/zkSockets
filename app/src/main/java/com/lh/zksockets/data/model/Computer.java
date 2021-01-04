package com.lh.zksockets.data.model;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Computer {

    @SerializedName("dn_ip")
    public String dn_ip;

    @SerializedName("dn_port")
    public String dn_port;

    @SerializedName("dn_ml")
    public String dn_ml;

    @SerializedName("dn_status")
    public String dn_status;

    @Generated(hash = 2048191255)
    public Computer(String dn_ip, String dn_port, String dn_ml, String dn_status) {
        this.dn_ip = dn_ip;
        this.dn_port = dn_port;
        this.dn_ml = dn_ml;
        this.dn_status = dn_status;
    }

    @Generated(hash = 1238779503)
    public Computer() {
    }

    @Override
    public String toString() {
        return "Computer{" +
                "dn_ip='" + dn_ip + '\'' +
                ", dn_port='" + dn_port + '\'' +
                ", dn_ml='" + dn_ml + '\'' +
                ", dn_status='" + dn_status + '\'' +
                '}';
    }

    public String getDn_ip() {
        return this.dn_ip;
    }

    public void setDn_ip(String dn_ip) {
        this.dn_ip = dn_ip;
    }

    public String getDn_port() {
        return this.dn_port;
    }

    public void setDn_port(String dn_port) {
        this.dn_port = dn_port;
    }

    public String getDn_ml() {
        return this.dn_ml;
    }

    public void setDn_ml(String dn_ml) {
        this.dn_ml = dn_ml;
    }

    public String getDn_status() {
        return this.dn_status;
    }

    public void setDn_status(String dn_status) {
        this.dn_status = dn_status;
    }
}
