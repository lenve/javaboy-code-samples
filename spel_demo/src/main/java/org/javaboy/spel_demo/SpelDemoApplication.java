package org.javaboy.spel_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.*;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

@SpringBootApplication
public class SpelDemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpelDemoApplication.class, args);
//        m1();
//        m2();
//        m3();
//        m4();

    }

    private static void m4() {
        String expression = "sayHello(99)";
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(expression);
        StandardEvaluationContext ctx = new StandardEvaluationContext();
        User user = new User();
        user.setAddress("广州");
        user.setUsername("javaboy");
        user.setId(99);
        ctx.setRootObject(user);
        String value = exp.getValue(ctx, String.class);
        System.out.println("value = " + value);
    }

    private static void m3() {
        String expression = "sayHello";
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(expression);
        StandardEvaluationContext ctx = new StandardEvaluationContext();
        User user = new User();
        user.setAddress("广州");
        user.setUsername("javaboy");
        user.setId(99);
        ctx.setRootObject(user);
        String value = exp.getValue(ctx, String.class);
        System.out.println("value = " + value);
    }

    private static void m2() {
        String expression = "username";
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(expression);
        StandardEvaluationContext ctx = new StandardEvaluationContext();
        User user = new User();
        user.setAddress("广州");
        user.setUsername("javaboy");
        user.setId(99);
        ctx.setRootObject(user);
        String value = exp.getValue(ctx, String.class);
        System.out.println("value = " + value);
    }

    private static void m1() {
        String expressionStr = "1 + 2";
        ExpressionParser parpser = new SpelExpressionParser(); //SpelExpressionParser是Spring内部对ExpressionParser的唯一最终实现类
        Expression exp = parpser.parseExpression(expressionStr); //把该表达式，解析成一个Expression对象：SpelExpression

        // 方式一：直接计算
        Object value = exp.getValue();
        System.out.println(value.toString()); //3
        // 若你在@Value中或者xml使用此表达式，请使用#{}包裹~~~~~~~~~~~~~~~~~
        System.out.println(parpser.parseExpression("T(System).getProperty('user.dir')").getValue()); //E:\work\remotegitcheckoutproject\myprojects\java\demo-war
        System.out.println(parpser.parseExpression("T(java.lang.Math).random() * 100.0").getValue()); //27.38227555400853

        // 方式二：定义环境变量，在环境内计算拿值
        // 环境变量可设置多个值：比如BeanFactoryResolver、PropertyAccessor、TypeLocator等~~~
        // 有环境变量，就有能力处理里面的占位符 ${}
        StandardEvaluationContext context = new StandardEvaluationContext();
        System.out.println(exp.getValue(context)); //3
    }

}
