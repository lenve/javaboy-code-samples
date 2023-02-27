package org.javaboy.flowable_demo;

import org.flowable.engine.FormService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.form.api.FormInfo;
import org.flowable.form.model.FormField;
import org.flowable.form.model.SimpleFormModel;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class FlowableDemoApplicationTests {

    @Autowired
    RuntimeService runtimeService;
    @Autowired
    TaskService taskService;
    @Autowired
    FormService formService;

    @Test
    void test03() {
    }

    @Test
    void test02() {
        Task task = taskService.createTaskQuery().singleResult();
        Map<String, Object> vars = new HashMap<>();
        vars.put("days", 10);
        vars.put("reason", "玩一下");
        vars.put("startTime", "2022-10-10");
        vars.put("endTime", "2022-11-10");
        taskService.complete(task.getId(),vars);
    }

    @Test
    void test01() {
        Task task = taskService.createTaskQuery().singleResult();
        FormInfo formInfo = taskService.getTaskFormModel(task.getId());
        SimpleFormModel formModel = (SimpleFormModel) formInfo.getFormModel();
        System.out.println("formInfo.getId() = " + formInfo.getId());
        System.out.println("formInfo.getName() = " + formInfo.getName());
        System.out.println("formInfo.getKey() = " + formInfo.getKey());
        List<FormField> fields = formModel.getFields();
        for (FormField field : fields) {
            System.out.println("field.getId() = " + field.getId());
            System.out.println("field.getName() = " + field.getName());
            System.out.println("field.getValue() = " + field.getValue());
            System.out.println("field.getType() = " + field.getType());
            System.out.println("===============");
        }
    }

    @Test
    void contextLoads() {
        runtimeService.startProcessInstanceByKey("askforleave");
    }

}
