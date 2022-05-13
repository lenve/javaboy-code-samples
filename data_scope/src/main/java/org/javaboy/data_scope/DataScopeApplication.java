package org.javaboy.data_scope;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "org.javaboy.data_scope.mapper")
public class DataScopeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataScopeApplication.class, args);
    }

}
