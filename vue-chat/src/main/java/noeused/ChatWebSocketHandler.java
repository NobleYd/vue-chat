package noeused;
//package com.app;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.AbstractWebSocketHandler;
//
//@Component
//public class ChatWebSocketHandler extends AbstractWebSocketHandler{
//
//    private Map<User, WebSocketSession> webSocketSessions = new HashMap<User, WebSocketSession>();
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        System.out.println("WebSocketHandler.afterConnectionEstablished()");
//        // 记录这个session
//        User user = (User) session.getAttributes().get("user");
//        webSocketSessions.put(user, session);
//        // 群发通知
//        Message message = new Message("in", "潇洒的" + user.getNickName() + "进入了聊天室！", new ArrayList<User>(webSocketSessions.keySet()));
//        WebSocketMessage<String> msg = new TextMessage(JacksonUtils.toJson(message));
//        send2all(msg);
//    }
//
//    @Override
//    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
//        System.out.println("WebSocketHandler.handleMessage()");
//        // 群发消息
//        Message message0 = new Message("msg", message.getPayload().toString(), null);
//        WebSocketMessage<String> msg = new TextMessage(JacksonUtils.toJson(message0));
//        send2all(msg);
//    }
//
//    @Override
//    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
//        System.out.println("WebSocketHandler.handleTransportError()");
//        // 关闭连接
//        User user = (User) session.getAttributes().get("user");
//        session.close();
//        // 从当前session列表中删除
//        webSocketSessions.remove(user);
//        // 群发通知
//        Message message = new Message("out", "帅气的" + user.getNickName() + "悄悄的离开了！", new ArrayList<User>(webSocketSessions.keySet()));
//        WebSocketMessage<String> msg = new TextMessage(JacksonUtils.toJson(message));
//        send2all(msg);
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
//        System.out.println("WebSocketHandler.afterConnectionClosed()");
//        // 关闭连接
//        User user = (User) session.getAttributes().get("user");
//        session.close();
//        // 从当前session列表中删除
//        webSocketSessions.remove(user);
//        // 群发通知
//        Message message = new Message("out", "帅气的" + user.getNickName() + "悄悄的离开了！", new ArrayList<User>(webSocketSessions.keySet()));
//        WebSocketMessage<String> msg = new TextMessage(JacksonUtils.toJson(message));
//        send2all(msg);
//    }
//
//    @Override
//    public boolean supportsPartialMessages() {
//        return false;
//    }
//
//    public void send2all(WebSocketMessage<String> msg) {
//        System.out.println("WebSocketHandler.send2all()");
//        for (WebSocketSession webSocketSession : webSocketSessions.values()) {
//            try {
//                if (webSocketSession.isOpen()) {
//                    webSocketSession.sendMessage(msg);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void send2one(User user, WebSocketMessage<String> msg) {
//        System.out.println("WebSocketHandler.send2one()");
//        try {
//            WebSocketSession webSocketSession = webSocketSessions.get(user);
//            if (webSocketSession.isOpen()) {
//                webSocketSession.sendMessage(msg);
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
