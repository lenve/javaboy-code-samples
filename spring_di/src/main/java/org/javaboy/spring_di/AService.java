package org.javaboy.spring_di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@Service
public class AService {
    BService bService;

    @Autowired
    public AService(BService bService) {
        this.bService = bService;
    }

    public AService() {
    }

    public BService getbService() {
        return bService;
    }
}
