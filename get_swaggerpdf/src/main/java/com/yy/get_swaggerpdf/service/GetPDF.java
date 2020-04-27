package com.yy.get_swaggerpdf.service;


import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

@Service
public class GetPDF {
    @Value("${path}")
    String path;
    public String getPDF() {
//        String filePath = path;
//        System.out.println("**"+path);
//        filePath = "cd " + filePath + " && mvn generate-resources";
//        System.out.println("****"+filePath);
//        String [] cmd={"/bin/sh","-c","cd /root/service && mvn generate-resources"};//linux版
//        String[] cmd = {"cmd", "/C", filePath};
        String path = System.getProperty("user.dir");
        String [] cmd={"cmd","/C","cd " + path + " && mvn generate-resources"};//win版
        try {
            Process proc = Runtime.getRuntime().exec(cmd);
            //取得命令结果的输出流
            InputStream fis = proc.getErrorStream();
            //用一个读输出流类去读
            InputStreamReader isr = new InputStreamReader(fis);
            //用缓冲器读行
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            //直到读完为止
            while ((line = br.readLine()) != null) {
                line = new String(line.getBytes("gbk"), "utf-8");
                System.out.println("****" + line);
            }
            proc.destroy();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "ok";
    }

    public String getAsciiDocs(String url) {
        try {
            Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                    .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
                    .withOutputLanguage(Language.ZH)
                    .withPathsGroupedBy(GroupBy.TAGS)
                    .withGeneratedExamples()
                    .withoutInlineSchema()
                    .build();
            Swagger2MarkupConverter.from(new URI(url))
                    .withConfig(config)
                    .build()
                    .toFolder(Paths.get("./src/gen"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "ok";
    }
}
