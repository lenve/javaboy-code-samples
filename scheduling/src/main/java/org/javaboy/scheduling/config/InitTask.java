package org.javaboy.scheduling.config;

import org.javaboy.scheduling.model.SysJob;
import org.javaboy.scheduling.service.SysJobService;
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
        //获取所有需要执行的定时任务
        List<SysJob> list = sysJobService.getSysJobByStatus(1);
        for (SysJob sysJob : list) {
            //遍历 list 集合，执行每一个定时任务
            cronTaskRegistrar.addCronTask(new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams()), sysJob.getCronExpression());
        }
    }
}
