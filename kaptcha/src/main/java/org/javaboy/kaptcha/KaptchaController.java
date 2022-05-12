package org.javaboy.kaptcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.font.ImageGraphicAttribute;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@RestController
public class KaptchaController {

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @GetMapping("/img")
    public void getKaptcha(HttpServletResponse resp) throws IOException {
        String text = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(text);
        ImageIO.write(image, "jpg", resp.getOutputStream());
    }

    @GetMapping("/test01")
    public void test01(HttpSession session) {
        Object kaptchaCode = session.getAttribute("kaptchaCode");
        System.out.println("kaptchaCode = " + kaptchaCode);
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            System.out.println(attributeNames.nextElement());
        }

    }
}
