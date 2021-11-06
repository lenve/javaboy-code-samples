package org.javaboy.shirodemo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.javaboy.shirodemo.model.RespBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨 ssm  shiro ss
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@Controller
public class LoginController {

    /**
     * 这个是 Shiro 中提供的表单登录，这个接口将包含两方面的功能：
     *
     * 1. 提供登录页面
     * 2. 处理登录请求
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest req, Model model) {
        //获取登录失败的异常信息
        String shiroLoginFailure = (String) req.getAttribute("shiroLoginFailure");
        if (UnknownAccountException.class.getName().equals(shiroLoginFailure)|| IncorrectCredentialsException.class.getName().equals(shiroLoginFailure)) {
            //用户名或者密码输入错误
            model.addAttribute("error", "用户名或者密码输入错误");
        }
        return "01";
    }

    @PostMapping(value = "/doLogin", produces = "text/html;charset=utf-8")
    public String doLogin(String username, String password, HttpServletRequest req) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //开启 RememberMe 功能
        //正常可以根据前端传入的参数来设置 RememberMe，我这里就直接写死
        token.setRememberMe(true);
        try {
            //执行登录操作
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            //获取登录成功的用户信息
            List list = subject.getPrincipals().asList();
            for (Object o : list) {
                System.out.println(o.getClass() + "=====>" + o);
            }
        } catch (AuthenticationException e) {
//            return "登录失败：" + e.getMessage();
            req.setAttribute("error", e.getMessage());
            return "forward:/01";
        }
        //登录成功后，重定向到 index 首页
        return "redirect:/index";
    }

    @RequestMapping("/01")
    public String m01() {
        return "01";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/logout111")
    @ResponseBody
    public RespBean logout() {
        try {
            SecurityUtils.getSubject().logout();
            return RespBean.ok("注销成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RespBean.error("注销失败");
    }
}
