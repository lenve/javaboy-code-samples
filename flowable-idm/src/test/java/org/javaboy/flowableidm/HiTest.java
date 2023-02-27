package org.javaboy.flowableidm;

import org.flowable.common.engine.api.history.HistoricData;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.history.ProcessInstanceHistoryLog;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.identitylink.api.history.HistoricIdentityLink;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.variable.api.history.HistoricVariableInstance;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
@SpringBootTest
public class HiTest {

    @Autowired
    HistoryService historyService;
    private static final Logger logger = LoggerFactory.getLogger(HiTest.class);
    @Autowired
    RuntimeService runtimeService;

    @Test
    void test13() {
        List<HistoricProcessInstance> instanceList = historyService.createHistoricProcessInstanceQuery().list();
        for (HistoricProcessInstance hpi : instanceList) {
            List<HistoricTaskInstance> list = historyService.createNativeHistoricTaskInstanceQuery()
                    .sql("SELECT RES.* from ACT_HI_TASKINST RES WHERE RES.PROC_INST_ID_ = #{pid} and RES.END_TIME_ is not null order by RES.ID_ asc")
                    .parameter("pid",hpi.getId()).list();
            for (HistoricTaskInstance hti : list) {
                logger.info("name:{},assignee:{},createTime:{},endTime:{}", hti.getName(), hti.getAssignee(), hti.getCreateTime(), hti.getEndTime());
            }
        }
    }

    @Test
    void test12() {
        String taskName = "提交请假申请";
        HistoricTaskInstance hti = historyService.createHistoricTaskInstanceQuery().taskName(taskName).singleResult();
        List<HistoricIdentityLink> links = historyService.getHistoricIdentityLinksForTask(hti.getId());
        for (HistoricIdentityLink link : links) {
            logger.info("{} 任务的处理人是 {}",taskName,link.getUserId());
        }
    }

    @Test
    void test11() {
        HistoricProcessInstance pi = historyService.createHistoricProcessInstanceQuery().singleResult();
        List<HistoricIdentityLink> links = historyService.getHistoricIdentityLinksForProcessInstance(pi.getId());
        for (HistoricIdentityLink link : links) {
            logger.info("userId:{}",link.getUserId());
        }
    }

    @Test
    void test10() {
        HistoricProcessInstance pi = historyService.createHistoricProcessInstanceQuery().singleResult();
        ProcessInstanceHistoryLog historyLog = historyService.createProcessInstanceHistoryLogQuery(pi.getId())
                //包括历史活动
                .includeActivities()
                //包括历史任务
                .includeTasks()
                //包括历史变量
                .includeVariables()
                .singleResult();
        logger.info("id:{},startTime:{},endTime:{}", historyLog.getId(), historyLog.getStartTime(), historyLog.getEndTime());
        List<HistoricData> historicData = historyLog.getHistoricData();
        for (HistoricData data : historicData) {
            if (data instanceof HistoricActivityInstance) {
                HistoricActivityInstance hai = (HistoricActivityInstance) data;
                logger.info("name:{},type:{}", hai.getActivityName(), hai.getActivityType());
            }
            if (data instanceof HistoricTaskInstance) {
                HistoricTaskInstance hti = (HistoricTaskInstance) data;
                logger.info("name:{},assignee:{}", hti.getName(), hti.getAssignee());
            }
            if (data instanceof HistoricVariableInstance) {
                HistoricVariableInstance hvi = (HistoricVariableInstance) data;
                logger.info("name:{},type:{},value:{}", hvi.getVariableName(), hvi.getVariableTypeName(), hvi.getValue());
            }
        }
    }


    @Test
    void test09() {
        HistoricProcessInstance pi = historyService.createHistoricProcessInstanceQuery().singleResult();
        List<HistoricVariableInstance> list = historyService.createHistoricVariableInstanceQuery().processInstanceId(pi.getId()).list();
        for (HistoricVariableInstance hvi : list) {
            logger.info("name:{},type:{},value:{}", hvi.getVariableName(), hvi.getVariableTypeName(), hvi.getValue());
        }
    }

    @Test
    void test08() {
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().list();
        for (HistoricActivityInstance hai : list) {
            logger.info("name:{},startTime:{},assignee:{},type:{}", hai.getActivityName(), hai.getStartTime(), hai.getAssignee(), hai.getActivityType());
        }
    }

    @Test
    void test07() {
        List<HistoricProcessInstance> instanceList = historyService.createHistoricProcessInstanceQuery().list();
        for (HistoricProcessInstance hpi : instanceList) {
            List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().processInstanceId(hpi.getId()).finished().list();
            for (HistoricTaskInstance hti : list) {
                logger.info("name:{},assignee:{},createTime:{},endTime:{}", hti.getName(), hti.getAssignee(), hti.getCreateTime(), hti.getEndTime());
            }
        }
    }

    @Test
    void test06() {
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().list();
        for (HistoricTaskInstance hti : list) {
            logger.info("name:{},assignee:{},createTime:{},endTime:{}", hti.getName(), hti.getAssignee(), hti.getCreateTime(), hti.getEndTime());
        }
    }

    @Test
    void test05() {
        List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery().list();
        for (HistoricProcessInstance hpi : list) {
            logger.info("name:{},startTime:{},endTime:{}", hpi.getName(), hpi.getStartTime(), hpi.getEndTime());
        }
    }

    @Test
    void test04() {
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().orderByHistoricTaskInstanceStartTime().asc().list();
        for (HistoricTaskInstance hti : list) {
            logger.info("流程ID：{}，Task 开始时间：{}，Task 结束时间:{}，Task 处理人：{}", hti.getProcessInstanceId(), hti.getCreateTime(), hti.getEndTime(), hti.getAssignee());
        }
    }

    @Test
    void test03() {
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceStartTime().asc().list();
        for (HistoricActivityInstance hai : list) {
            logger.info("流程ID：{}，活动名称：{}，活动ID:{}，活动处理人：{}", hai.getProcessInstanceId(), hai.getActivityName(), hai.getActivityId(), hai.getAssignee());
        }
    }

    @Test
    void test02() {
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId("a3786614-38eb-11ed-afc8-acde48001122").singleResult();
        logger.info("pi:{}", pi);
    }

    @Test
    void test01() {
        List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery().list();
        for (HistoricProcessInstance hi : list) {
            logger.info("==={},{},{},{},{},{}", hi.getId(), hi.getName(), hi.getStartActivityId(), hi.getStartTime(), hi.getEndActivityId(), hi.getEndTime());
        }
    }
}
