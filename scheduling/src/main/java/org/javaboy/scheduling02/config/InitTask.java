package org.javaboy.scheduling02.config;

import org.javaboy.scheduling02.model.SysJob;
import org.javaboy.scheduling02.service.SysJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@Component
public class InitTask implements CommandLineRunner {
    @Autowired
    CronTaskRegistrar cronTaskRegistrar;
    @Autowired
    SysJobService sysJobService;

    @Override
    public void run(String... args) throws Exception {
        List<SysJob> list = sysJobService.getJobsByStatus(1);
        for (SysJob sysJob : list) {
            cronTaskRegistrar.addCronTask(new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams()), sysJob.getCronExpression());
        }
    }
}
