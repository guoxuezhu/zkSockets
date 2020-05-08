package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class IcCard {

    public String workNum;

    public String name;

    public String card_no;

    @Id
    public Long cardNumId;

    public int role;

    public String updataTime;

    public String status;


    @Generated(hash = 1566743293)
    public IcCard(String workNum, String name, String card_no, Long cardNumId,
            int role, String updataTime, String status) {
        this.workNum = workNum;
        this.name = name;
        this.card_no = card_no;
        this.cardNumId = cardNumId;
        this.role = role;
        this.updataTime = updataTime;
        this.status = status;
    }


    @Generated(hash = 2135423348)
    public IcCard() {
    }

    @Override
    public String toString() {
        return "IcCard{" +
                "workNum='" + workNum + '\'' +
                ", name='" + name + '\'' +
                ", card_no='" + card_no + '\'' +
                ", cardNumId=" + cardNumId +
                ", role=" + role +
                ", updataTime='" + updataTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getWorkNum() {
        return this.workNum;
    }


    public void setWorkNum(String workNum) {
        this.workNum = workNum;
    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getCard_no() {
        return this.card_no;
    }


    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }


    public Long getCardNumId() {
        return this.cardNumId;
    }


    public void setCardNumId(Long cardNumId) {
        this.cardNumId = cardNumId;
    }


    public int getRole() {
        return this.role;
    }


    public void setRole(int role) {
        this.role = role;
    }


    public String getUpdataTime() {
        return this.updataTime;
    }


    public void setUpdataTime(String updataTime) {
        this.updataTime = updataTime;
    }


    public String getStatus() {
        return this.status;
    }


    public void setStatus(String status) {
        this.status = status;
    }
}