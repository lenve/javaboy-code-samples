package org.javaboy.flash_map;

import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@Controller
public class OrderController {
    @PostMapping("/order")
    public String order(HttpServletRequest req) {

        FlashMap flashMap = (FlashMap) req.getAttribute(DispatcherServlet.OUTPUT_FLASH_MAP_ATTRIBUTE);
        flashMap.put("site", "www.javaboy.org");
        flashMap.addTargetRequestParam("aa", "bb");
//        attr.addFlashAttribute("site", "www.javaboy.org");
//        attr.addAttribute("name", "微信公众号：江南一点雨");
//        Locale locale = LocaleContextHolder.getLocale();
        return "redirect:/orderlist?aa=bb";
    }

    @GetMapping("/orderlist")
    @ResponseBody
    public String orderList(Model model) {
        return (String) model.getAttribute("site");
    }
}
