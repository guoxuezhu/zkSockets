package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ChazuoData {

    @Id
    public Long id;
    public String name;
    public String bindName;

    @Generated(hash = 449371066)
    public ChazuoData(Long id, String name, String bindName) {
        this.id = id;
        this.name = name;
        this.bindName = bindName;
    }

    @Generated(hash = 1700423749)
    public ChazuoData() {
    }

    @Override
    public String toString() {
        return "ChazuoData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bindName='" + bindName + '\'' +
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

    public String getBindName() {
        return this.bindName;
    }

    public void setBindName(String bindName) {
        this.bindName = bindName;
    }
}
