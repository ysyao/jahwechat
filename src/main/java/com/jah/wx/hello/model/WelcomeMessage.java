package com.jah.wx.hello.model;

public class WelcomeMessage {
    private String message;
    private String name;

    public interface HelloMessage{}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
