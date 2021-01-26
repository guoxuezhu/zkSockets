package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class WgDeviceInfo {

    @Id(autoincrement = true)
    public Long longId;

    public int deviceid;
    public String stringId;
    public String name;
    public int port;
    public int agreement;
    public int serial;
    public int poweronctm; //0-100 亮度
    public int cts; // 0开，1关  2暂停("pt": 100　 //0 全关  100 全开)
    public boolean on; // true开  false关
    public String attr;
    public int bir;
    public int current; //电流 0.01A
    public int voltage; //电压 0.01v
    public int energy; //电能0.1WH
    public int power; //功率 0.1W
    public double mtemp; // 温度
    public double mhumi; // 湿度
    public int pm25; //pm2.5

    @Generated(hash = 1140609128)
    public WgDeviceInfo(Long longId, int deviceid, String stringId, String name,
            int port, int agreement, int serial, int poweronctm, int cts,
            boolean on, String attr, int bir, int current, int voltage, int energy,
            int power, double mtemp, double mhumi, int pm25) {
        this.longId = longId;
        this.deviceid = deviceid;
        this.stringId = stringId;
        this.name = name;
        this.port = port;
        this.agreement = agreement;
        this.serial = serial;
        this.poweronctm = poweronctm;
        this.cts = cts;
        this.on = on;
        this.attr = attr;
        this.bir = bir;
        this.current = current;
        this.voltage = voltage;
        this.energy = energy;
        this.power = power;
        this.mtemp = mtemp;
        this.mhumi = mhumi;
        this.pm25 = pm25;
    }

    @Generated(hash = 1790403076)
    public WgDeviceInfo() {
    }

    @Override
    public String toString() {
        return "WgDeviceInfo{" +
                "longId=" + longId +
                ", deviceid=" + deviceid +
                ", stringId='" + stringId + '\'' +
                ", name='" + name + '\'' +
                ", port=" + port +
                ", agreement=" + agreement +
                ", serial=" + serial +
                ", poweronctm=" + poweronctm +
                ", cts=" + cts +
                ", on=" + on +
                ", attr='" + attr + '\'' +
                ", bir=" + bir +
                ", current=" + current +
                ", voltage=" + voltage +
                ", energy=" + energy +
                ", power=" + power +
                ", mtemp=" + mtemp +
                ", mhumi=" + mhumi +
                ", pm25=" + pm25 +
                '}';
    }

    public Long getLongId() {
        return this.longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public int getDeviceid() {
        return this.deviceid;
    }

    public void setDeviceid(int deviceid) {
        this.deviceid = deviceid;
    }

    public String getStringId() {
        return this.stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getAgreement() {
        return this.agreement;
    }

    public void setAgreement(int agreement) {
        this.agreement = agreement;
    }

    public int getSerial() {
        return this.serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public int getPoweronctm() {
        return this.poweronctm;
    }

    public void setPoweronctm(int poweronctm) {
        this.poweronctm = poweronctm;
    }

    public int getCts() {
        return this.cts;
    }

    public void setCts(int cts) {
        this.cts = cts;
    }

    public boolean getOn() {
        return this.on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public String getAttr() {
        return this.attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public int getBir() {
        return this.bir;
    }

    public void setBir(int bir) {
        this.bir = bir;
    }

    public int getCurrent() {
        return this.current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getVoltage() {
        return this.voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    public int getEnergy() {
        return this.energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getPower() {
        return this.power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public double getMtemp() {
        return this.mtemp;
    }

    public void setMtemp(double mtemp) {
        this.mtemp = mtemp;
    }

    public double getMhumi() {
        return this.mhumi;
    }

    public void setMhumi(double mhumi) {
        this.mhumi = mhumi;
    }

    public int getPm25() {
        return this.pm25;
    }

    public void setPm25(int pm25) {
        this.pm25 = pm25;
    }
}
