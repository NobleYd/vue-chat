package com.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.other.User;

@RestController
public class LoginController {

    @Autowired
    UserHelper userHelper;

    @RequestMapping("/login")
    public User login(HttpSession session, String nickName) {
        User user = new User(nickName);
        session.setAttribute("user", user);

        userHelper.addUser(user);

        return user;
    }

}
