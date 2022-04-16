package org.javaboy.mybatis_gen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.javaboy.mybatis_gen.mapper")
public class MybatisGenApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisGenApplication.class, args);
    }

}
