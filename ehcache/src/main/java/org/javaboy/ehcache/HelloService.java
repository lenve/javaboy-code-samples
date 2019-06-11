package org.javaboy.ehcache;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Author 江南一点雨
 * @Site www.javaboy.org 2019-06-03 11:01
 */
@Service
@CacheConfig(cacheNames = "user")
public class HelloService {
    @Cacheable
    public String hello(String name) {
        System.out.println(name);
        return "hello " + name;
    }
}
