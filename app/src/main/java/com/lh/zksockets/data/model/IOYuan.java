package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class IOYuan {


    @Id
    public Long id;

    public int dangerIoStatus; //1 高      0 低

    public String dangerMl;//报警操作

    public String noDangerMl;//不报警操作

    @Generated(hash = 1248375324)
    public IOYuan(Long id, int dangerIoStatus, String dangerMl, String noDangerMl) {
        this.id = id;
        this.dangerIoStatus = dangerIoStatus;
        this.dangerMl = dangerMl;
        this.noDangerMl = noDangerMl;
    }

    @Generated(hash = 1488061952)
    public IOYuan() {
    }

    @Override
    public String toString() {
        return "IOYuan{" +
                "id=" + id +
                ", dangerIoStatus=" + dangerIoStatus +
                ", dangerMl='" + dangerMl + '\'' +
                ", noDangerMl='" + noDangerMl + '\'' +
                '}';
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDangerIoStatus() {
        return this.dangerIoStatus;
    }

    public void setDangerIoStatus(int dangerIoStatus) {
        this.dangerIoStatus = dangerIoStatus;
    }

    public String getDangerMl() {
        return this.dangerMl;
    }

    public void setDangerMl(String dangerMl) {
        this.dangerMl = dangerMl;
    }

    public String getNoDangerMl() {
        return this.noDangerMl;
    }

    public void setNoDangerMl(String noDangerMl) {
        this.noDangerMl = noDangerMl;
    }
}
