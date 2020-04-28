package com.example.demo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Created by yanyong on 2020/4/28.
 */

/**
 * 修改打包形式war
 * 移除内置tomcat影响
 * 类似于web.xml的配置方式启动spring上下文，修改启动类
 * mvn clean package
 * war包--tomcat--webapps
 */
public class SpringBootStartApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(DemoApplication.class);
    }
}
