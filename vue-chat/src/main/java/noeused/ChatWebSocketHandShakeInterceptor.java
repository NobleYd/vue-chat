//package noeused;
//
//import java.util.Map;
//
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.http.server.ServletServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.server.HandshakeInterceptor;
//
//@Component
//public class ChatWebSocketHandShakeInterceptor implements HandshakeInterceptor {
//
//    @Override
//    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
//        System.out.println("Interceptor.beforeHandshake()");
//        ServletServerHttpRequest req = (ServletServerHttpRequest) request;
//        Object user = req.getServletRequest().getSession().getAttribute("user");
//        if (user == null) {
//            System.out.println("未登陆拒绝握手！");
//            return false;
//        } else {
//            System.out.println(user.toString() + "开始握手！");
//            // 放入websocket session
//            attributes.put("user", user);
//            return true;
//        }
//    }
//
//    @Override
//    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
//        ServletServerHttpRequest req = (ServletServerHttpRequest) request;
//        Object user = req.getServletRequest().getSession().getAttribute("user");
//        System.out.println(user.toString() + "握手结束！");
//    }
//
//}
