package org.javaboy.flowableidm;

import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ActivityInstance;
import org.flowable.engine.runtime.DataObject;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.identitylink.api.IdentityLink;
import org.flowable.identitylink.api.IdentityLinkInfo;
import org.flowable.identitylink.api.history.HistoricIdentityLink;
import org.flowable.job.service.TimerJobService;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
public class RuTest {
    @Autowired
    RuntimeService runtimeService;

    private static final Logger logger = LoggerFactory.getLogger(RuTest.class);

    @Autowired
    TaskService taskService;

    @Autowired
    HistoryService historyService;

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    ManagementService managementService;



    @Test
    void test27() {
        managementService.moveJobToDeadLetterJob("de55aa3f-5082-11ed-bb40-acde48001122");
    }

    @Test
    void test26() {
        managementService.moveDeadLetterJobToExecutableJob("de55aa3f-5082-11ed-bb40-acde48001122", 10);
    }

    @Test
    void test25() {
        managementService.moveTimerToExecutableJob("b7e9501d-5075-11ed-9706-acde48001122");
    }

    @Test
    void test24() {
        repositoryService.activateProcessDefinitionByKey("UserTaskDemo", true, new Date(System.currentTimeMillis() + 240 * 1000));
    }

    @Test
    void test23() {
        repositoryService.suspendProcessDefinitionByKey("UserTaskDemo", true, new Date(System.currentTimeMillis() + 240 * 1000));
    }

    @Test
    void test22() {
        Task task = taskService.createTaskQuery().singleResult();
        Map<String, Object> transientVariables = new HashMap<>();
        transientVariables.put("days", 10);
        taskService.complete(task.getId(), null, transientVariables);
    }

