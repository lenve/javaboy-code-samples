package org.javaboy.java8;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @作者 江南一点雨
 * @公众号 江南一点雨
 * @微信号 a_java_boy
 * @GitHub https://github.com/lenve
 * @博客 http://wangsong.blog.csdn.net
 * @网站 http://www.javaboy.org
 * @时间 2019-11-03 20:27
 */
@FunctionalInterface
interface Calculator {
    int add(int a, int b);
}

@FunctionalInterface
interface ISayAge {
    String say(int age);
}

class Person {
    private int age;

    public Person(int age) {
        this.age = age;
    }

    public void info(Function<Integer, String> iSayAge) {
        System.out.println(iSayAge.compose(i -> (int) i + 1).andThen(i -> i + "!").apply(age));
    }
}

public class Java8Demo {
    public static void main(String[] args) {
        new Thread(() -> System.out.println("hello java8")).start();
        Calculator c1 = (a, b) -> a + b;
        Calculator c2 = (int a, int b) -> a + b;
        Calculator c3 = (int a, int b) -> {
            return a + b;
        };
        Person person = new Person(99);
        person.info(i -> "我今年：" + i + "岁");

//        Consumer<String> consumer = s -> System.out.println(s);
        Consumer<String> consumer = System.out::println;
        consumer.accept("hello java8");
        new Java8Demo().sayHello("123");
    }

    public void sayHello(Java8Demo this,String hello) {
        System.out.println(hello);
    }
}