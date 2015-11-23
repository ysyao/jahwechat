package com.jah.wx.hello.controller;

import com.jah.wx.hello.model.WelcomeMessage;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class WelcomeController {

    @RequestMapping("hello")
    public @ResponseBody WelcomeMessage welcome() {
        WelcomeMessage message = new WelcomeMessage();
        message.setMessage("今天是个好天气");
        message.setName("哈哈");
        return message;
    }
}
