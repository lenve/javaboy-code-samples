package org.javaboy.permiss_demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.AntPathMatcher;

@SpringBootTest
class PermissDemoApplicationTests {

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Test
    @PreAuthorize("hasPermission('system:user:add')")
    void contextLoads() {
        System.out.println("antPathMatcher.match(\"user:*\",\"user:aaa\") = " + antPathMatcher.match("user:*", "user:aaa"));
        System.out.println("antPathMatcher.match(\"user:list:*\", \"user:list:add\") = " + antPathMatcher.match("user:list:*", "user:list:add"));
        System.out.println("antPathMatcher.match(\"*:*:*\",\"aa:bb:cc\") = " + antPathMatcher.match("*:*:*", "aa:bb:cc"));
        System.out.println("antPathMatcher.match(\"a:*:*\",\"c:bbb:ccc\") = " + antPathMatcher.match("a:*:*", "c:bbb:ccc"));
        System.out.println("antPathMatcher.match(\"user:*:add\",\"user:aaa:add\") = " + antPathMatcher.match("user:*:add", "user:aaa:add"));
        System.out.println("antPathMatcher.match(\"user:*:add\",\"user1:aa:add\") = " + antPathMatcher.match("user:*:add", "user1:aa:add"));
    }

}