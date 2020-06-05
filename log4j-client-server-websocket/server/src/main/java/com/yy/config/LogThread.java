package com.yy.config;

import javax.websocket.Session;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;

/**
 * Created by yanyong on 2020/6/5.
 */
public class LogThread extends Thread {
    private BufferedReader reader;
    private Session session;

    public LogThread(PipedReader pipedReader, Session session) {
        this.reader = new BufferedReader(pipedReader);
        this.session = session;

    }

    @Override
    public void run() {
        String line;
        try {
            while((line = reader.readLine()) != null) {
                // 将实时日志通过WebSocket发送给客户端
                session.getBasicRemote().sendText(line + "<br>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
