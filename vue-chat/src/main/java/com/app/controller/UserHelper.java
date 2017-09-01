package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.app.other.User;

@Component
public class UserHelper {

    private List<User> users = new ArrayList<User>();

    public void addUser(User user){
        this.users.add(user);
    }
    
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
