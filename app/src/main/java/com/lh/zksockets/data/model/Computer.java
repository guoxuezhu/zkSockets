package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Computer {

    public String IP;
    public String PORT;
    public String userName;
    public String Password;

    @Generated(hash = 2132518220)
    public Computer(String IP, String PORT, String userName, String Password) {
        this.IP = IP;
        this.PORT = PORT;
        this.userName = userName;
        this.Password = Password;
    }

    @Generated(hash = 1238779503)
    public Computer() {
    }

    @Override
    public String toString() {
        return "Computer{" +
                "IP='" + IP + '\'' +
                ", PORT='" + PORT + '\'' +
                ", userName='" + userName + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }

    public String getIP() {
        return this.IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getPORT() {
        return this.PORT;
    }

    public void setPORT(String PORT) {
        this.PORT = PORT;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
}
