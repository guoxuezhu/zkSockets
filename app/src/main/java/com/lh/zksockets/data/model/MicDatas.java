package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class MicDatas {

    public String mic_a;
    public int mic_a_status;

    public String mic_b;
    public int mic_b_status;

    public String mic_c;
    public int mic_c_status;

    @Generated(hash = 519697728)
    public MicDatas(String mic_a, int mic_a_status, String mic_b, int mic_b_status,
            String mic_c, int mic_c_status) {
        this.mic_a = mic_a;
        this.mic_a_status = mic_a_status;
        this.mic_b = mic_b;
        this.mic_b_status = mic_b_status;
        this.mic_c = mic_c;
        this.mic_c_status = mic_c_status;
    }

    @Generated(hash = 2072734270)
    public MicDatas() {
    }

    @Override
    public String toString() {
        return "MicDatas{" +
                "mic_a='" + mic_a + '\'' +
                ", mic_a_status=" + mic_a_status +
                ", mic_b='" + mic_b + '\'' +
                ", mic_b_status=" + mic_b_status +
                ", mic_c='" + mic_c + '\'' +
                ", mic_c_status=" + mic_c_status +
                '}';
    }

    public String getMic_a() {
        return this.mic_a;
    }

    public void setMic_a(String mic_a) {
        this.mic_a = mic_a;
    }

    public int getMic_a_status() {
        return this.mic_a_status;
    }

    public void setMic_a_status(int mic_a_status) {
        this.mic_a_status = mic_a_status;
    }

    public String getMic_b() {
        return this.mic_b;
    }

    public void setMic_b(String mic_b) {
        this.mic_b = mic_b;
    }

    public int getMic_b_status() {
        return this.mic_b_status;
    }

    public void setMic_b_status(int mic_b_status) {
        this.mic_b_status = mic_b_status;
    }

    public String getMic_c() {
        return this.mic_c;
    }

    public void setMic_c(String mic_c) {
        this.mic_c = mic_c;
    }

    public int getMic_c_status() {
        return this.mic_c_status;
    }

    public void setMic_c_status(int mic_c_status) {
        this.mic_c_status = mic_c_status;
    }
}