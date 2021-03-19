package com.star.util;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 布隆过滤器
 *
 * @Author: zzStar
 * @Date: 03-19-2021 11:05
 */
@Component
@RequiredArgsConstructor
public class BloomFilter {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private BloomFilterConfig bloomFilterConfig;

    /**
     * 根据给定的布隆过滤器添加值
     */
    public void addByBloomFilter(String value) {
        Preconditions.checkArgument(value != null, "value不能为空");
        long[] offset = bloomFilterConfig.murmurHashOffset(value);
        // 把对应位修改为0或1
        for (long i : offset) {
            redisTemplate.opsForValue().setBit(bloomFilterConfig.getBfKey(), i, true);
        }
    }

    /**
     * 根据给定的布隆过滤器判断值是否存在
     */
    public boolean includeByBloomFilter(String value) {
        Preconditions.checkArgument(value != null, "value不能为空");
        long[] offset = bloomFilterConfig.murmurHashOffset(value);
        for (long i : offset) {
            Boolean include;
            if ((include = redisTemplate.opsForValue().getBit(bloomFilterConfig.getBfKey(), i)) == null || !include) {
                return false;
            }
        }
        return true;
    }

}
