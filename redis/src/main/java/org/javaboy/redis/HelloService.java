package org.javaboy.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

/**
 * @Author 江南一点雨
 * @Site www.javaboy.org 2019-06-01 16:15
 */
@Service
public class HelloService {
    @Autowired
    RedisTemplate redisTemplate;

    public void hello() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("k1", "v1");
        Object k1 = ops.get("k1");
        System.out.println(k1);
    }

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public void hello2() {
        ValueOperations ops = stringRedisTemplate.opsForValue();
        ops.set("k2", "v2");
        Object k1 = ops.get("k2");
        System.out.println(k1);
    }
}