    @Test
    void test21() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("reason", "休息一下");
        variables.put("startTime", new Date());
        ProcessInstance pi = runtimeService
                .createProcessInstanceBuilder()
                .transientVariable("days", 10)
                .transientVariables(variables)
                .processDefinitionKey("demo01")
                .start();
        logger.info("id:{},activityId:{}", pi.getId(), pi.getActivityId());
    }

    @Test
    void test20() {
        List<Task> list = taskService.createTaskQuery().taskCandidateGroup("leader").list();
        for (Task task : list) {
            logger.info("name:{},createTime:{}", task.getName(), task.getCreateTime());
        }
    }

    @Test
    void test19() {
        List<Task> list = taskService.createTaskQuery().taskCandidateUser("zhangsan").list();
        for (Task task : list) {
            logger.info("name:{},createTime:{}", task.getName(), task.getCreateTime());
        }
    }

    @Test
    void test18() {
        List<Task> list = taskService.createTaskQuery().taskCandidateUser("javaboy").list();
        for (Task task : list) {
            taskService.deleteCandidateUser(task.getId(), "wangwu");
        }
    }

    @Test
    void test17() {
        List<Task> list = taskService.createTaskQuery().taskCandidateUser("javaboy").list();
        for (Task task : list) {
            taskService.addCandidateUser(task.getId(), "wangwu");
        }
    }

    @Test
    void test16() {
        List<Task> list = taskService.createTaskQuery().taskAssignee("javaboy").list();
        for (Task task : list) {
            taskService.setAssignee(task.getId(), null);
        }
    }


    @Test
    void test15() {
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().list();
        for (HistoricTaskInstance historicTaskInstance : list) {
            List<HistoricIdentityLink> links = historyService.getHistoricIdentityLinksForTask(historicTaskInstance.getId());
            for (HistoricIdentityLink link : links) {
                logger.info("userId:{},taskId:{},type:{},processInstanceId:{}", link.getUserId(), link.getTaskId(), link.getType(), link.getProcessInstanceId());
            }
        }
    }

    @Test
    void test14() {
        List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery().list();
        for (HistoricProcessInstance historicProcessInstance : list) {
            List<HistoricIdentityLink> links = historyService.getHistoricIdentityLinksForProcessInstance(historicProcessInstance.getId());
            for (HistoricIdentityLink link : links) {
                logger.info("userId:{},taskId:{},type:{},processInstanceId:{}", link.getUserId(), link.getTaskId(), link.getType(), link.getProcessInstanceId());
            }
        }
    }

    @Test
    void test13() {
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().list();
        for (ProcessInstance pi : list) {
            List<IdentityLink> identityLinksForProcessInstance = runtimeService.getIdentityLinksForProcessInstance(pi.getId());
            for (IdentityLink identityLink : identityLinksForProcessInstance) {
                logger.info("ProcessInstanceId:{},UserId:{}", identityLink.getProcessInstanceId(), identityLink.getUserId());
            }
        }
    }

    @Test
    void test12() {
        List<Task> list = taskService.createTaskQuery().taskCandidateUser("javaboy").list();
        for (Task task : list) {
//            taskService.claim(task.getId(), null);
//            taskService.setAssignee(task.getId(), null);
        }
    }

    @Test
    void test11() {
        List<Task> list = taskService.createTaskQuery().taskAssignee("javaboy").list();
        for (Task task : list) {
            taskService.complete(task.getId());
        }
    }

    @Test
    void test10() {
        List<Execution> list = runtimeService.createExecutionQuery().activityId("sendMsg").list();
        for (Execution execution : list) {
            runtimeService.trigger(execution.getId());
        }
    }

    @Test
    void test09() {
        identityService.setAuthenticatedUserId("wangwu");
        ProcessInstance pi = runtimeService.startProcessInstanceByKeyAndTenantId("leave", "A");
        logger.info("id:{},activityId:{}", pi.getId(), pi.getActivityId());
    }

    @Test
    void test08() {
        List<Execution> list = runtimeService.createExecutionQuery().list();
        for (Execution execution : list) {
            DataObject data = runtimeService.getDataObject(execution.getId(), "流程绘制人");
            logger.info("key:{},name:{},value:{}", data.getDataObjectDefinitionKey(), data.getName(), data.getValue());
        }
    }


    @Test
    void test07() {
        List<Execution> list = runtimeService.createExecutionQuery().list();
        for (Execution execution : list) {
            List<String> activeActivityIds = runtimeService.getActiveActivityIds(execution.getId());
            for (String activeActivityId : activeActivityIds) {
                System.out.println("activeActivityId = " + activeActivityId);
            }
        }
    }

    @Test
    void test06() {
        runtimeService.deleteProcessInstance("65ab0b38-38f3-11ed-b103-acde48001122", "javaboy想删除了");
    }

    @Test
    void test05() {
        List<Execution> list = runtimeService.createExecutionQuery().processInstanceId("6d0341c7-3729-11ed-8e4e-acde48001122").list();
        for (Execution execution : list) {
            logger.info("id:{};processInstanceId:{};name:{}", execution.getId(), execution.getProcessInstanceId(), execution.getName());
        }

    }

    @Test
    void test04() {
        String pId = "9c8557dd-3727-11ed-9404-acde48001122";
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(pId).singleResult();
        if (pi == null) {
            logger.info("{} 流程执行结束", pId);
        } else {
            logger.info("{} 流程正在执行中", pId);
        }
    }

    @Test
    void test03() {
        List<Task> list = taskService.createTaskQuery().taskAssignee("zhangsan").list();
        System.out.println("list.size() = " + list.size());
        for (Task task : list) {
//            taskService.complete(task.getId());
            taskService.setVariable(task.getId(), "a", "a");
            taskService.setVariable(task.getId(), "a", "b");
            System.out.println(new Date());
        }
    }

    @Test
    void test02() {
        List<Task> list = taskService.createTaskQuery().taskAssignee("zhangsan").list();
        for (Task task : list) {
            logger.info("id:{}；name:{}；taskDefinitionKey:{}", task.getId(), task.getName(), task.getTaskDefinitionKey());
        }
    }

    @Autowired
    IdentityService identityService;

    @Test
    void test01() {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("UserTaskDemo");
        logger.info("id:{},activityId:{}", pi.getId(), pi.getActivityId());
    }
}
