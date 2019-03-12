package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class IOYuan {


    @Id
    public Long id;

    public int dangerIo1; //1 高      0 底
    public int dangerIo2;
    public int dangerIo3;
    public int dangerIo4;


    @Generated(hash = 364110937)
    public IOYuan(Long id, int dangerIo1, int dangerIo2, int dangerIo3,
            int dangerIo4) {
        this.id = id;
        this.dangerIo1 = dangerIo1;
        this.dangerIo2 = dangerIo2;
        this.dangerIo3 = dangerIo3;
        this.dangerIo4 = dangerIo4;
    }


    @Generated(hash = 1488061952)
    public IOYuan() {
    }


    @Override
    public String toString() {
        return "IOYuan{" +
                "id=" + id +
                ", dangerIo1=" + dangerIo1 +
                ", dangerIo2=" + dangerIo2 +
                ", dangerIo3=" + dangerIo3 +
                ", dangerIo4=" + dangerIo4 +
                '}';
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public int getDangerIo1() {
        return this.dangerIo1;
    }


    public void setDangerIo1(int dangerIo1) {
        this.dangerIo1 = dangerIo1;
    }


    public int getDangerIo2() {
        return this.dangerIo2;
    }


    public void setDangerIo2(int dangerIo2) {
        this.dangerIo2 = dangerIo2;
    }


    public int getDangerIo3() {
        return this.dangerIo3;
    }


    public void setDangerIo3(int dangerIo3) {
        this.dangerIo3 = dangerIo3;
    }


    public int getDangerIo4() {
        return this.dangerIo4;
    }


    public void setDangerIo4(int dangerIo4) {
        this.dangerIo4 = dangerIo4;
    }
}
