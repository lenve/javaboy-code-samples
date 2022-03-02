package org.javaboy.scheduling02.service;

import org.javaboy.scheduling02.config.CronTaskRegistrar;
import org.javaboy.scheduling02.config.SchedulingRunnable;
import org.javaboy.scheduling02.dao.SysJobRepository;
import org.javaboy.scheduling02.model.SysJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public List<SysJob> getAllJobs() {
        return sysJobRepository.findAll();
    }

    public Boolean addJob(SysJob sysJob) {
        List<SysJob> all = sysJobRepository.findAll();
        for (SysJob job : all) {
            if (job.equals(sysJob)) {
                //作业重复，添加失败
                return false;
            }
        }
        //添加
        SysJob sj = sysJobRepository.save(sysJob);
        if (sj != null) {
            //添加成功，如果新加的job是开启状态，就顺便开启
            SchedulingRunnable schedulingRunnable = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
            if (sj.getJobStatus() == 1) {
                cronTaskRegistrar.addCronTask(schedulingRunnable, sysJob.getCronExpression());
            }
            //添加成功
            return true;
        }
        return false;
    }

    public List<SysJob> getJobsByStatus(int status) {
        return sysJobRepository.findAllByJobStatus(status);
    }

    public Boolean updateJob(SysJob sysJob) {
        SysJob job = sysJobRepository.saveAndFlush(sysJob);
        if (job != null) {
            SchedulingRunnable schedulingRunnable = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
            if (sysJob.getJobStatus() == 1) {
                cronTaskRegistrar.addCronTask(schedulingRunnable, sysJob.getCronExpression());
            } else {
                cronTaskRegistrar.removeCronTask(schedulingRunnable);
            }
            return true;
        }
        return false;
    }

    public Boolean deleteJobsById(Integer id) {
        SysJob sysJob = sysJobRepository.findById(id).get();
        SchedulingRunnable schedulingRunnable = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
        cronTaskRegistrar.removeCronTask(schedulingRunnable);
        sysJobRepository.delete(sysJob);
        return true;
    }
}
