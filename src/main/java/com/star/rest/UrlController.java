package com.star.rest;

import com.alibaba.fastjson.JSONObject;
import com.star.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zzStar
 * @Date: 03-19-2021 12:01
 */
@RestController
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/convert")
    @ResponseBody
    public String convertUrl(@RequestParam String url) {
        String shortUrl = urlService.convertUrl(url);
        JSONObject json = new JSONObject();
        json.put("convertUrl", shortUrl);
        return json.toJSONString();
    }

    @PostMapping("/revert")
    public String revertUrl(@RequestParam String url) {
        String revertUrl = urlService.revertUrl(url);
        JSONObject json = new JSONObject();
        json.put("revertUrl", revertUrl);
        return json.toJSONString();
    }
}
