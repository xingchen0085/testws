package cn.myxinge.demo.ws;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @Auth:chenxinghua
 * @Date:2018\2\24 0024 15:00
 * @Description: WebSoket服务端处理类
 */
@ServerEndpoint("/ws/")
public class WebSoketServerHandler {

    private static Session session;

    public static Session getSession() {
        return session;
    }

    @OnOpen
    public void onOpen(Session session) throws IOException {
        this.session = session;
        System.out.println("已连接");
    }

    @OnClose
    public void onClose() throws IOException {
        System.out.println("已关闭");
    }

    @OnMessage
    public void onMessage(String message) throws IOException {

        System.out.println("消息:" + message);
        sendMessageTo(message, "");
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessageTo(String message, String To) throws IOException {
        session.getBasicRemote().sendText(message);
        //session.getAsyncRemote().sendText(message);
    }
}
