package org.javaboy.flowable02.service;

import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.variable.api.history.HistoricVariableInstance;
import org.javaboy.flowable02.model.ApproveRejectVO;
import org.javaboy.flowable02.model.AskForLeaveVO;
import org.javaboy.flowable02.model.HistoryInfo;
import org.javaboy.flowable02.model.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
public class AskForLeaveService {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    HistoryService historyService;

    @Transactional
    public RespBean askForLeave(AskForLeaveVO askForLeaveVO) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", askForLeaveVO.getName());
        variables.put("days", askForLeaveVO.getDays());
        variables.put("reason", askForLeaveVO.getReason());
        try {
            runtimeService.startProcessInstanceByKey("holidayRequest", askForLeaveVO.getName(), variables);
            return RespBean.ok("已提交请假申请");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RespBean.error("提交申请失败");
    }

    public RespBean leaveList(String identity) {
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup(identity).list();
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            Map<String, Object> variables = taskService.getVariables(task.getId());
            variables.put("id", task.getId());
            list.add(variables);
        }
        return RespBean.ok("加载成功", list);
    }

    public RespBean askForLeaveHandler(ApproveRejectVO approveRejectVO) {
        try {
            boolean approved = approveRejectVO.getApprove();
            Map<String, Object> variables = new HashMap<String, Object>();
            variables.put("approved", approved);
            variables.put("employee", approveRejectVO.getName());
            Task task = taskService.createTaskQuery().taskId(approveRejectVO.getTaskId()).singleResult();
            taskService.complete(task.getId(), variables);
            if (approved) {
                //如果是同意，还需要继续走一步
                Task t = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
                taskService.complete(t.getId());
            }
            return RespBean.ok("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RespBean.error("操作失败");
    }

    public RespBean searchResult(String name) {
        List<HistoryInfo> historyInfos = new ArrayList<>();
        List<HistoricProcessInstance> historicProcessInstances = historyService.createHistoricProcessInstanceQuery().processInstanceBusinessKey(name).finished().orderByProcessInstanceEndTime().desc().list();
        for (HistoricProcessInstance historicProcessInstance : historicProcessInstances) {
            HistoryInfo historyInfo = new HistoryInfo();
            Date startTime = historicProcessInstance.getStartTime();
            Date endTime = historicProcessInstance.getEndTime();
            List<HistoricVariableInstance> historicVariableInstances = historyService.createHistoricVariableInstanceQuery()
                    .processInstanceId(historicProcessInstance.getId())
                    .list();
            for (HistoricVariableInstance historicVariableInstance : historicVariableInstances) {
                String variableName = historicVariableInstance.getVariableName();
                Object value = historicVariableInstance.getValue();
                if ("reason".equals(variableName)) {
                    historyInfo.setReason((String) value);
                } else if ("days".equals(variableName)) {
                    historyInfo.setDays(Integer.parseInt(value.toString()));
                } else if ("approved".equals(variableName)) {
                    historyInfo.setStatus((Boolean) value);
                } else if ("name".equals(variableName)) {
                    historyInfo.setName((String) value);
                }
            }
            historyInfo.setStartTime(startTime);
            historyInfo.setEndTime(endTime);
            historyInfos.add(historyInfo);
        }
        return RespBean.ok("ok", historyInfos);
    }
}