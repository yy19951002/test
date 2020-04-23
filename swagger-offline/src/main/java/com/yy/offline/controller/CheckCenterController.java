package com.yy.offline.controller;

import com.yy.offline.pojo.ApiReturnObject;
import com.yy.offline.pojo.JsonResult;
import com.yy.offline.pojo.UserInfo;
import com.yy.offline.util.ApiReturnUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Api("客服接口")
@Controller
@RequestMapping("/checkcenter")
public class CheckCenterController {

    // 创建线程安全的Map
    static Map<Integer, UserInfo> users = Collections.synchronizedMap(new HashMap<Integer, UserInfo>());

    @ApiOperation(value="获取客服", notes="根据cid获取客服")
    @ApiImplicitParam(name = "cid", value = "客户id", required = true, dataType = "String")
    @ResponseBody
    @GetMapping("/getCenter")
    public ApiReturnObject getCenter(String cid) {
        Map<String,String> map=new LinkedHashMap<String,String>();
        map.put("cid",cid);
        map.put("name","客服");
        return ApiReturnUtil.success(map);
    }

    /**
     * 查询用户列表
     * @return
     */
    @ApiOperation(value="获取用户列表", notes="获取用户列表")
    @RequestMapping(value = "users", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getUserList (){
        JsonResult r = new JsonResult();
        try {
            Map<String,String> map=new LinkedHashMap<String,String>();
            map.put("aa", "小红");
            map.put("bb", "小明");
            r.setResult(map);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

}
