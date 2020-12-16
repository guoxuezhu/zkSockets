package com.lh.zksockets.data.model;


public class HttpResult<T> {

    public String code;
    public String message;
    public boolean success;

    T data;

    public T getData() {
        return data;
    }

    public HttpResult(String code, String message, boolean success, T data) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", success=" + success +
                ", data=" + data +
                '}';
    }
}
