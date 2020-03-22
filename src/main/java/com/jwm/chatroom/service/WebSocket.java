package com.jwm.chatroom.service;

import com.google.gson.Gson;
import com.jwm.chatroom.pojo.Content;
import com.jwm.chatroom.pojo.Message;
import com.jwm.chatroom.pojo.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/webSocket")
public class WebSocket {

    private Session session;
    private String username;
    private String headUrl;

    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();
    private static Map<String, String> map = new HashMap<>();

    private static RecordService recordService;

    @Autowired
    public void setRecordService(RecordService recordService){
        WebSocket.recordService = recordService;
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSockets.add(this);
        String s = session.getQueryString();

        String param1 = s.split("&")[0];
        String param2 = s.split("&")[1];
        try {
            username = param1.split("=")[1];
            headUrl = param2.split("=")[1];
        } catch (Exception e) {
            e.printStackTrace();
        }

        map.put(session.getId(), username);
        System.out.println("有新的连接，总数：" + webSockets.size() + "  sessionId：" + session.getId() + "  " + username);
        String content = username + " 加入了聊天室！";
        Message message = new Message(content, map);
        send(message.toJson());
    }

    @OnClose
    public void onClose() {
        webSockets.remove(this);
        map.remove(session.getId());
        System.out.println("有新的断开，总数：" + webSockets.size() + "  sessionId：" + session.getId());
        String content = username + " 离开了聊天室！";
        Message message = new Message(content, map);
        send(message.toJson());
    }


    private static Gson gson = new Gson();

    @OnMessage
    public void onMessage(String json) {
        Content content = gson.fromJson(json, Content.class);

        //System.out.println(content);
        Record record = new Record();
        record.setContent(content.getMsg());
        record.setCreated_date(LocalDateTime.now());
        record.setHeadUrl(headUrl);
        recordService.addRecord(record);

        if (content.getType() == 1) {
            //广播
            Message message = new Message();
            message.setContent(this.headUrl, content.getMsg());
            message.setNames(map);
            send(message.toJson());
        } else {
            //单聊
            Message message = new Message();
            message.setContent(this.headUrl, content.getMsg());
            message.setNames(map);

            String to = content.getTo();
            String tos[] = to.substring(0, to.length() - 1).split("-");
            List<String> lists = Arrays.asList(tos);
            for (WebSocket webSocket : webSockets) {
                if (lists.contains(webSocket.session.getId()) && webSocket.session.getId() != this.session.getId()) {
                    try {
                        webSocket.session.getBasicRemote().sendText(message.toJson());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    public void send(String message) {
        for (WebSocket webSocket : webSockets) {
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
