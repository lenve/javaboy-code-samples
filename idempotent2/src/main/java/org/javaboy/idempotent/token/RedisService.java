package org.javaboy.idempotent.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@Component
public class RedisService {
    @Autowired
    RedisTemplate redisTemplate;

    public boolean setEx(String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations ops = redisTemplate.opsForValue();
            ops.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    public boolean remove(String key) {
        if (exists(key)) {
            return redisTemplate.delete(key);
        }
        return false;
    }
}
