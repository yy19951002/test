package com.huayun.test.web;

import com.huayun.test.model.JdbcConfig;
import com.huayun.test.model.JsonResult;
import com.huayun.test.service.JdbcConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/jdbcConfigs")
@Api(description = "数据库配置中心管理")
public class JdbcConfigController {
    private static Logger logger = Logger
            .getLogger(JdbcConfigController.class);

    @Autowired
    private JdbcConfigService jdbcConfigService;

    @ApiOperation(value = "获取所有配置")
    @RequestMapping(value="/jdbcConfigs",method= RequestMethod.GET)
    public ResponseEntity<JsonResult> getJdbcConfigs(HttpServletRequest request,
                                                     @RequestParam(value = "application", required = false) String application){
        logger.info("-----------------------开始获取所有配置信息-----------------");
        JsonResult r = new JsonResult();
        try {
            List<JdbcConfig> jdbcConfigs = jdbcConfigService.getJdbcConfigs(application);

            r.setResult(jdbcConfigs);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    @ApiOperation(value = "通过id获取所有配置")
    @RequestMapping(value="/jdbcConfig",method= RequestMethod.GET)
    public ResponseEntity<JsonResult> getJdbcConfig(HttpServletRequest request,
                                                    @RequestParam(value = "id", required = false) String id){
        logger.info("---------通过id-------"+ id +"-------开始获取配置信息");
        JsonResult r = new JsonResult();
        try {
            JdbcConfig jdbcConfig = jdbcConfigService.getJdbcConfigById(id);

            r.setResult(jdbcConfig);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    @ApiOperation(value = "新增配置")
    @RequestMapping(value="/add",method= RequestMethod.POST)
    public ResponseEntity<JsonResult> create(
            @RequestBody JdbcConfig jdbcConfig) {
        JsonResult r = new JsonResult();
        logger.info("-----------------------开始添加新配置-----------------------------");

        JdbcConfig jdbc = jdbcConfigService.getJdbcConfig(jdbcConfig.getKey(), jdbcConfig.getApplication(), jdbcConfig.getProfile(), jdbcConfig.getLabel());
        if(jdbc == null){
            try{
                JdbcConfig jd = jdbcConfigService.insert(jdbcConfig);

                r.setStatus("ok");
                r.setResult(jd);
            } catch (Exception e) {
                r.setResult(e.getClass().getName() + ":" + e.getMessage());
                r.setStatus("error");
                e.printStackTrace();
            }
        }else{
            r.setStatus("已配置");
            r.setResult(jdbc);
        }

        return ResponseEntity.ok(r);
    }

    @ApiOperation(value = "修改配置")
    @RequestMapping(value="/update",method= RequestMethod.POST)
    public ResponseEntity<JsonResult> update(
            @RequestBody JdbcConfig jdbcConfig) {
        logger.info("-----------------------开始更新配置：" + jdbcConfig.getId()
                + "-----------------------------");

        JsonResult r = new JsonResult();
        try {
            JdbcConfig us = jdbcConfigService.update(jdbcConfig);
            r.setResult(us);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    @ApiOperation(value = "删除配置")
    @RequestMapping(value="/delete",method= RequestMethod.POST)
    public ResponseEntity<JsonResult> delete(
            @RequestParam(value = "id", required = true) String id) {
        logger.info("-----------------------开始删除配置：" + id
                + "-----------------------------");

        JsonResult r = new JsonResult();
        try {
            String[] ids = id.split(",");
            jdbcConfigService.delete(ids);
            r.setResult("删除配置" + id);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }
}
