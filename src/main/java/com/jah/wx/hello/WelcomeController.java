package com.jah.wx.hello;

import com.jah.wx.pojo.ReturnMessage;
import com.jah.wx.utils.JsonEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class WelcomeController {
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private WelcomeManager welcomeManager;
    @Autowired
    private WelcomeDao welcomeDao;
    @Autowired
    private JsonEncrypter jsonEncrypter;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public @ResponseBody WelcomeMessage welcome() {
        WelcomeMessage message = new WelcomeMessage();
        message.setMessage("今天是个好天气");
        message.setName("哈哈");
        return message;
    }

    @RequestMapping(value = "hello/{id}", method = RequestMethod.GET)
    public @ResponseBody ReturnMessage<WelcomeMessage, Void> getUserById(@PathVariable("id") int id) throws Exception {
        ReturnMessage<WelcomeMessage, Void> returnMessage = new ReturnMessage<WelcomeMessage, Void>();
        WelcomeMessage welcomeMessage = welcomeDao.getUserById(id);
        if (welcomeMessage == null) {
            returnMessage.setCode(1);
            returnMessage.setMessage("Can not find user by this id:"+id);
        } else {
            returnMessage.setCode(0);
            returnMessage.setObject(welcomeMessage);
            returnMessage.setMessage("success");
            String encryStr = jsonEncrypter.encryptObject(welcomeMessage);
            returnMessage.setEncrytedJson(encryStr);
            returnMessage.setDecrytedJson(jsonEncrypter.decryptObject(encryStr));
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
