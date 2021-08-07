package org.javaboy.lombok;

import lombok.Cleanup;

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
public class FileUtils {
    public void copy(String source, String dest) throws IOException {
        File sourceFile = new File(source);
        File destFile = new File(dest);
        @Cleanup InputStream in = new FileInputStream(sourceFile);
        @Cleanup OutputStream out = new FileOutputStream(destFile);
        byte[] b = new byte[1024];
        while (true) {
            int r = in.read(b);
            if (r == -1) break;
            out.write(b, 0, r);
        }
    }
}
