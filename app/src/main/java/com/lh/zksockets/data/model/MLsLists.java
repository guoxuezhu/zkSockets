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

    @Generated(hash = 295336955)
    public MLsLists(Long id, String name, String strMLs) {
        this.id = id;
        this.name = name;
        this.strMLs = strMLs;
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
}