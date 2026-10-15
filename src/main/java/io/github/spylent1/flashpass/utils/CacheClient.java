package io.github.spylent1.flashpass.utils;


import cn.hutool.json.JSONUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class CacheClient {
    public CacheClient(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }
    private  final StringRedisTemplate stringRedisTemplate;

    public void set(String key, Object value, Long timeout, java.util.concurrent.TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value), timeout, java.util.concurrent.TimeUnit.MINUTES);
    }

    public void setWithLogicalExpire(String key, Object value, Long timeout, java.util.concurrent.TimeUnit unit) {
       RedisData redisData = new RedisData();
       redisData.setData(value);
       redisData.setExpireTime(LocalDateTime.now().plusMinutes(unit.toSeconds(timeout)));
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(redisData));
    }


}
