package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class IcCard {

    public String workNum;

    public int icType;

    public String terName;

    public String department;

    @Id
    public Long cardNum;

    public String updataTime;

    @Generated(hash = 827250967)
    public IcCard(String workNum, int icType, String terName, String department,
            Long cardNum, String updataTime) {
        this.workNum = workNum;
        this.icType = icType;
        this.terName = terName;
        this.department = department;
        this.cardNum = cardNum;
        this.updataTime = updataTime;
    }

    @Generated(hash = 2135423348)
    public IcCard() {
    }

    @Override
    public String toString() {
        return "IcCard{" +
                "workNum='" + workNum + '\'' +
                ", icType=" + icType +
                ", terName='" + terName + '\'' +
                ", department='" + department + '\'' +
                ", cardNum=" + cardNum +
                ", updataTime='" + updataTime + '\'' +
                '}';
    }

    public String getWorkNum() {
        return this.workNum;
    }

    public void setWorkNum(String workNum) {
        this.workNum = workNum;
    }

    public int getIcType() {
        return this.icType;
    }

    public void setIcType(int icType) {
        this.icType = icType;
    }

    public String getTerName() {
        return this.terName;
    }

    public void setTerName(String terName) {
        this.terName = terName;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getCardNum() {
        return this.cardNum;
    }

    public void setCardNum(Long cardNum) {
        this.cardNum = cardNum;
    }

    public String getUpdataTime() {
        return this.updataTime;
    }

    public void setUpdataTime(String updataTime) {
        this.updataTime = updataTime;
    }
}