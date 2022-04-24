package org.javaboy.flowable;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class FlowableApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    String staffId = "1000";
    String zuzhangId = "90";
    String managerId = "100";

    /**
     * 列出所有待执行的任务
     */
    @Test
    void allTasks() {
        List<Task> list = taskService.createTaskQuery().orderByTaskId().desc().list();
        for (Task task : list) {
            logger.info("任务 ID：{}；任务处理人：{}；任务是否挂起：{}", task.getId(), task.getAssignee(), task.isSuspended());
        }
    }

    /**
     * 开启一个流程
     */
    @Test
    void askForLeave() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("leaveTask", staffId);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("ask_for_leave", map);
        runtimeService.setVariable(processInstance.getId(), "name", "javaboy");
        runtimeService.setVariable(processInstance.getId(), "reason", "休息一下");
        runtimeService.setVariable(processInstance.getId(), "days", 10);
        logger.info("创建请假流程 processId：{}", processInstance.getId());
    }

    /**
     * 提交给组长审批
     */
    @Test
    void submitToZuzhang() {
        //员工查找到自己的任务，然后提交给组长审批
        List<Task> list = taskService.createTaskQuery().taskAssignee(staffId).orderByTaskId().desc().list();
        for (Task task : list) {
            logger.info("任务 ID：{}；任务处理人：{}；任务是否挂起：{}", task.getId(), task.getAssignee(), task.isSuspended());
            Map<String, Object> map = new HashMap<>();
            //提交给组长的时候，需要指定组长的 id
            map.put("zuzhangTask", zuzhangId);
            taskService.complete(task.getId(), map);
        }
    }

    /**
     * 组长查看自己的任务
     */
    @Test
    void zuzhangTaskList() {
        List<Task> list = taskService.createTaskQuery().taskAssignee(zuzhangId).orderByTaskId().desc().list();
        for (Task task : list) {
            Map<String, Object> info = taskService.getVariables(task.getId());
            logger.info("请假人：{};请假时间：{} 天；请假理由：{}",info.get("name"),info.get("days"),info.get("reason"));
            logger.info("任务 ID：{}；任务处理人：{}；任务是否挂起：{}", task.getId(), task.getAssignee(), task.isSuspended());
        }
    }

    /**
     * 组长审批-批准
     */
    @Test
    void zuZhangApprove() {
        List<Task> list = taskService.createTaskQuery().taskAssignee(zuzhangId).orderByTaskId().desc().list();
        for (Task task : list) {
            logger.info("组长 {} 在审批 {} 任务", task.getAssignee(), task.getId());
            Map<String, Object> map = new HashMap<>();
            //组长审批的时候，如果是同意，需要指定经理的 id
            map.put("managerTask", managerId);
            map.put("checkResult", "通过");
            taskService.complete(task.getId(), map);
        }
    }

    /**
     * 组长审批-拒绝
     */
    @Test
    void zuZhangReject() {
        List<Task> list = taskService.createTaskQuery().taskAssignee(zuzhangId).orderByTaskId().desc().list();
        for (Task task : list) {
            logger.info("组长 {} 在审批 {} 任务", task.getAssignee(), task.getId());
            Map<String, Object> map = new HashMap<>();
            //组长审批的时候，如果是拒绝，就不需要指定经理的 id
            map.put("checkResult", "拒绝");
            taskService.complete(task.getId(), map);
        }
    }

    /**
     * 经理查看自己的任务
     */
    @Test
    void managerTaskList() {
        List<Task> list = taskService.createTaskQuery().taskAssignee(managerId).orderByTaskId().desc().list();
        for (Task task : list) {
            logger.info("经理 {} 待审批的任务 ID：{}", task.getAssignee(), task.getId());
        }
    }

    /**
     * 经理审批自己的任务-批准
     */
    @Test
    void managerApprove() {
        List<Task> list = taskService.createTaskQuery().taskAssignee(managerId).orderByTaskId().desc().list();
        for (Task task : list) {
            logger.info("经理 {} 在审批 {} 任务", task.getAssignee(), task.getId());
            Map<String, Object> map = new HashMap<>();
            map.put("checkResult", "通过");
            taskService.complete(task.getId(), map);
        }
    }


    /**
     * 经理审批自己的任务-拒绝
     */
    @Test
    void managerReject() {
        List<Task> list = taskService.createTaskQuery().taskAssignee(managerId).orderByTaskId().desc().list();
        for (Task task : list) {
            logger.info("经理 {} 在审批 {} 任务", task.getAssignee(), task.getId());
            Map<String, Object> map = new HashMap<>();
            map.put("checkResult", "拒绝");
            taskService.complete(task.getId(), map);
        }
    }

}
