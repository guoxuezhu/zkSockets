package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class DangerOut {


    @Id
    public Long id;

    public int dangerOut1; //1 高      0 底
    public int dangerOut2;
    public int dangerOut3;
    public int dangerOut4;

    @Generated(hash = 712442484)
    public DangerOut(Long id, int dangerOut1, int dangerOut2, int dangerOut3,
            int dangerOut4) {
        this.id = id;
        this.dangerOut1 = dangerOut1;
        this.dangerOut2 = dangerOut2;
        this.dangerOut3 = dangerOut3;
        this.dangerOut4 = dangerOut4;
    }

    @Generated(hash = 1919526387)
    public DangerOut() {
    }

    @Override
    public String toString() {
        return "DangerOut{" +
                "id=" + id +
                ", dangerOut1=" + dangerOut1 +
                ", dangerOut2=" + dangerOut2 +
                ", dangerOut3=" + dangerOut3 +
                ", dangerOut4=" + dangerOut4 +
                '}';
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDangerOut1() {
        return this.dangerOut1;
    }

    public void setDangerOut1(int dangerOut1) {
        this.dangerOut1 = dangerOut1;
    }

    public int getDangerOut2() {
        return this.dangerOut2;
    }

    public void setDangerOut2(int dangerOut2) {
        this.dangerOut2 = dangerOut2;
    }

    public int getDangerOut3() {
        return this.dangerOut3;
    }

    public void setDangerOut3(int dangerOut3) {
        this.dangerOut3 = dangerOut3;
    }

    public int getDangerOut4() {
        return this.dangerOut4;
    }

    public void setDangerOut4(int dangerOut4) {
        this.dangerOut4 = dangerOut4;
    }
}
