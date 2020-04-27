package com.yy.get_swaggerpdf;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;

@SpringBootTest
class GetSwaggerpdfApplicationTests {
    @Test
    public void run(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("date","123");
        hashMap.put("size","456");
        ArrayList<HashMap> hashMaps = new ArrayList<>();
        boolean add = hashMaps.add(hashMap);
        System.out.println(hashMaps);
    }
}
