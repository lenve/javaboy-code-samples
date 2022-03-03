package org.javaboy.elasticjob.job;

import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
public class MyFirstJob implements SimpleJob {
private static final Logger logger = LoggerFactory.getLogger(MyFirstJob.class);
    @Override
    public void execute(ShardingContext shardingContext) {
        logger.info("作业名称：{}；作业参数：{}；分片总数：{}；当前分片：{}；分片参数：{}；任务编号：{}",shardingContext.getJobName(),shardingContext.getJobParameter(),shardingContext.getShardingTotalCount(),shardingContext.getShardingItem(),shardingContext.getShardingParameter(),shardingContext.getTaskId());
    }
}
