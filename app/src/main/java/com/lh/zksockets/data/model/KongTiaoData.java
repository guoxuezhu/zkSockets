package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class KongTiaoData {

    public String wenstr_re;
    public String re_timeStart;
    public String re_timeEnd;
    public String re_ml;
    public String wenstr_leng;
    public String leng_timeStart;
    public String leng_timeEnd;
    public String leng_ml;
    public int kt_status;

    @Generated(hash = 1149075918)
    public KongTiaoData(String wenstr_re, String re_timeStart, String re_timeEnd,
            String re_ml, String wenstr_leng, String leng_timeStart,
            String leng_timeEnd, String leng_ml, int kt_status) {
        this.wenstr_re = wenstr_re;
        this.re_timeStart = re_timeStart;
        this.re_timeEnd = re_timeEnd;
        this.re_ml = re_ml;
        this.wenstr_leng = wenstr_leng;
        this.leng_timeStart = leng_timeStart;
        this.leng_timeEnd = leng_timeEnd;
        this.leng_ml = leng_ml;
        this.kt_status = kt_status;
    }

    @Generated(hash = 907548345)
    public KongTiaoData() {
    }

    @Override
    public String toString() {
        return "KongTiaoData{" +
                "wenstr_re='" + wenstr_re + '\'' +
                ", re_timeStart='" + re_timeStart + '\'' +
                ", re_timeEnd='" + re_timeEnd + '\'' +
                ", re_ml='" + re_ml + '\'' +
                ", wenstr_leng='" + wenstr_leng + '\'' +
                ", leng_timeStart='" + leng_timeStart + '\'' +
                ", leng_timeEnd='" + leng_timeEnd + '\'' +
                ", leng_ml='" + leng_ml + '\'' +
                ", kt_status=" + kt_status +
                '}';
    }

    public String getWenstr_re() {
        return this.wenstr_re;
    }

    public void setWenstr_re(String wenstr_re) {
        this.wenstr_re = wenstr_re;
    }

    public String getRe_timeStart() {
        return this.re_timeStart;
    }

    public void setRe_timeStart(String re_timeStart) {
        this.re_timeStart = re_timeStart;
    }

    public String getRe_timeEnd() {
        return this.re_timeEnd;
    }

    public void setRe_timeEnd(String re_timeEnd) {
        this.re_timeEnd = re_timeEnd;
    }

    public String getRe_ml() {
        return this.re_ml;
    }

    public void setRe_ml(String re_ml) {
        this.re_ml = re_ml;
    }

    public String getWenstr_leng() {
        return this.wenstr_leng;
    }

    public void setWenstr_leng(String wenstr_leng) {
        this.wenstr_leng = wenstr_leng;
    }

    public String getLeng_timeStart() {
        return this.leng_timeStart;
    }

    public void setLeng_timeStart(String leng_timeStart) {
        this.leng_timeStart = leng_timeStart;
    }

    public String getLeng_timeEnd() {
        return this.leng_timeEnd;
    }

    public void setLeng_timeEnd(String leng_timeEnd) {
        this.leng_timeEnd = leng_timeEnd;
    }

    public String getLeng_ml() {
        return this.leng_ml;
    }

    public void setLeng_ml(String leng_ml) {
        this.leng_ml = leng_ml;
    }

    public int getKt_status() {
        return this.kt_status;
    }

    public void setKt_status(int kt_status) {
        this.kt_status = kt_status;
    }
}
