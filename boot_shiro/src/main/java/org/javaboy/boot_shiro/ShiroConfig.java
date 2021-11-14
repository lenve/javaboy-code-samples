package org.javaboy.boot_shiro;

import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@Configuration
public class ShiroConfig {

    @Autowired
    MyRealm02 myRealm02;

    @Bean
    DefaultWebSecurityManager defaultWebSecurityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myRealm02);
        return defaultWebSecurityManager;
    }

    @Bean
    ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        definition.addPathDefinition("/login", "anon");
        definition.addPathDefinition("/doLogin", "anon");
        definition.addPathDefinition("/logout", "logout");
        definition.addPathDefinition("/**", "authc");
        return definition;
    }
}
