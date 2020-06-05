package com.yy.config;

/**
 * Created by yanyong on 2020/6/4.
 */

import com.yy.domain.LogRunner;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Queue;

@ServerEndpoint("/logs")
@Component
public class LogWebSocketHandle {
    private Queue<String> queue;


    /**
     * 新的WebSocket请求开启
     */
    @OnOpen
    public void onOpen(Session session) {
        try {
            queue = LogRunner.queue;
            System.out.println(queue);
            // 一定要启动新的线程，防止InputStream阻塞处理WebSocket的线程
            TailLogThread thread = new TailLogThread(queue, session);
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message){
        System.out.println("message" + message);
    }
    /**
     * WebSocket请求关闭
     */
    @OnClose
    public void onClose(Session session) {
        try {
            TailLogThread tailLogThread = new TailLogThread(queue, session);
            tailLogThread.exit = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Throwable thr) {
        thr.printStackTrace();
    }
}

