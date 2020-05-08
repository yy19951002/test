package com.yy.cas.controller;

import com.yy.cas.filter.FetchCasTicketFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

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

//    /*
//    oauth-server
//     */
//    @RequestMapping("hello")
//    public String hello() {
//        return "c";
//    }
    //定义一个login的post方法，然后返回cookie信息
    @RequestMapping(value = "/ticket2Token",method = RequestMethod.GET)
    @ResponseBody
    public void Login(HttpServletResponse response) throws Exception{
        Map<String, String> map = FetchCasTicketFilter.map;
        String ticket = map.get("ticket");
        response.sendRedirect("http://localhost:9090/login.html" + "?token=" + ticket);
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