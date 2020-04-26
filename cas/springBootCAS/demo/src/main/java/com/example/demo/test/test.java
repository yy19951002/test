package com.example.demo.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yanyong on 2020/4/26.
 */
@RestController
public class test {
    @RequestMapping("hello")
    public String talk(){
        return "hellow word";
    }
}
