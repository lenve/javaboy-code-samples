package org.javaboy.flowable03.listener;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
public class SettingApproveUser implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        String approveType = (String) delegateTask.getVariable("approveType");
        if ("by_user".equals(approveType)) {
            delegateTask.setAssignee((String) delegateTask.getVariable("approveUser"));
        } else if ("by_role".equals(approveType)) {
            Object approveRole = delegateTask.getVariable("approveRole");
            delegateTask.addCandidateGroup((String) approveRole);
        }
    }
}
