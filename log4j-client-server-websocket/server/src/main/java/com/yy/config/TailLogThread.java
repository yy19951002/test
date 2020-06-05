package com.yy.config;

/**
 * Created by yanyong on 2020/6/4.
 */

import com.yy.domain.LogRunner;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Queue;

public class TailLogThread extends Thread {

    public volatile boolean exit = false;
    private Queue<String> queue;
    private Session session;

    public TailLogThread(Queue<String> queue, Session session) {
        this.queue = queue;
        this.session = session;
    }

    @Override
    public void run() {
        try {
            while(!exit){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if( queue.size() > 0){
                    session.getBasicRemote().sendText(queue.poll() + "<br>");
                }
                queue = LogRunner.queue;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

