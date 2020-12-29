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

    public int user_permission;

    public Long login_time;

    public int login_count;

    public int user_status;

    @Generated(hash = 634424600)
    public Users(Long id, String username, String userPaw, int usertype,
            int user_permission, Long login_time, int login_count,
            int user_status) {
        this.id = id;
        this.username = username;
        this.userPaw = userPaw;
        this.usertype = usertype;
        this.user_permission = user_permission;
        this.login_time = login_time;
        this.login_count = login_count;
        this.user_status = user_status;
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
                ", user_permission=" + user_permission +
                ", login_time=" + login_time +
                ", login_count=" + login_count +
                ", user_status=" + user_status +
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

    public int getUser_permission() {
        return this.user_permission;
    }

    public void setUser_permission(int user_permission) {
        this.user_permission = user_permission;
    }

    public Long getLogin_time() {
        return this.login_time;
    }

    public void setLogin_time(Long login_time) {
        this.login_time = login_time;
    }

    public int getLogin_count() {
        return this.login_count;
    }

    public void setLogin_count(int login_count) {
        this.login_count = login_count;
    }

    public int getUser_status() {
        return this.user_status;
    }

    public void setUser_status(int user_status) {
        this.user_status = user_status;
    }
}