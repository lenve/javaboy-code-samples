package org.javaboy.druid_monitor.filter;

import com.alibaba.druid.util.Utils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@WebFilter(urlPatterns = "/druid/js/common.js")
public class RemoveAdFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.resetBuffer();
        String text = Utils.readFromResource("support/http/resources/js/common.js");
        text = text.replace("this.buildFooter();", "");
        servletResponse.getWriter().write(text);
    }
}