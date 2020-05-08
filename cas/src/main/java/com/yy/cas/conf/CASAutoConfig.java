package com.yy.cas.conf;
//
//import org.jasig.cas.client.authentication.AuthenticationFilter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by yanyong on 2020/4/26.
// */
//@Configuration
//public class CASAutoConfig {
//    @Value("${cas.server-url-prefix}")
//    private String serverUrlPrefix;
//    @Value("${cas.server-login-url}")
//    private String serverLoginUrl;
//    @Value("${cas.client-host-url}")
//    private String clientHostUrl;
//
//    /**
//     * 授权过滤器
//     * @return
//     */
//    @Bean
//    public FilterRegistrationBean filterAuthenticationRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new AuthenticationFilter());
//        // 设定匹配的路径
//        registration.addUrlPatterns("/*");
//        Map<String,String> initParameters = new HashMap<String, String>();
//        initParameters.put("casServerLoginUrl", serverUrlPrefix);
//        initParameters.put("serverName", clientHostUrl);
//        //忽略的url，"|"分隔多个url
//        initParameters.put("ignorePattern", "/logout/success|/index");
//        registration.setInitParameters(initParameters);
//        // 设定加载的顺序
//        registration.setOrder(1);
//        return registration;
//    }
//}

import com.yy.cas.filter.FetchCasTicketFilter;
import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CASAutoConfig {

    @Value("${cas.server-login-url}")
    private String casServerLoginUrl;

    @Value("${cas.client-host-url}")
    private String casClientHostUrl;

    @Value("${cas-ignore-pattern}")
    private String casIgnorePattern;


    @Bean
    public FilterRegistrationBean authFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new FetchCasTicketFilter());
        registration.addUrlPatterns("/*"); //
        registration.addInitParameter("paramName", "paramValue"); //
        registration.setName("fetchCasTicketFilter");
        registration.setOrder(1);
        return registration;
    }
    /**
     * description:授权过滤器
          * ignoreUrlPatternType 使用CAS现成的正则表达式过滤策略
     */
    @Bean
    public FilterRegistrationBean filterAuthenticationRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AuthenticationFilter());
        registration.addUrlPatterns("/*");

        Map<String,String> initParameters = new HashMap<String, String>();
        initParameters.put("casServerLoginUrl", casServerLoginUrl);
        initParameters.put("serverName", casClientHostUrl);
        //配置文件中设置要过滤拦截的路径
        initParameters.put("ignorePattern", casIgnorePattern);
        initParameters.put("ignoreUrlPatternType", "org.jasig.cas.client.authentication.RegexUrlPatternMatcherStrategy");
        registration.setInitParameters(initParameters);
        registration.setOrder(2);
        return registration;
    }
}