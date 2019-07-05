package com.lh.zksockets.data.model;

public class Vidstatus {

    public String name;
    public int status;

    public Vidstatus(String name, int status) {
        this.name = name;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Vidstatus{" +
                "name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
