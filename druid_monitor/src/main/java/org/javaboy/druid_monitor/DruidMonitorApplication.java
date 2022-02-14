package org.javaboy.druid_monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("org.javaboy.druid_monitor.filter")
public class DruidMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DruidMonitorApplication.class, args);
    }

}
