package com.star.service;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.star.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author: zzStar
 * @Date: 03-19-2021 10:51
 */
@Slf4j
@Service
public class UrlService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private SnowFlake snowFlake;

    @Autowired
    private BloomFilter bloomFilter;

    @Autowired
    private AsyncJob asyncJob;

    /**
     * 根据原地址生成短地址
     *
     * @param url
     * @return
     */
    public String convertUrl(String url) {
        Preconditions.checkArgument(Validator.checkUrl(url), "[url]格式不合法！url={}", url);
        log.info("地址开始转换成短地址 ---> [url]={}", url);
        String shortCut;

        // 这里先判断布隆过滤器是否命中，有则直接返回对应的value
        if (bloomFilter.includeByBloomFilter(url)) {
            if (!Strings.isNullOrEmpty(shortCut = redisTemplate.opsForValue().get(url))) {
                log.info("布隆过滤器命中----->[shortCut]={}", shortCut);
                return shortCut;
            }
        }

        // 否则开始生成短地址 并存入缓存
        long nextId = snowFlake.nextId();
        // 转换为62进制
        shortCut = NumericConvertUtils.convertTo(nextId, 62);
        log.info("进制转换成功----->[shortCut]={}", shortCut);
        // 将短网址和短域名异步添加到布隆过滤器中，提升响应速度
        asyncJob.add2RedisAndBloomFilter(shortCut, url);
        return shortCut;
    }


    /**
     * 将短地址还原为原地址
     *
     * @param shortUrl
     * @return
     */
    public String revertUrl(String shortUrl) {
        log.info("还原短地址开始----->[shortUrl]={}", shortUrl);
        String shortcut = shortUrl.substring(shortUrl.lastIndexOf("/") + 1);
        String url = redisTemplate.opsForValue().get(shortcut);
        log.info("还原短地址成功----->[真实Url]={}", url);
        return url;
    }


}
