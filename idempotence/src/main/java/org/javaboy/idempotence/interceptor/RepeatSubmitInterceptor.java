package org.javaboy.idempotence.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaboy.idempotence.annotation.RepeatSubmit;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 防止重复提交拦截器
 *
 * @author tienchin
 */
public abstract class RepeatSubmitInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);
            if (annotation != null) {
                if (this.isRepeatSubmit(request, annotation)) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("status", 500);
                    map.put("msg", annotation.message());
                    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().write(new ObjectMapper().writeValueAsString(map));
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    /**
     * 验证是否重复提交由子类实现具体的防重复提交的规则
     *
     * @param request
     * @return
     * @throws Exception
     */
    public abstract boolean isRepeatSubmit(HttpServletRequest request, RepeatSubmit annotation);
}
