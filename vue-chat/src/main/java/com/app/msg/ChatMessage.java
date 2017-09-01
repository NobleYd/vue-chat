package com.app.msg;

import java.util.Date;

public class ChatMessage {

    private String type = "chat";

    private Long from;

    private Long to;

    private String content;

    private String date = new Date().toString();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ChatMessage(Long from, Long to, String content) {
        super();
        this.from = from;
        this.to = to;
        this.content = content;
    }

    public ChatMessage() {
        super();
    }

    @Override
    public String toString() {
        return "ChatMessage [type=" + type + ", from=" + from + ", to=" + to + ", content=" + content + "]";
    }

}
