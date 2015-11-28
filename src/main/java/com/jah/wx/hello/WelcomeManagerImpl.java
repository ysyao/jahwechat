package com.jah.wx.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("welcomeManager")
public class WelcomeManagerImpl implements WelcomeManager {
    @Autowired
    WelcomeDao welcomeDao;
    public WelcomeMessage getUserById(int id) {
        return welcomeDao.getUserById(id);
    }
}
