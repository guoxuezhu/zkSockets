package com.lh.zksockets.data.model;


public class HttpData<T> {

    public int flag;
    public String msg;
//    public int count;
    T data;

    public T getData() {
        return data;
    }


    @Override
    public String toString() {
        return "HttpData{" +
                "flag=" + flag +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
