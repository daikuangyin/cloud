package com.sun.cloud.listener;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.history.HistoricTaskInstance;

import java.util.List;

@Slf4j
public class CustomTaskListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        log.info("任务监听类: {}", JSONUtil.toJsonStr(delegateTask));

        String taskDefinitionKey = delegateTask.getTaskDefinitionKey();
        String processInstanceId = delegateTask.getProcessInstanceId();

        List<HistoricTaskInstance> list = SpringUtil.getBean(HistoryService.class).createHistoricTaskInstanceQuery()
                .processDefinitionId(processInstanceId)
                .taskDefinitionKey(taskDefinitionKey)
                .list();

        if (list != null) {
            String user = list.get(0).getAssignee(); //获取最新的一个责任人信息回退给他
            delegateTask.setVariable("assignee", user);
        }

    }
}
