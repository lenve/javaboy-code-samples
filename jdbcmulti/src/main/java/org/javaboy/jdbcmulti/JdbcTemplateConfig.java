package org.javaboy.jdbcmulti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @Author 江南一点雨
 * @Site www.javaboy.org 2019-05-31 15:54
 */
@Configuration
public class JdbcTemplateConfig {
    @Resource(name = "dsOne")
    DataSource dsOne;
    @Autowired
    @Qualifier("dsTwo")
    DataSource dsTwo;

    @Bean
    JdbcTemplate jdbcTemplateOne() {
        return new JdbcTemplate(dsOne);
    }

    @Bean
    JdbcTemplate jdbcTemplateTwo() {
        return new JdbcTemplate(dsTwo);
    }

}
