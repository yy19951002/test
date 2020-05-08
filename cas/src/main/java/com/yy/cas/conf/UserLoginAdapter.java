package com.yy.cas.conf;

/**
 * Created by yanyong on 2020/5/7.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * description:
 * author:cb
 * datetime:2019年7月28日  下午6:22:29
 */

@SpringBootApplication
@EnableCaching
public class UserLoginAdapter implements WebMvcConfigurer{

    @Autowired
    private UserLoginInterceptor userLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //添加对用户是否登录的拦截器，并添加过滤项、排除项
        registry.addInterceptor(userLoginInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/css/**","/js/**","/images/**")//排除样式、脚本、图片等资源文件
                .excludePathPatterns("/wechatplatformuser/loginRBAC.html")//排除登录页面
                .excludePathPatterns("/wechatplatformuser/defaultKaptcha")//排除验证码
                .excludePathPatterns("/wechatplatformuser/loginRBAC");//排除用户点击登录按钮

    }

}