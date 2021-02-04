package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UIsetData {

    public String btn_1_status;
    public String btn_2_status;
    public String btn_3_status;
    public String btn_4_status;
    public String btn_5_status;
    public String btn_6_status;
    public String btn_7_status;
    public String btn_8_status;
    public String btn_9_status;

    @Generated(hash = 2049620878)
    public UIsetData(String btn_1_status, String btn_2_status, String btn_3_status,
                     String btn_4_status, String btn_5_status, String btn_6_status,
                     String btn_7_status, String btn_8_status, String btn_9_status) {
        this.btn_1_status = btn_1_status;
        this.btn_2_status = btn_2_status;
        this.btn_3_status = btn_3_status;
        this.btn_4_status = btn_4_status;
        this.btn_5_status = btn_5_status;
        this.btn_6_status = btn_6_status;
        this.btn_7_status = btn_7_status;
        this.btn_8_status = btn_8_status;
        this.btn_9_status = btn_9_status;
    }

    @Generated(hash = 1568590389)
    public UIsetData() {
    }

    @Override
    public String toString() {
        return "UIsetData{" +
                "btn_1_status='" + btn_1_status + '\'' +
                ", btn_2_status='" + btn_2_status + '\'' +
                ", btn_3_status='" + btn_3_status + '\'' +
                ", btn_4_status='" + btn_4_status + '\'' +
                ", btn_5_status='" + btn_5_status + '\'' +
                ", btn_6_status='" + btn_6_status + '\'' +
                ", btn_7_status='" + btn_7_status + '\'' +
                ", btn_8_status='" + btn_8_status + '\'' +
                ", btn_9_status='" + btn_9_status + '\'' +
                '}';
    }

    public String getBtn_1_status() {
        return this.btn_1_status;
    }

    public void setBtn_1_status(String btn_1_status) {
        this.btn_1_status = btn_1_status;
    }

    public String getBtn_2_status() {
        return this.btn_2_status;
    }

    public void setBtn_2_status(String btn_2_status) {
        this.btn_2_status = btn_2_status;
    }

    public String getBtn_3_status() {
        return this.btn_3_status;
    }

    public void setBtn_3_status(String btn_3_status) {
        this.btn_3_status = btn_3_status;
    }

    public String getBtn_4_status() {
        return this.btn_4_status;
    }

    public void setBtn_4_status(String btn_4_status) {
        this.btn_4_status = btn_4_status;
    }

    public String getBtn_5_status() {
        return this.btn_5_status;
    }

    public void setBtn_5_status(String btn_5_status) {
        this.btn_5_status = btn_5_status;
    }

    public String getBtn_6_status() {
        return this.btn_6_status;
    }

    public void setBtn_6_status(String btn_6_status) {
        this.btn_6_status = btn_6_status;
    }

    public String getBtn_7_status() {
        return this.btn_7_status;
    }

    public void setBtn_7_status(String btn_7_status) {
        this.btn_7_status = btn_7_status;
    }

    public String getBtn_8_status() {
        return this.btn_8_status;
    }

    public void setBtn_8_status(String btn_8_status) {
        this.btn_8_status = btn_8_status;
    }

    public String getBtn_9_status() {
        return this.btn_9_status;
    }

    public void setBtn_9_status(String btn_9_status) {
        this.btn_9_status = btn_9_status;
    }
}