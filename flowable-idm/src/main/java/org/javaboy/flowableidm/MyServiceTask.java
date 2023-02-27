package org.javaboy.flowableidm;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@Component
public class MyServiceTask implements JavaDelegate {
//    Expression username;
    @Override
    public void execute(DelegateExecution execution) {
//        System.out.println("username.getExpressionText() = " + username.getExpressionText());
//        System.out.println("username.getValue(execution) = " + username.getValue(execution));
//        System.out.println("========MyServiceTask==========");
    }
}
