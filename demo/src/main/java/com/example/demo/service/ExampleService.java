package com.example.demo.service;

/**
 * Created by yanyong on 2020/4/28.
 */
public class ExampleService {
    private String prefix;
    private String suffix;

    public ExampleService(String prefix, String suffix){
        this.prefix = prefix;
        this.suffix = suffix;
    }
    public String wrap(String word){
        return prefix + suffix;
    }
}
