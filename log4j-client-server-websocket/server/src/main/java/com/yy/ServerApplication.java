package com.yy;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.InetAddress;

@SpringBootApplication
public class ServerApplication {

	private static final Logger logger = Logger.getLogger(ServerApplication.class);

	public static void main(String[] args) throws IOException{
		SpringApplication.run(ServerApplication.class, args);

		while (true){
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 获取本服务器id
			String hostAddress = InetAddress.getLocalHost().getHostAddress();
// MDC是key-value结构，有兴趣的可以去了解下，在log4j的配置中设置 %X{ip},在日志中输出
			MDC.put("ip",hostAddress);
			logger.info("testeeee");
			logger.error("1234567");
		}
//		socket方式
//		ServerSocket socket = new ServerSocket(5000);
//
//		while (true){
//			logger.info("dfsdfsfsf");
//			Socket client = socket.accept();
//			Thread t = new Thread(new LogRunner(client));
//			t.start();
//		}
	}

}
