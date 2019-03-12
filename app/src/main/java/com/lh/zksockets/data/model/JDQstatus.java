package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class JDQstatus {


    @Id
    public Long id;

    public int jdq1; //1 开      0 关
    public int jdq2;
    public int jdq3;
    public int jdq4;
    public int jdq5;
    public int jdq6;


    @Generated(hash = 1956495542)
    public JDQstatus(Long id, int jdq1, int jdq2, int jdq3, int jdq4, int jdq5,
            int jdq6) {
        this.id = id;
        this.jdq1 = jdq1;
        this.jdq2 = jdq2;
        this.jdq3 = jdq3;
        this.jdq4 = jdq4;
        this.jdq5 = jdq5;
        this.jdq6 = jdq6;
    }


    @Generated(hash = 1434542256)
    public JDQstatus() {
    }


    @Override
    public String toString() {
        return "JDQstatus{" +
                "id=" + id +
                ", jdq1=" + jdq1 +
                ", jdq2=" + jdq2 +
                ", jdq3=" + jdq3 +
                ", jdq4=" + jdq4 +
                ", jdq5=" + jdq5 +
                ", jdq6=" + jdq6 +
                '}';
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public int getJdq1() {
        return this.jdq1;
    }


    public void setJdq1(int jdq1) {
        this.jdq1 = jdq1;
    }


    public int getJdq2() {
        return this.jdq2;
    }


    public void setJdq2(int jdq2) {
        this.jdq2 = jdq2;
    }


    public int getJdq3() {
        return this.jdq3;
    }


    public void setJdq3(int jdq3) {
        this.jdq3 = jdq3;
    }


    public int getJdq4() {
        return this.jdq4;
    }


    public void setJdq4(int jdq4) {
        this.jdq4 = jdq4;
    }


    public int getJdq5() {
        return this.jdq5;
    }


    public void setJdq5(int jdq5) {
        this.jdq5 = jdq5;
    }


    public int getJdq6() {
        return this.jdq6;
    }


    public void setJdq6(int jdq6) {
        this.jdq6 = jdq6;
    }
}
