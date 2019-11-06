package org.javaboy.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @Author 江南一点雨
 * @Site www.javaboy.org 2019-09-12 15:30
 */
public class MyJob2 extends QuartzJobBean {
    HelloService helloService;

    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println(helloService.sayHello());
    }
}
