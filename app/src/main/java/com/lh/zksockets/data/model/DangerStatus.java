package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class DangerStatus {

    @Id
    public Long id;

    public int dangerStatus1;

    public int dangerStatus2;

    public int dangerStatus3;

    public int dangerStatus4;


    @Generated(hash = 1797165363)
    public DangerStatus(Long id, int dangerStatus1, int dangerStatus2,
            int dangerStatus3, int dangerStatus4) {
        this.id = id;
        this.dangerStatus1 = dangerStatus1;
        this.dangerStatus2 = dangerStatus2;
        this.dangerStatus3 = dangerStatus3;
        this.dangerStatus4 = dangerStatus4;
    }


    @Generated(hash = 575313411)
    public DangerStatus() {
    }


    @Override
    public String toString() {
        return "DangerStatus{" +
                "id=" + id +
                ", dangerStatus1=" + dangerStatus1 +
                ", dangerStatus2=" + dangerStatus2 +
                ", dangerStatus3=" + dangerStatus3 +
                ", dangerStatus4=" + dangerStatus4 +
                '}';
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public int getDangerStatus1() {
        return this.dangerStatus1;
    }


    public void setDangerStatus1(int dangerStatus1) {
        this.dangerStatus1 = dangerStatus1;
    }


    public int getDangerStatus2() {
        return this.dangerStatus2;
    }


    public void setDangerStatus2(int dangerStatus2) {
        this.dangerStatus2 = dangerStatus2;
    }


    public int getDangerStatus3() {
        return this.dangerStatus3;
    }


    public void setDangerStatus3(int dangerStatus3) {
        this.dangerStatus3 = dangerStatus3;
    }


    public int getDangerStatus4() {
        return this.dangerStatus4;
    }


    public void setDangerStatus4(int dangerStatus4) {
        this.dangerStatus4 = dangerStatus4;
    }
}
