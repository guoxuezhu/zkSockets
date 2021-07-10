package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class DianliangData {

    @Id
    public Long id;

    public String name;

    public String dn_kwh;

    public String dn_v;

    public String dn_a;

    public String dn_w;

    public String dn_w_zong;

    public int intMsg;

    @Generated(hash = 1954157074)
    public DianliangData(Long id, String name, String dn_kwh, String dn_v,
            String dn_a, String dn_w, String dn_w_zong, int intMsg) {
        this.id = id;
        this.name = name;
        this.dn_kwh = dn_kwh;
        this.dn_v = dn_v;
        this.dn_a = dn_a;
        this.dn_w = dn_w;
        this.dn_w_zong = dn_w_zong;
        this.intMsg = intMsg;
    }

    @Generated(hash = 859066484)
    public DianliangData() {
    }

    @Override
    public String toString() {
        return "DianliangData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dn_kwh='" + dn_kwh + '\'' +
                ", dn_v='" + dn_v + '\'' +
                ", dn_a='" + dn_a + '\'' +
                ", dn_w='" + dn_w + '\'' +
                ", dn_w_zong='" + dn_w_zong + '\'' +
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

    public String getDn_kwh() {
        return this.dn_kwh;
    }

    public void setDn_kwh(String dn_kwh) {
        this.dn_kwh = dn_kwh;
    }

    public String getDn_v() {
        return this.dn_v;
    }

    public void setDn_v(String dn_v) {
        this.dn_v = dn_v;
    }

    public String getDn_a() {
        return this.dn_a;
    }

    public void setDn_a(String dn_a) {
        this.dn_a = dn_a;
    }

    public String getDn_w() {
        return this.dn_w;
    }

    public void setDn_w(String dn_w) {
        this.dn_w = dn_w;
    }

    public String getDn_w_zong() {
        return this.dn_w_zong;
    }

    public void setDn_w_zong(String dn_w_zong) {
        this.dn_w_zong = dn_w_zong;
    }

    public int getIntMsg() {
        return this.intMsg;
    }

    public void setIntMsg(int intMsg) {
        this.intMsg = intMsg;
    }
}