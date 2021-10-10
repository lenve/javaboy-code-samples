package org.javaboy.scheduling.service;

import org.javaboy.scheduling.config.CronTaskRegistrar;
import org.javaboy.scheduling.config.SchedulingRunnable;
import org.javaboy.scheduling.dao.SysJobRepository;
import org.javaboy.scheduling.model.SysJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class SysJobService {

    @Autowired
    SysJobRepository sysJobRepository;

    @Autowired
    CronTaskRegistrar cronTaskRegistrar;


    public List<SysJob> getSysJobByStatus(int status) {
        return sysJobRepository.findAllByStatus(status);
    }

    public List<SysJob> getAllJobs() {
        return sysJobRepository.findAll();
    }

    public Boolean updateSysJob(SysJob sysJob) {
        SysJob job = sysJobRepository.saveAndFlush(sysJob);
        if (job != null) {
            //更新成功
            SchedulingRunnable schedulingRunnable = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
            if (sysJob.getStatus() == 1) {
                //定时任务是开启状态
                cronTaskRegistrar.addCronTask(schedulingRunnable, sysJob.getCronExpression());
            }else{
                //定时任务是关闭状态，移除定时任务
                cronTaskRegistrar.removeCronTask(schedulingRunnable);
            }
            return true;
        }
        return false;
    }
}
