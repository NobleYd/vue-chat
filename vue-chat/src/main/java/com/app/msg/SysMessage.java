package com.app.msg;

import java.util.List;

import com.app.other.User;

public class SysMessage {

    private String type = "sys";

    private String content;

    private List<User> users;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public SysMessage(String content, List<User> users) {
        super();
        this.content = content;
        this.users = users;
    }

    public SysMessage() {
        super();
    }

    @Override
    public String toString() {
        return "SysMessage [type=" + type + ", content=" + content + "]";
    }

}
