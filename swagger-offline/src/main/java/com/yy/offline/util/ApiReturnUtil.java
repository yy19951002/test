package com.yy.offline.util;

import com.yy.offline.pojo.ApiReturnObject;

import java.util.Map;

/**
 * Created by yanyong on 2020/4/23.
 */
public class ApiReturnUtil {
    public static ApiReturnObject success(Map<String, String> map) {
        return (ApiReturnObject) map;
    }
}
