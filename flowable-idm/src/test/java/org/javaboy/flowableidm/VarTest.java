package org.javaboy.flowableidm;

import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.variable.api.history.HistoricVariableInstance;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class VarTest {

    @Autowired
    TaskService taskService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    HistoryService historyService;

    private static final Logger logger = LoggerFactory.getLogger(VarTest.class);

    @Test
    void test07() {
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().singleResult();
        List<HistoricVariableInstance> list = historyService.createHistoricVariableInstanceQuery().processInstanceId(pi.getId()).list();
        for (HistoricVariableInstance hvi : list) {
            logger.info("name:{},type:{},value:{}", hvi.getVariableName(), hvi.getVariableTypeName(), hvi.getValue());
        }
    }

    @Test
    void test06() {
        Task task = taskService.createTaskQuery().singleResult();
        taskService.complete(task.getId());
    }

    @Test
    void test05() {
        Execution execution = runtimeService.createExecutionQuery().singleResult();
        runtimeService.setVariable(execution.getId(), "days", 10);
        Map<String, Object> variables = new HashMap<>();
        variables.put("reason", "休息一下");
        variables.put("startTime", new Date());
        runtimeService.setVariables(execution.getId(), variables);
    }


    @Test
    void test04() {
        Task task = taskService.createTaskQuery().singleResult();
        Map<String, Object> variables = new HashMap<>();
        variables.put("reason", "休息一下");
        variables.put("startTime", new Date());
        variables.put("days", 10);
        taskService.complete(task.getId(),variables);
    }


    @Test
    void test03() {
        Task task = taskService.createTaskQuery().singleResult();
        taskService.setVariableLocal(task.getId(), "days", 10);
        Map<String, Object> variables = new HashMap<>();
        variables.put("reason", "休息一下");
        variables.put("startTime", new Date());
        taskService.setVariables(task.getId(),variables);
    }

    @Test
    void test02() {
        List<Execution> list = runtimeService.createExecutionQuery().list();
        for (Execution execution : list) {
            Map<String,Object> variables = runtimeService.getVariables(execution.getId());
            logger.info("variables:{}", variables);
        }
    }

    @Test
    void test01() {
        List<Execution> list = runtimeService.createExecutionQuery().list();
        for (Execution execution : list) {
            Object reason = runtimeService.getVariable(execution.getId(), "reason");
            logger.info("reason:{}", reason);
        }
    }
}
