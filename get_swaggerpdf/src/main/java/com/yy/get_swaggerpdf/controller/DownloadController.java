package com.yy.get_swaggerpdf.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
public class DownloadController {
    @ApiOperation(value = "下载PDF文件")
    @RequestMapping(value = "/downloadPdf",method = RequestMethod.GET)
    public void downloadPdf(HttpServletResponse response){
        String type = "pdf";
        download(response, type);
    }
    @ApiOperation(value = "下载HTML文件")
    @RequestMapping(value = "/downloadHtml",method = RequestMethod.GET)
    public void downloadHtml(HttpServletResponse response){
        String type = "html";
        download(response, type);
    }
    public void download(HttpServletResponse response, String type){
        String path = System.getProperty("user.dir");
        //File file = new File("/root/service/src/pdf/paths.pdf");//linux版
        File file = new File(path + "\\src\\" + type + "\\paths." + type);//win版
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        try {
            String fileName = "swagger." + type;
            response.setHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("utf-8"),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
