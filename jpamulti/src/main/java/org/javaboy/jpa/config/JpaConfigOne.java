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
@EnableJpaRepositories(basePackages = "org.javaboy.jpa.dao",entityManagerFactoryRef = "localContainerEntityManagerFactoryBeanOne",transactionManagerRef = "transactionManagerOne")
public class JpaConfigOne {

    @Autowired
    @Qualifier("dsOne")
    DataSource dsOne;

    @Autowired
    JpaProperties jpaProperties;

    @Bean
    @Primary
    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBeanOne(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dsOne)
                .packages("org.javaboy.jpa.bean")
                .properties(jpaProperties.getProperties())
                .persistenceUnit("pu1")
                .build();
    }

    @Bean
    PlatformTransactionManager transactionManagerOne(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(localContainerEntityManagerFactoryBeanOne(builder).getObject());
    }
}
