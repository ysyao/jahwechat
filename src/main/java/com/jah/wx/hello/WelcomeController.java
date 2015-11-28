package com.jah.wx.hello;

import com.jah.wx.pojo.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class WelcomeController {
    @Autowired
    WelcomeManager welcomeManager;
    @Autowired
    WelcomeDao welcomeDao;

    @RequestMapping("hello")
    public @ResponseBody WelcomeMessage welcome() {
        WelcomeMessage message = new WelcomeMessage();
        message.setMessage("今天是个好天气");
        message.setName("哈哈");
        return message;
    }

    @RequestMapping("hello/{id}")
    public @ResponseBody
    ReturnMessage<WelcomeMessage, Void> getUserById(@PathVariable("id") int id) {
        ReturnMessage<WelcomeMessage, Void> returnMessage = new ReturnMessage<WelcomeMessage, Void>();
        WelcomeMessage welcomeMessage = welcomeDao.getUserById(id);
        if (welcomeMessage == null) {
            returnMessage.setCode(1);
            returnMessage.setMessage("Can not find user by this id:"+id);
        } else {
            returnMessage.setCode(0);
            returnMessage.setObject(welcomeMessage);
            returnMessage.setMessage("success");
        }

        return returnMessage;
    }
}
