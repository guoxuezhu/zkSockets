package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class MicDatas {

    @Id
    public Long mic_id;
    public String mic_index;
    public int mic_status;

    @Generated(hash = 1014081608)
    public MicDatas(Long mic_id, String mic_index, int mic_status) {
        this.mic_id = mic_id;
        this.mic_index = mic_index;
        this.mic_status = mic_status;
    }

    @Generated(hash = 2072734270)
    public MicDatas() {
    }

    @Override
    public String toString() {
        return "MicDatas{" +
                "mic_id=" + mic_id +
                ", mic_index='" + mic_index + '\'' +
                ", mic_status=" + mic_status +
                '}';
    }

    public Long getMic_id() {
        return this.mic_id;
    }

    public void setMic_id(Long mic_id) {
        this.mic_id = mic_id;
    }

    public String getMic_index() {
        return this.mic_index;
    }

    public void setMic_index(String mic_index) {
        this.mic_index = mic_index;
    }

    public int getMic_status() {
        return this.mic_status;
    }

    public void setMic_status(int mic_status) {
        this.mic_status = mic_status;
    }
}