package org.javaboy.jpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @Author 江南一点雨
 * @Site www.javaboy.org 2019-06-01 14:30
 */
@Configuration
@EnableJpaRepositories(basePackages = "org.javaboy.jpa.dao2",entityManagerFactoryRef = "localContainerEntityManagerFactoryBeanTwo",transactionManagerRef = "transactionManagerTwo")
public class JpaConfigTwo {

    @Autowired
    @Qualifier("dsTwo")
    DataSource dsTwo;

    @Autowired
    JpaProperties jpaProperties;

    @Bean
//    @Primary
    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBeanTwo(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dsTwo)
                .packages("org.javaboy.jpa.bean")
                .properties(jpaProperties.getProperties())
                .persistenceUnit("pu2")
                .build();
    }

    @Bean
    PlatformTransactionManager transactionManagerTwo(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(localContainerEntityManagerFactoryBeanTwo(builder).getObject());
    }
}
