package org.javaboy.flowableidm;

import org.apache.commons.io.FileUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ActivityInstance;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.image.impl.DefaultProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
public class BpmnDemo {

    @Autowired
    RepositoryService repositoryService;
    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;
    @Autowired
    HistoryService historyService;

    @Test
    void test05() throws IOException {
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionKey("ExclusiveGatewayDemo01").latestVersion().singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(pd.getId());
        HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery().singleResult();
        if (hpi == null) {
            return;
        }
        List<String> highLightedActivities = new ArrayList<>();
        List<String> hightLightedFlows = new ArrayList<>();
        double scaleFactor = 1.0;
        boolean drawSqquenceFlowNameWithNoLabelDI = true;
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().processInstanceId(hpi.getId()).list();
        for (HistoricActivityInstance hai : list) {
            if (hai.getActivityType().equals("sequenceFlow")) {
                hightLightedFlows.add(hai.getActivityId());
            } else {
                highLightedActivities.add(hai.getActivityId());
            }
        }
        DefaultProcessDiagramGenerator generator = new DefaultProcessDiagramGenerator();
        InputStream inputStream = generator.generateDiagram(bpmnModel, "PNG", highLightedActivities, hightLightedFlows, scaleFactor, drawSqquenceFlowNameWithNoLabelDI);
        FileUtils.copyInputStreamToFile(inputStream, new File("/Users/sang/Downloads/1.png"));
    }

    @Test
    void test04() {
        Task task = taskService.createTaskQuery().singleResult();
        taskService.complete(task.getId());
    }

    @Test
    void test02() {
        Map<String, Object> vars = new HashMap<>();
        vars.put("days", 3);
        runtimeService.startProcessInstanceByKey("ExclusiveGatewayDemo01", vars);

    }


    @Test
    void test01() throws IOException {
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionKey("ExclusiveGatewayDemo01").latestVersion().singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(pd.getId());
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().singleResult();
        List<String> highLightedActivities = new ArrayList<>();
        List<String> hightLightedFlows = new ArrayList<>();
        double scaleFactor = 1.0;
        boolean drawSqquenceFlowNameWithNoLabelDI = true;
        if (pi == null) {
            return;
        }
        List<ActivityInstance> list = runtimeService.createActivityInstanceQuery().list();
        for (ActivityInstance ai : list) {
            if (ai.getActivityType().equals("sequenceFlow")) {
                hightLightedFlows.add(ai.getActivityId());
            } else {
                highLightedActivities.add(ai.getActivityId());
            }
        }
        DefaultProcessDiagramGenerator generator = new DefaultProcessDiagramGenerator();
        InputStream inputStream = generator.generateDiagram(bpmnModel, "PNG", highLightedActivities, hightLightedFlows, scaleFactor, drawSqquenceFlowNameWithNoLabelDI);
        FileUtils.copyInputStreamToFile(inputStream, new File("/Users/sang/Downloads/1.png"));
    }

    @Test
    void test03() throws IOException {
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionKey("ExclusiveGatewayDemo01").latestVersion().singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(pd.getId());
        DefaultProcessDiagramGenerator generator = new DefaultProcessDiagramGenerator();
        InputStream inputStream = generator.generatePngDiagram(bpmnModel, 1.0, true);
        FileUtils.copyInputStreamToFile(inputStream, new File("/Users/sang/Downloads/1.png"));
    }
}
