package com.foan.crm.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class RedisRepository implements QuickResponseRepository {

    @Autowired
    private StringRedisTemplate SredisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    public void saveByKey(String key, String content, int expire) {
        SredisTemplate.opsForValue().set(key, content, expire, TimeUnit.SECONDS);
    }

    public void saveByKey(String key, String content) {
        SredisTemplate.opsForValue().set(key, content);
    }

    public boolean isKeyExist(String key){
        return SredisTemplate.hasKey(key);
    }

    public String findKey(String key) throws Exception {
        if (SredisTemplate.hasKey(key)) {
            return SredisTemplate.opsForValue().get(key);
        }
        throw new Exception("Not found key in redis");
    }


    public void saveObjectByKey(String key, Object content){
        redisTemplate.opsForValue().set(key, content);
    }

    public Object findObjectByKey(String key) throws Exception{
        if (redisTemplate.hasKey(key)) {
            return redisTemplate.opsForValue().get(key);
        }
        throw new Exception("Not found key in redis");
    }


    public void deleteByKey(String key) {
        SredisTemplate.delete(key);
    }

}

