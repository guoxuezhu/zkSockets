package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class MLsLists {

    @Id
    public Long id;

    public String name;

    public String strMLs;

    public String time;

    @Generated(hash = 934382541)
    public MLsLists(Long id, String name, String strMLs, String time) {
        this.id = id;
        this.name = name;
        this.strMLs = strMLs;
        this.time = time;
    }

    @Generated(hash = 525193264)
    public MLsLists() {
    }

    @Override
    public String toString() {
        return "MLsLists{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", strMLs='" + strMLs + '\'' +
                ", time='" + time + '\'' +
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

    public String getStrMLs() {
        return this.strMLs;
    }

    public void setStrMLs(String strMLs) {
        this.strMLs = strMLs;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}