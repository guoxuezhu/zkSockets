package com.lh.zksockets.data.model;


public class HttpRow<T> {


    public int count;

    T rows;

    public T getRows() {
        return rows;
    }

    @Override
    public String toString() {
        return "HttpRow{" +
                "count=" + count +
                ", rows=" + rows +
                '}';
    }
}
