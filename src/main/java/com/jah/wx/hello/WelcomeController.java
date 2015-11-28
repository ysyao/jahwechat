package com.jah.wx.hello;

import com.jah.wx.pojo.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class WelcomeController {
    @Autowired
    private WelcomeManager welcomeManager;
    @Autowired
    private WelcomeDao welcomeDao;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public @ResponseBody WelcomeMessage welcome() {
        WelcomeMessage message = new WelcomeMessage();
        message.setMessage("今天是个好天气");
        message.setName("哈哈");
        return message;
    }

    @RequestMapping(value = "hello/{id}", method = RequestMethod.GET)
    public @ResponseBody ReturnMessage<WelcomeMessage, Void> getUserById(@PathVariable("id") int id) {
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

    @RequestMapping(value = "hello/add", method = RequestMethod.POST)
    public @ResponseBody ReturnMessage<Void, Void> addUser(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "message", required = true) String message)
    {
        ReturnMessage<Void, Void> returnMessage = new ReturnMessage<Void, Void>();
        WelcomeMessage welcomeMessage = new WelcomeMessage();
        welcomeMessage.setName(name);
        welcomeMessage.setMessage(message);
        int rowNum = welcomeDao.addUser(welcomeMessage);
        if (rowNum > 0) {
           returnMessage.setCode(0);
            returnMessage.setMessage("success");
        } else {
            returnMessage.setCode(1);
            returnMessage.setMessage("add user failed");
        }
        return returnMessage;
    }
}
