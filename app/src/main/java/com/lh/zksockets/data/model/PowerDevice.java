package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class PowerDevice {

    @Id
    public Long chazuoId;

    public String chazuoName;
    
    public String bindName;

    public int openTime;

    public int closedTime;




    @Generated(hash = 1812988197)
    public PowerDevice(Long chazuoId, String chazuoName, String bindName,
            int openTime, int closedTime) {
        this.chazuoId = chazuoId;
        this.chazuoName = chazuoName;
        this.bindName = bindName;
        this.openTime = openTime;
        this.closedTime = closedTime;
    }




    @Generated(hash = 1030580101)
    public PowerDevice() {
    }




    @Override
    public String toString() {
        return "PowerDevice{" +
                "chazuoId=" + chazuoId +
                ", chazuoName='" + chazuoName + '\'' +
                ", bindName='" + bindName + '\'' +
                ", openTime=" + openTime +
                ", closedTime=" + closedTime +
                '}';
    }




    public Long getChazuoId() {
        return this.chazuoId;
    }




    public void setChazuoId(Long chazuoId) {
        this.chazuoId = chazuoId;
    }




    public String getChazuoName() {
        return this.chazuoName;
    }




    public void setChazuoName(String chazuoName) {
        this.chazuoName = chazuoName;
    }




    public String getBindName() {
        return this.bindName;
    }




    public void setBindName(String bindName) {
        this.bindName = bindName;
    }




    public int getOpenTime() {
        return this.openTime;
    }




    public void setOpenTime(int openTime) {
        this.openTime = openTime;
    }




    public int getClosedTime() {
        return this.closedTime;
    }




    public void setClosedTime(int closedTime) {
        this.closedTime = closedTime;
    }


}
