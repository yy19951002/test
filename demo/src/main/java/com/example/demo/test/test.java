package com.example.demo.test;

import com.example.demo.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yanyong on 2020/4/26.
 */
@RestController
public class test {
    @RequestMapping("hello")
    public String talk(){
        System.out.println(System.getProperty("user.dir"));
        return "hellow word";
    }
    @Autowired
    ExampleService exampleService;
    @RequestMapping("wrap")
    public String wrap(String word){
        System.out.println(System.getProperty("user.dir"));
        return exampleService.wrap(word);
    }
    @RequestMapping("/test")
    public String test1(HttpServletRequest request){
        request.setAttribute("hello1", "hello");
        request.setAttribute("hello2","world");
        return "test";
    }
}
