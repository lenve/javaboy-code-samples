package org.javaboy.spel_demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

@SpringBootTest
class SpelDemoApplicationTests {

    @Autowired
    BeanFactory beanFactory;

    @Test
    void contextLoads() {
        String expression = "@us.sayHello('zhangsan')";
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(expression);
        StandardEvaluationContext ctx = new StandardEvaluationContext();
        ctx.setBeanResolver(new BeanFactoryResolver(beanFactory));
        String value = exp.getValue(ctx, String.class);
        System.out.println("value = " + value);
    }

}
