package org.javaboy.demo.test;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
public class PasswordTest {
    @Test
    public void test01() {
        //对明文 123 进行加密
        Md5Hash md5Hash = new Md5Hash("123", "javaboy", 1024);
        System.out.println("md5Hash = " + md5Hash);
        Sha512Hash sha512Hash = new Sha512Hash("123");
        System.out.println("sha512Hash = " + sha512Hash);
        SimpleHash md5 = new SimpleHash("md5", "123", "lisi", 1024);
        System.out.println("md5 = " + md5);
        SimpleHash simpleHash = new SimpleHash("sha-512", "123");
        System.out.println("simpleHash = " + simpleHash);
    }
}
