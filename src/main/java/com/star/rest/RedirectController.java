package com.star.rest;

import com.star.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 重定向
 *
 * @Author: zzStar
 * @Date: 03-19-2021 16:48
 */
@Controller
public class RedirectController {

    @Autowired
    private UrlService urlService;

    @GetMapping("/*")
    public RedirectView redirectView(HttpServletRequest request, HttpServletResponse response) {
        String shortUrl = request.getServletPath().substring(1);
        String url = urlService.revertUrl(shortUrl);
        return new RedirectView(url);
    }
}
