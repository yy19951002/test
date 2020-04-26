package com.yy.cas.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by yanyong on 2020/4/26.
 */
@Controller
public class CASController {

    @RequestMapping("index")
    public String index(ModelMap map) {
        map.addAttribute("name", "clien B");
        return "index";
    }

    @RequestMapping("hello")
    public String hello() {
        return "hello";
    }

    @Value("${casClientLogoutUrl}")
    private String clientLogoutUrl;//http://cas.server.com:8443/cas/logout?service=http://cas.client1.com:9002/logout/success

    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();//销毁session
        //使用cas退出成功后,跳转到http://cas.client1.com:9002/logout/success
        return "redirect:" + clientLogoutUrl;
    }

    @RequestMapping("logout/success")
    public String logoutsuccess(HttpSession session) {
        return "logoutsuccess";
    }
}