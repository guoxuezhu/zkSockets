package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class IoPortData {

    @Id
    public Long id;
    public String ioName;
    public boolean Vgd;
    public String strML;
    @Generated(hash = 382964495)
    public IoPortData(Long id, String ioName, boolean Vgd, String strML) {
        this.id = id;
        this.ioName = ioName;
        this.Vgd = Vgd;
        this.strML = strML;
    }
    @Generated(hash = 1124049858)
    public IoPortData() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getIoName() {
        return this.ioName;
    }
    public void setIoName(String ioName) {
        this.ioName = ioName;
    }
    public boolean getVgd() {
        return this.Vgd;
    }
    public void setVgd(boolean Vgd) {
        this.Vgd = Vgd;
    }
    public String getStrML() {
        return this.strML;
    }
    public void setStrML(String strML) {
        this.strML = strML;
    }

    @Override
    public String toString() {
        return "IoPortData{" +
                "id=" + id +
                ", ioName='" + ioName + '\'' +
                ", Vgd=" + Vgd +
                ", strML='" + strML + '\'' +
                '}';
    }
}
