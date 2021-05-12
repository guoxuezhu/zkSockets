package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class WuangguanInfo {

    @Id
    public Long wg_id;
    public String wg_ip;
    public int wg_port;
    public int wg_status;

    @Generated(hash = 547633195)
    public WuangguanInfo(Long wg_id, String wg_ip, int wg_port, int wg_status) {
        this.wg_id = wg_id;
        this.wg_ip = wg_ip;
        this.wg_port = wg_port;
        this.wg_status = wg_status;
    }

    @Generated(hash = 184286446)
    public WuangguanInfo() {
    }

    @Override
    public String toString() {
        return "WuangguanInfo{" +
                "wg_id=" + wg_id +
                ", wg_ip='" + wg_ip + '\'' +
                ", wg_port=" + wg_port +
                ", wg_status=" + wg_status +
                '}';
    }

    public Long getWg_id() {
        return this.wg_id;
    }

    public void setWg_id(Long wg_id) {
        this.wg_id = wg_id;
    }

    public String getWg_ip() {
        return this.wg_ip;
    }

    public void setWg_ip(String wg_ip) {
        this.wg_ip = wg_ip;
    }

    public int getWg_port() {
        return this.wg_port;
    }

    public void setWg_port(int wg_port) {
        this.wg_port = wg_port;
    }

    public int getWg_status() {
        return this.wg_status;
    }

    public void setWg_status(int wg_status) {
        this.wg_status = wg_status;
    }
}
