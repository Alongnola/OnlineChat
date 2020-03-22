package com.jwm.chatroom.pojo;

import com.google.gson.Gson;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class Message {

    private String content;
    private Map<String, String> names;
    private String user;
    private Date date = new Date();

    public void setContent(String headUrl, String msg) {
        // this.content = name + " " + DateFormat.getDateTimeInstance().format(date) + ":<br/> " + msg;
        this.content = headUrl + "#" + msg;
    }

    public String toJson() {
        return gson.toJson(this);
    }

    private static Gson gson = new Gson();

    public Message(String content, Map<String, String> names) {
        this.content = content;
        this.names = names;
    }

    public Message() {
    }
}
