package org.javaboy.idempotent.token;

import org.javaboy.idempotent.exception.IdempotentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.events.Event;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨 idempotent
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@Component
public class TokenService {
    @Autowired
    RedisService redisService;

    public String createToken() {
        String uuid = UUID.randomUUID().toString();
        redisService.setEx(uuid, uuid, 10000L);
        return uuid;
    }

    public boolean checkToken(HttpServletRequest req) throws IdempotentException {
        String token = req.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            token = req.getParameter("token");
            if (StringUtils.isEmpty(token)) {
                throw new IdempotentException("token 不存在");
            }
        }
        if (!redisService.exists(token)) {
            throw new IdempotentException("重复的操作");
        }
        boolean remove = redisService.remove(token);
        if (!remove) {
            throw new IdempotentException("重复的操作");
        }
        return true;
    }
}
