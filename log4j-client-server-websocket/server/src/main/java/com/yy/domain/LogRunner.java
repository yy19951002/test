package com.yy.domain;

/**
 * Created by yanyong on 2020/6/3.
 */

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class LogRunner implements Runnable{
    private static final Logger logger = Logger.getLogger(LogRunner.class);

    private ObjectInputStream ois;
    public static Queue<String> queue = new LinkedList<String>();


    public LogRunner(Socket client) {
        try {
            this.ois = new ObjectInputStream(client.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        try {
            while (true) {
                Object obj= ois.readObject();
                System.out.println(obj.toString());
                LoggingEvent event = (LoggingEvent) ois.readObject();
                System.out.println(event.getLoggerName()+":"+event.getMessage());
                queue.offer(event.getMessage().toString());
                System.out.println("queue" + queue);
                System.out.println("queuesize" + queue.size());
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
