package org.javaboy.flowable05.service;

import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
import org.flowable.engine.impl.persistence.entity.ExecutionEntityImpl;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.repository.ProcessDefinitionQuery;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.form.api.FormInfo;
import org.flowable.task.api.Task;
import org.flowable.variable.api.history.HistoricVariableInstance;
import org.javaboy.flowable05.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
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

    @Autowired
    FormService formService;
    @Autowired
    RepositoryService repositoryService;

    @Transactional
    public RespBean askForLeave(AskForLeaveVO askForLeaveVO) {
        Map<String, Object> variables = new HashMap<>();
        askForLeaveVO.setName(SecurityContextHolder.getContext().getAuthentication().getName());
        variables.put("name", askForLeaveVO.getName());
        variables.put("days", String.valueOf(askForLeaveVO.getDays()));
        variables.put("reason", askForLeaveVO.getReason());
        variables.put("approveType", askForLeaveVO.getApproveType());
        variables.put("approveUser", askForLeaveVO.getApproveUser());
        variables.put("approveRole", askForLeaveVO.getApproveRole());
        try {
//            runtimeService.startProcessInstanceByKey("holidayRequest", askForLeaveVO.getName(), variables);
//            runtimeService.startProcessInstanceWithForm("holidayRequest", "", variables, "holidayRequest");
//            ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceName("holidayRequest").singleResult();
//            ProcessInstance pi = runtimeService.createProcessInstanceQuery().processDefinitionId("holidayRequest").singleResult();
//            ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionId("holidayRequest").singleResult();
//            Deployment deployment = repositoryService.createDeploymentQuery().deploymentName("holiday-request.bpmn20.xml").singleResult();
//            System.out.println("deployment = " + deployment);
//            ProcessInstance pi = runtimeService.startProcessInstanceByKey("holidayRequest");
//            formService.submitStartFormData(pi.getProcessDefinitionId(), askForLeaveVO.getName(), variables);
//            FormInfo fi = runtimeService.getStartFormModel(pi.getProcessDefinitionId(), pi.getProcessInstanceId());
//            System.out.println("fi = " + fi);
            ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionKey("holidayRequest").singleResult();
            ProcessInstance pi = runtimeService.startProcessInstanceWithForm(pd.getId(), "", variables, pd.getName());
//            ProcessInstance pi = formService.submitStartFormData(pd.getId(), askForLeaveVO.getName(), variables);
            ((ExecutionEntityImpl) pi).setBusinessKey(askForLeaveVO.getName());
            FormInfo fi = runtimeService.getStartFormModel(pd.getId(), pi.getProcessInstanceId());
            System.out.println("pd = " + pd);
            return RespBean.ok("已提交请假申请");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RespBean.error("提交申请失败");
    }

    /**
     * 待审批列表
     *
     * @return
     */
    public RespBean leaveList() {
        String identity = SecurityContextHolder.getContext().getAuthentication().getName();
        //找到所有分配给你的任务
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(identity).list();
        //找到所有分配给你所属角色的任务
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            tasks.addAll(taskService.createTaskQuery().taskCandidateGroup(authority.getAuthority()).list());
        }
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
            variables.put("approveUser", SecurityContextHolder.getContext().getAuthentication().getName());
            Task task = taskService.createTaskQuery().taskId(approveRejectVO.getTaskId()).singleResult();
            taskService.complete(task.getId(), variables);
            if (approved) {
                //如果是同意，还需要继续走一步
                Task t = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
                System.out.println("t = " + t);
                taskService.complete(t.getId());
            }
            return RespBean.ok("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RespBean.error("操作失败");
    }

    public RespBean searchResult() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        List<HistoryInfo> infos = new ArrayList<>();
        List<HistoricProcessInstance> historicProcessInstances = historyService.createHistoricProcessInstanceQuery().variableValueEquals("name",name)
                .orderByProcessInstanceStartTime().desc().list();
        for (HistoricProcessInstance historicProcessInstance : historicProcessInstances) {
            HistoryInfo historyInfo = new HistoryInfo();
            historyInfo.setStatus(3);
            Date startTime = historicProcessInstance.getStartTime();
            Date endTime = historicProcessInstance.getEndTime();
            List<HistoricVariableInstance> historicVariableInstances = historyService.createHistoricVariableInstanceQuery()
                    .processInstanceId(historicProcessInstance.getId())
                    .list();
            System.out.println("historicVariableInstances = " + historicVariableInstances);
            for (HistoricVariableInstance historicVariableInstance : historicVariableInstances) {
                String variableName = historicVariableInstance.getVariableName();
                Object value = historicVariableInstance.getValue();
                if ("reason".equals(variableName)) {
                    historyInfo.setReason((String) value);
                } else if ("days".equals(variableName)) {
                    historyInfo.setDays(Integer.parseInt(value.toString()));
                } else if ("approved".equals(variableName)) {
                    Boolean v = (Boolean) value;
//                    System.out.println("v = " + v);
                    if (v) {
                        historyInfo.setStatus(1);
                    } else {
                        historyInfo.setStatus(2);
                    }
                } else if ("name".equals(variableName)) {
                    historyInfo.setName((String) value);
                } else if ("approveUser".equals(variableName)) {
                    historyInfo.setApproveUser((String) value);
                }
            }
            historyInfo.setStartTime(startTime);
            historyInfo.setEndTime(endTime);
            infos.add(historyInfo);
        }
        return RespBean.ok("ok", infos);
    }
}