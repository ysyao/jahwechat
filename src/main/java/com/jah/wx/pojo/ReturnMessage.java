package com.jah.wx.pojo;

import java.util.List;

public class ReturnMessage<T, V> {
    private T object;
    private List<V> list;
    private int code;
    private String message;
    private String encrytedJson;
    private String decrytedJson;

    public String getEncrytedJson() {
        return encrytedJson;
    }

    public void setEncrytedJson(String encrytedJson) {
        this.encrytedJson = encrytedJson;
    }

    public String getDecrytedJson() {
        return decrytedJson;
    }

    public void setDecrytedJson(String decrytedJson) {
        this.decrytedJson = decrytedJson;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public List<V> getList() {
        return list;
    }

    public void setList(List<V> list) {
        this.list = list;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
