package com.sun.cloud.test.activiti;

import com.sun.cloud.test.BaseSpringBootTest;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

public class SpringActivetiTest extends BaseSpringBootTest {

    @Resource
    RepositoryService repositoryService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    HistoryService historyService;


    @Test
    public void startProcessInstance() {

        String businessKey = "1002";
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_1", businessKey);

        System.out
                .println(" 流 程 定 义 id ： " +
                        processInstance.getProcessDefinitionId());
        System.out.println("流程实例id：" + processInstance.getId());
        System.out.println("当前活动Id：" + processInstance.getActivityId());
    }


    /**
     * 查询任务
     */
    @Test
    public void TestTaskProcess() {
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey("myProcess_1")
                .taskAssignee("张三")
                .list();

        for (Task task : taskList) {
            System.out.println(" 流 程 实 例 id ： " +
                    task.getProcessInstanceId());
            System.out.println("任务id：" + task.getId());
            System.out.println("任务负责人：" + task.getAssignee());
            System.out.println("任务名称：" + task.getName());
        }

    }


    /**
     * 添加/查询审批批注
     */
    @Test
    public void TestHiComment() {
        //任务id
        String taskId = "10005";

        // 使用任务id,获取任务对象，获取流程实例id
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        //利用任务对象，获取流程实例id
        String processInstancesId=task.getProcessInstanceId();
        System.out.println(" 流 程 实 例 id ： " +
                task.getProcessInstanceId());
        Authentication.setAuthenticatedUserId("cmc"); // 添加批注时候的审核人，通常应该从session获取

        taskService.addComment(taskId,processInstancesId,"审核通过");

        taskService.complete(taskId);

        System.out.println("完成任务id=" + taskId);
    }


    /**
     * 全部流程实例挂起
     */
    @Test
    public void suspendOrActivateProcessDefinition() {
        // 流程定义id
        String processDefinitionId = "fund_flow:1:6";
        // 获得流程定义
        ProcessDefinition processDefinition = repositoryService
                .createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId)
                .singleResult();
        //是否暂停
        boolean suspend = processDefinition.isSuspended();
        if (suspend) {
            //如果暂停则激活，这里将流程定义下的所有流程实例全部激活
            repositoryService.activateProcessDefinitionById(processDefinitionId,
                    true, null);
            System.out.println("流程定义：" + processDefinitionId + "激活");
        } else {
            //如果激活则挂起，这里将流程定义下的所有流程实例全部挂起
            repositoryService.suspendProcessDefinitionById(processDefinitionId,
                    true, null);
            System.out.println("流程定义：" + processDefinitionId + "挂起");
        }
    }

    /**
     * 单个流程实例挂起
     */
    @Test
    public void suspendOrActiveProcessInstance() {
        // 流程实例id
        String processInstanceId = "20001";
        //根据流程实例id查询流程实例
        ProcessInstance processInstance =
                runtimeService.createProcessInstanceQuery()
                        .processInstanceId(processInstanceId)
                        .singleResult();
        boolean suspend = processInstance.isSuspended();
        if (suspend) {
            //如果暂停则激活
            runtimeService.activateProcessInstanceById(processInstanceId);
            System.out.println("流程实例：" + processInstanceId + "激活");
        } else {
            //如果激活则挂起
            runtimeService.suspendProcessInstanceById(processInstanceId);
            System.out.println("流程实例：" + processInstanceId + "挂起");
        }
    }

}
