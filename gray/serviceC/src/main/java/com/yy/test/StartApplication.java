package com.huayun.test;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * 该注解指定项目为springboot，由此类当作程序入口
 * 自动装配 web 依赖的环境
 * @author Administrator
 *
 */
@SpringBootApplication  //用于快捷配置启动类
@EnableDiscoveryClient
@EnableFeignClients
public class StartApplication extends WebMvcConfigurerAdapter {
	private static Logger logger=Logger.getLogger(StartApplication.class);
	@Autowired
	private RestTemplateBuilder builder;

	@Bean
	public RestTemplate restTemplate() {
		return builder.build();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(StartApplication.class, args);
	}

}
