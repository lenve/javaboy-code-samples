package org.javaboy.exception;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 江南一点雨
 * @Site www.javaboy.org 2019-05-30 16:12
 */
@Component
public class MyErrorAttribute extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("error", map.get("error"));
        map2.put("mydata", "出错啦！");
        return map2;
    }
}
