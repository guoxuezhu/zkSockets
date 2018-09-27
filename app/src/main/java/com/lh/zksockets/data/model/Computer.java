package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Computer {

    public String IP;
    public String PORT;
    public String userName;
    public String Password;
    public String openCommand;
    public String closedCommand;

    @Generated(hash = 599429489)
    public Computer(String IP, String PORT, String userName, String Password,
            String openCommand, String closedCommand) {
        this.IP = IP;
        this.PORT = PORT;
        this.userName = userName;
        this.Password = Password;
        this.openCommand = openCommand;
        this.closedCommand = closedCommand;
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
                ", openCommand='" + openCommand + '\'' +
                ", closedCommand='" + closedCommand + '\'' +
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

    public String getOpenCommand() {
        return this.openCommand;
    }

    public void setOpenCommand(String openCommand) {
        this.openCommand = openCommand;
    }

    public String getClosedCommand() {
        return this.closedCommand;
    }

    public void setClosedCommand(String closedCommand) {
        this.closedCommand = closedCommand;
    }
}
