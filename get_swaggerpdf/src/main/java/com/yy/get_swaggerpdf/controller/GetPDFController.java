package com.yy.get_swaggerpdf.controller;

import com.yy.get_swaggerpdf.service.GetPDF;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GetPDFController {

    @Autowired
    private GetPDF getPDF;

    @ApiOperation("导出接口文档")
    @RequestMapping(value = "/getAsciiDocs", method = RequestMethod.GET)
    public String getAsciiDocs(@RequestParam(required = true, name = "需要导出项目的swagger地址")String url) {
        try {
            String asciiDocs = getPDF.getAsciiDocs(url);
            String pdf = getPDF.getPDF();
        } catch (Exception e) {
            e.printStackTrace();
            return "导出失败";
        }
        return "导出成功";
    }
}
