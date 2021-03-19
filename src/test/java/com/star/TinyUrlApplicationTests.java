package com.star;

import com.star.service.UrlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TinyUrlApplicationTests {

    @Autowired
    private UrlService urlService;

    @Test
    void testConvert() {
        String url = urlService.convertUrl("https://www.baidu.com");
        System.out.println(url);
    }

}
