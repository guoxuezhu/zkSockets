package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class VidStatus {

    @Id
    public Long id;

    public String vidinName;

    public String vidinStatus;

    @Generated(hash = 1900363989)
    public VidStatus(Long id, String vidinName, String vidinStatus) {
        this.id = id;
        this.vidinName = vidinName;
        this.vidinStatus = vidinStatus;
    }

    @Generated(hash = 862370014)
    public VidStatus() {
    }

    @Override
    public String toString() {
        return "VidStatus{" +
                "id=" + id +
                ", vidinName='" + vidinName + '\'' +
                ", vidinStatus='" + vidinStatus + '\'' +
                '}';
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVidinName() {
        return this.vidinName;
    }

    public void setVidinName(String vidinName) {
        this.vidinName = vidinName;
    }

    public String getVidinStatus() {
        return this.vidinStatus;
    }

    public void setVidinStatus(String vidinStatus) {
        this.vidinStatus = vidinStatus;
    }
}
