package org.javaboy.demo;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

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
public class HelloController {
    @GetMapping("/pdf/{file}")
    public void pdf(@PathVariable String file, HttpServletResponse resp) throws IOException {
        ApplicationHome h = new ApplicationHome(getClass());
        File jarF = h.getSource();
        File parentFile = jarF.getParentFile();
        System.out.println(parentFile.toString());
        File f = new File(parentFile.toString() + File.separator + "pdf", file);
        FileInputStream fis = new FileInputStream(f);
        int len = 0;
        byte[] buf = new byte[1024];
        ServletOutputStream out = resp.getOutputStream();
        while ((len = fis.read(buf)) != -1) {
            out.write(buf, 0, len);
        }
        fis.close();
    }
}
