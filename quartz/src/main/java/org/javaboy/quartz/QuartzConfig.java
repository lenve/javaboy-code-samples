package org.javaboy.quartz;

import org.quartz.JobDataMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.*;

import java.util.Date;

/**
 * @Author 江南一点雨
 * @Site www.javaboy.org 2019-09-12 15:34
 *
 * 1. 配置 JOb：
 * 两种方式：
 * 第一种方式使用 MethodInvokingJobDetailFactoryBean，这种方式不支持传参
 * 第二种方式使用 JobDetailFactoryBean ，这种方式支持传参
 * 2. 配置触发器 Trigger
 */
@Configuration
public class QuartzConfig {
    @Bean
    MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean() {
        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
        bean.setTargetBeanName("myJob1");
        bean.setTargetMethod("hello");
        return bean;
    }

    @Bean
    JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(MyJob2.class);
        JobDataMap data = new JobDataMap();
        data.put("helloService", helloService());
        bean.setJobDataMap(data);
        return bean;
    }

    @Bean
    SimpleTriggerFactoryBean simpleTriggerFactoryBean() {
        SimpleTriggerFactoryBean bean = new SimpleTriggerFactoryBean();
        bean.setRepeatCount(5);
        bean.setRepeatInterval(2000);
        bean.setStartTime(new Date());
        bean.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
        return bean;
    }

    @Bean
    CronTriggerFactoryBean cronTriggerFactoryBean() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setCronExpression("0/5 * * * * ?");
        bean.setJobDetail(jobDetailFactoryBean().getObject());
        return bean;
    }

    @Bean
    SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setTriggers(simpleTriggerFactoryBean().getObject(),cronTriggerFactoryBean().getObject());
        return bean;
    }

    @Bean
    HelloService helloService() {
        return new HelloService();
    }
}
