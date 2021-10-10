package org.javaboy.scheduling.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.config.CronTask;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */

/**
 * 这个是最最核心的配置类
 */
@Configuration
public class CronTaskRegistrar implements DisposableBean {
    //这个变量中保存着所有的定时任务
    private final Map<Runnable, ScheduledTask> scheduledTasks = new ConcurrentHashMap<>(16);

    @Autowired
    TaskScheduler taskScheduler;

    public TaskScheduler taskScheduler() {
        return this.taskScheduler;
    }

    /**
     * 添加一个定时任务
     * @param task
     * @param cronExpression
     */
    public void addCronTask(Runnable task,String cronExpression) {
        addCronTask(new CronTask(task, cronExpression));
    }

    private void addCronTask(CronTask cronTask) {
        if (cronTask != null) {
            Runnable runnable = cronTask.getRunnable();
            if (this.scheduledTasks.containsKey(runnable)) {
                //说明要添加的定时任务已经存在
                //先把已经存在的定时任务移除，然后再添加新的定时任务
                removeCronTask(runnable);
            }
            //添加一个定时任务
            this.scheduledTasks.put(runnable, scheduledCronTask(cronTask));
        }
    }

    private ScheduledTask scheduledCronTask(CronTask cronTask) {
        ScheduledTask scheduledTask = new ScheduledTask();
        scheduledTask.future = this.taskScheduler.schedule(cronTask.getRunnable(), cronTask.getTrigger());
        return scheduledTask;
    }

    public void removeCronTask(Runnable runnable) {
        //1. 从 Map 集合中移除
        ScheduledTask task = this.scheduledTasks.remove(runnable);
        //2. 取消正在执行的定时任务
        if (task != null) {
            task.cancel();
        }
    }

    @Override
    public void destroy() throws Exception {
        //1. 让所有定时任务停止执行
        for (ScheduledTask task : this.scheduledTasks.values()) {
            task.cancel();
        }
        //2. 清空集合
        this.scheduledTasks.clear();
    }
}
