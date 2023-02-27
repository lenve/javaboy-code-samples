package org.javaboy.flowableidm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FlowableIdmApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowableIdmApplication.class, args);
    }

}
