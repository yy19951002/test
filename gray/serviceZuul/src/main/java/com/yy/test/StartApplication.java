package com.huayun.test;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * 该注解指定项目为springboot，由此类当作程序入口
 * 自动装配 web 依赖的环境
 * @author Administrator
 *
 */
@SpringCloudApplication  //用于快捷配置启动类
@EnableDiscoveryClient
@EnableZuulProxy
public class StartApplication {
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(StartApplication.class);
	public static void main(String[] args) throws UnknownHostException {
//		// TODO Auto-generated method stub
//		SpringApplication.run(StartApplication.class, args);
		Environment env = new SpringApplicationBuilder(StartApplication.class).web(true).run(args).getEnvironment();
		log.info(
				"\n----------------------------------------------------------\n\t"
						+ "Application '{}' is running! Access URLs:\n\t" + "Local: \t\thttp://127.0.0.1:{}\n\t"
						+ "External: \thttp://{}:{}\n----------------------------------------------------------",
				env.getProperty("spring.application.name"), env.getProperty("server.port"),
				InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"));

		String configServerStatus = env.getProperty("configserver.status");
		log.info(
				"\n----------------------------------------------------------\n\t"
						+ "Config Server: \t{}\n----------------------------------------------------------",
				configServerStatus == null ? "Not found or not setup for this application" : configServerStatus);
	}

}
