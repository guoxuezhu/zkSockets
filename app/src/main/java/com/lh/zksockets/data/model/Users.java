package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class Users {

    @Id(autoincrement = true)
    public Long id;

    public String username;

    public String userPaw;

    public int usertype;


    @Generated(hash = 1176685848)
    public Users(Long id, String username, String userPaw, int usertype) {
        this.id = id;
        this.username = username;
        this.userPaw = userPaw;
        this.usertype = usertype;
    }


    @Generated(hash = 2146996206)
    public Users() {
    }


    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userPaw='" + userPaw + '\'' +
                ", usertype=" + usertype +
                '}';
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getUsername() {
        return this.username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getUserPaw() {
        return this.userPaw;
    }


    public void setUserPaw(String userPaw) {
        this.userPaw = userPaw;
    }


    public int getUsertype() {
        return this.usertype;
    }


    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }
}