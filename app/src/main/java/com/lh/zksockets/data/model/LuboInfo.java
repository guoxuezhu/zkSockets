package com.lh.zksockets.data.model;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class LuboInfo {

    @SerializedName("record_ip")
    public String IP;
    @SerializedName("record_user")
    public String userName;
    @SerializedName("record_pass")
    public String Password;
    @SerializedName("token")
    public String token;
    @SerializedName("status")
    public int status;

    @Generated(hash = 1657002293)
    public LuboInfo(String IP, String userName, String Password, String token,
            int status) {
        this.IP = IP;
        this.userName = userName;
        this.Password = Password;
        this.token = token;
        this.status = status;
    }

    @Generated(hash = 351647369)
    public LuboInfo() {
    }

    @Override
    public String toString() {
        return "LuboInfo{" +
                "IP='" + IP + '\'' +
                ", userName='" + userName + '\'' +
                ", Password='" + Password + '\'' +
                ", token='" + token + '\'' +
                ", status=" + status +
                '}';
    }

    public String getIP() {
        return this.IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
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

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
