package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class DangerOut {


    @Id
    public Long id;

    public String name;

    public int dangerOutStatus;//1 底      0 高

    public int time;

    @Generated(hash = 1650904680)
    public DangerOut(Long id, String name, int dangerOutStatus, int time) {
        this.id = id;
        this.name = name;
        this.dangerOutStatus = dangerOutStatus;
        this.time = time;
    }

    @Generated(hash = 1919526387)
    public DangerOut() {
    }

    @Override
    public String toString() {
        return "DangerOut{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dangerOutStatus=" + dangerOutStatus +
                ", time=" + time +
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

    public int getDangerOutStatus() {
        return this.dangerOutStatus;
    }

    public void setDangerOutStatus(int dangerOutStatus) {
        this.dangerOutStatus = dangerOutStatus;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
