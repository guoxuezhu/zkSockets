package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class IoPortData {

    @Id
    public Long id;

    public int io1; //1 高      0 底
    public int io2;
    public int io3;
    public int io4;

    @Generated(hash = 1883525756)
    public IoPortData(Long id, int io1, int io2, int io3, int io4) {
        this.id = id;
        this.io1 = io1;
        this.io2 = io2;
        this.io3 = io3;
        this.io4 = io4;
    }

    @Generated(hash = 1124049858)
    public IoPortData() {
    }

    @Override
    public String toString() {
        return "IoPortData{" +
                "id=" + id +
                ", io1=" + io1 +
                ", io2=" + io2 +
                ", io3=" + io3 +
                ", io4=" + io4 +
                '}';
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIo1() {
        return this.io1;
    }

    public void setIo1(int io1) {
        this.io1 = io1;
    }

    public int getIo2() {
        return this.io2;
    }

    public void setIo2(int io2) {
        this.io2 = io2;
    }

    public int getIo3() {
        return this.io3;
    }

    public void setIo3(int io3) {
        this.io3 = io3;
    }

    public int getIo4() {
        return this.io4;
    }

    public void setIo4(int io4) {
        this.io4 = io4;
    }
}
