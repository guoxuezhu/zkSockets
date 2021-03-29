package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class UIsetData {

    @Id
    public Long btnId;

    public String btn_name;

    public String btn_status;


    @Generated(hash = 480825843)
    public UIsetData(Long btnId, String btn_name, String btn_status) {
        this.btnId = btnId;
        this.btn_name = btn_name;
        this.btn_status = btn_status;
    }


    @Generated(hash = 1568590389)
    public UIsetData() {
    }


    @Override
    public String toString() {
        return "UIsetData{" +
                "btnId=" + btnId +
                ", btn_name='" + btn_name + '\'' +
                ", btn_status='" + btn_status + '\'' +
                '}';
    }


    public Long getBtnId() {
        return this.btnId;
    }


    public void setBtnId(Long btnId) {
        this.btnId = btnId;
    }


    public String getBtn_name() {
        return this.btn_name;
    }


    public void setBtn_name(String btn_name) {
        this.btn_name = btn_name;
    }


    public String getBtn_status() {
        return this.btn_status;
    }


    public void setBtn_status(String btn_status) {
        this.btn_status = btn_status;
    }
}