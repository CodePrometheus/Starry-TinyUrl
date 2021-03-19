package com.star.rest;

import com.star.config.ServerInitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: zzStar
 * @Date: 03-19-2021 12:01
 */
@Controller
public class ViewController {

    @Autowired
    private ServerInitConfig serverInitConfig;

    @Value("${common.domain}")
    private String domain;

    @GetMapping(value = "/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("domain", StringUtils.isEmpty(domain) ? serverInitConfig.getUrl() : domain);
        return mv;
    }
}
