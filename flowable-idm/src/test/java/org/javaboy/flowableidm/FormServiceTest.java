package org.javaboy.flowableidm;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.Process;
import org.flowable.bpmn.model.StartEvent;
import org.flowable.bpmn.model.UserTask;
import org.flowable.engine.FormService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.TaskService;
import org.flowable.engine.form.FormProperty;
import org.flowable.engine.form.FormType;
import org.flowable.engine.form.StartFormData;
import org.flowable.engine.form.TaskFormData;
import org.flowable.engine.impl.form.DateFormType;
import org.flowable.engine.impl.form.EnumFormType;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
public class FormServiceTest {
    @Autowired
    FormService formService;
    @Autowired
    RepositoryService repositoryService;
    private static final Logger logger = LoggerFactory.getLogger(FormServiceTest.class);
    @Autowired
    TaskService taskService;

    @Test
    void test11() {
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().latestVersion().processDefinitionKey("FormDemo02").singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(pd.getId());
        Process mainProcess = bpmnModel.getMainProcess();
        List<StartEvent> list = mainProcess.findFlowElementsOfType(StartEvent.class);
        for (StartEvent startEvent : list) {
            logger.info("name:{},formKey:{}", startEvent.getName(), startEvent.getFormKey());
        }
    }

    @Test
    void test10() {
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().latestVersion().processDefinitionKey("FormDemo02").singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(pd.getId());
        Process mainProcess = bpmnModel.getMainProcess();
        List<UserTask> list = mainProcess.findFlowElementsOfType(UserTask.class);
        for (UserTask userTask : list) {
            logger.info("name:{},formKey:{}",userTask.getName(), userTask.getFormKey());
        }
    }
    
    @Test
    void test09() {
        Task task = taskService.createTaskQuery().taskAssignee("lisi").singleResult();
        taskService.complete(task.getId());
    }

    @Test
    void test08() {
        Task task = taskService.createTaskQuery().taskAssignee("zhangsan").singleResult();
        Map<String, String> vars = new HashMap<>();
        vars.put("startTime", "2022-10-30 10:10");
        vars.put("endTime", "2022-12-30 10:10");
        vars.put("reason", "玩十天");
        vars.put("days", "10");
        formService.submitTaskFormData(task.getId(),vars);
    }

    @Test
    void test07() {
        Task task = taskService.createTaskQuery().taskAssignee("zhangsan").singleResult();
        Map<String, String> vars = new HashMap<>();
        vars.put("startTime", "2022-10-30 10:10");
        vars.put("endTime", "2022-12-30 10:10");
        vars.put("reason", "玩十天");
        vars.put("days", "10");
        formService.saveFormData(task.getId(),vars);
    }


    @Test
    void test06() {
        Task task = taskService.createTaskQuery().taskAssignee("zhangsan").singleResult();
        String renderedTaskForm = (String) formService.getRenderedTaskForm(task.getId());
        System.out.println("renderedTaskForm = " + renderedTaskForm);
    }

    @Test
    void test05() {
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().latestVersion().processDefinitionKey("FormDemo02").singleResult();
        String startFormKey = formService.getStartFormKey(pd.getId());
        String renderedStartForm = (String) formService.getRenderedStartForm(pd.getId());
        System.out.println("startFormKey = " + startFormKey);
        System.out.println("renderedStartForm = " + renderedStartForm);
    }

    @Test
    void test04() {
        Task task = taskService.createTaskQuery().singleResult();
        Map<String, String> vars = new HashMap<>();
        vars.put("startTime", "2022-10-11 11:11");
        vars.put("endTime", "2022-10-19 11:11");
        formService.submitTaskFormData(task.getId(), vars);
    }

    @Test
    void test03() {
        Task task = taskService.createTaskQuery().singleResult();
        TaskFormData taskFormData = formService.getTaskFormData(task.getId());
        List<FormProperty> formProperties = taskFormData.getFormProperties();
        for (FormProperty fp : formProperties) {
            String value = fp.getValue();
            String id = fp.getId();
            boolean readable = fp.isReadable();
            boolean writable = fp.isWritable();
            boolean required = fp.isRequired();
            String name = fp.getName();
            FormType type = fp.getType();
            String key = "";
            if (type instanceof EnumFormType) {
                key = "values";
            } else if (type instanceof DateFormType) {
                key = "datePattern";
            }
            Object information = type.getInformation(key);
            logger.info("value:{},id:{},readable:{},writeable:{},required:{},name:{},info:{}", value, id, readable, writable, required, name, information);
        }
    }

    @Test
    void test02() {
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionKey("FormDemo02").latestVersion().singleResult();
        Map<String, String> vars = new HashMap<>();
        vars.put("startTime", "2022-10-10 10:10");
        vars.put("endTime", "2022-10-12 10:10");
        vars.put("reason", "玩两天");
        vars.put("days", "3");
        ProcessInstance pi = formService.submitStartFormData(pd.getId(), vars);
    }

    @Test
    void test01() {
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionKey("FormDemo02").latestVersion().singleResult();
        StartFormData startFormData = formService.getStartFormData(pd.getId());
        System.out.println("startFormData.getDeploymentId() = " + startFormData.getDeploymentId());
        System.out.println("startFormData.getFormKey() = " + startFormData.getFormKey());
        List<FormProperty> formProperties = startFormData.getFormProperties();
        for (FormProperty fp : formProperties) {
            String value = fp.getValue();
            String id = fp.getId();
            boolean readable = fp.isReadable();
            boolean writable = fp.isWritable();
            boolean required = fp.isRequired();
            String name = fp.getName();
            FormType type = fp.getType();
            String key = "";
            if (type instanceof EnumFormType) {
                key = "values";
            } else if (type instanceof DateFormType) {
                key = "datePattern";
            }
            Object information = type.getInformation(key);
            logger.info("value:{},id:{},readable:{},writeable:{},required:{},name:{},info:{}", value, id, readable, writable, required, name, information);
        }
    }
}

