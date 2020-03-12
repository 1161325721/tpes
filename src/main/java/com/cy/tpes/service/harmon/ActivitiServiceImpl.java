package com.cy.tpes.service.harmon;

import com.cy.tpes.entity.harmon.*;
import com.cy.tpes.mapper.harmon.ActivitiMapper;
import com.cy.tpes.util.harmon.ActivitiUtil;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.identity.User;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ActivitiServiceImpl implements ActivitiService {

    @Autowired
    private ActivitiMapper activitiMapper;
    @Resource
    private IdentityService identityService;

    @Resource
    private TaskService taskService;
    @Resource
    private HistoryService historyService;

    @Resource
    private RuntimeService runtimeService;

    private static final String PROCESS_DEFINE_KEY = "purchaseProcess";

    @Override
    public CardTableMsg queryWorkerGroup(CardAllConditionMsg conditionMsg) {
        int count = activitiMapper.countUserGroup(conditionMsg);
        List users = activitiMapper.queryUserGroup(conditionMsg);
        CardTableMsg cardTableMsg = new CardTableMsg();
        cardTableMsg.setData(users);
        cardTableMsg.setCount(count);
        return cardTableMsg;
    }

    @Override
    public String addUser(CardUser cardUser) {
        String userId = cardUser.getUserName();
        String groupId = cardUser.getGroupId();
        User actUser = identityService.newUser(userId);
        actUser.setPassword(cardUser.getPassword());
        identityService.saveUser(actUser);
        identityService.createMembership(userId, groupId);
        return "success";
    }

    @Override
    public String startVac(String userName, Vacation vac) {
        identityService.setAuthenticatedUserId(userName);
        // 开始流程
        ProcessInstance vacationInstance = runtimeService.startProcessInstanceByKey(PROCESS_DEFINE_KEY);
        // 查询当前任务
        Task currentTask = taskService.createTaskQuery().processInstanceId(vacationInstance.getId()).singleResult();
        // 申明任务
        taskService.claim(currentTask.getId(), userName);

        Map<String, Object> vars = new HashMap<>(4);
        vars.put("applyUser", userName);
        vars.put("days", vac.getDays());
        vars.put("reason", vac.getReason());

        taskService.complete(currentTask.getId(), vars);
        return "success";
    }

    @Override
    public CardTableMsg myVac(String userName) {
        List<ProcessInstance> instanceList = runtimeService.createProcessInstanceQuery().startedBy(userName).list();
        List<Object> vacList = new ArrayList<>();
        for (ProcessInstance instance : instanceList) {
            Vacation vac = getVac(instance);
            vacList.add(vac);
        }
        CardTableMsg cardTableMsg = new CardTableMsg();
        cardTableMsg.setData(vacList);
        return cardTableMsg;
    }

    @Override
    public CardTableMsg myVacRecord(String userName) {
        List<HistoricProcessInstance> hisProInstance = historyService.createHistoricProcessInstanceQuery()
                .processDefinitionKey(PROCESS_DEFINE_KEY).startedBy(userName).finished()
                .orderByProcessInstanceEndTime().desc().list();

        List<Object> vacList = new ArrayList<>();
        for (HistoricProcessInstance hisInstance : hisProInstance) {
            Vacation vacation = new Vacation();
            vacation.setApplyUser(hisInstance.getStartUserId());
            vacation.setApplyTime(hisInstance.getStartTime());
            vacation.setApplyStatus("申请结束");
            List<HistoricVariableInstance> varInstanceList = historyService.createHistoricVariableInstanceQuery()
                    .processInstanceId(hisInstance.getId()).list();
            ActivitiUtil.setVars(vacation, varInstanceList);
            vacList.add(vacation);
        }
        CardTableMsg cardTableMsg = new CardTableMsg();
        cardTableMsg.setData(vacList);
        return cardTableMsg;
    }

    @Override
    public CardTableMsg myAudit(String userName) {
        List<Task> taskList = taskService.createTaskQuery().taskCandidateUser(userName)
                .orderByTaskCreateTime().desc().list();
        List<Object> vacTaskList = new ArrayList<>();
        for (Task task : taskList) {
            VacTask vacTask = new VacTask();
            vacTask.setId(task.getId());
            vacTask.setName(task.getName());
            vacTask.setCreateTime(task.getCreateTime());
            String instanceId = task.getProcessInstanceId();
            ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId(instanceId).singleResult();
            Vacation vac = getVac(instance);
            vacTask.setVac(vac);
            vacTaskList.add(vacTask);
        }
        CardTableMsg cardTableMsg = new CardTableMsg();
        cardTableMsg.setData(vacTaskList);
        return cardTableMsg;
    }

    @Override
    public CardTableMsg myAuditRecord(String userName) {
        List<HistoricProcessInstance> hisProInstance = historyService.createHistoricProcessInstanceQuery()
                .processDefinitionKey(PROCESS_DEFINE_KEY).involvedUser(userName).finished()
                .orderByProcessInstanceEndTime().desc().list();

        List<String> auditTaskNameList = new ArrayList<>();
        auditTaskNameList.add("经理审批");
        auditTaskNameList.add("总监审批");
        List<Object> vacList = new ArrayList<>();
        for (HistoricProcessInstance hisInstance : hisProInstance) {
            List<HistoricTaskInstance> hisTaskInstanceList = historyService.createHistoricTaskInstanceQuery()
                    .processInstanceId(hisInstance.getId()).processFinished()
                    .taskAssignee(userName)
                    .taskNameIn(auditTaskNameList)
                    .orderByHistoricTaskInstanceEndTime().desc().list();
            boolean isMyAudit = false;
            for (HistoricTaskInstance taskInstance : hisTaskInstanceList) {
                if (taskInstance.getAssignee().equals(userName)) {
                    isMyAudit = true;
                }
            }
            if (!isMyAudit) {
                continue;
            }
            Vacation vacation = new Vacation();
            vacation.setApplyUser(hisInstance.getStartUserId());
            vacation.setApplyStatus("申请结束");
            vacation.setApplyTime(hisInstance.getStartTime());
            List<HistoricVariableInstance> varInstanceList = historyService.createHistoricVariableInstanceQuery()
                    .processInstanceId(hisInstance.getId()).list();
            ActivitiUtil.setVars(vacation, varInstanceList);
            vacList.add(vacation);
        }
        CardTableMsg cardTableMsg = new CardTableMsg();
        cardTableMsg.setData(vacList);
        return cardTableMsg;
    }

    @Override
    public String passAudit(String userName, VacTask vacTask) {
        String taskId = vacTask.getId();
        String result = vacTask.getVac().getResult();
        Map<String, Object> vars = new HashMap<>();
        vars.put("result", result);
        vars.put("auditor", userName);
        vars.put("auditTime", new Date());
        taskService.claim(taskId, userName);
        taskService.complete(taskId, vars);
        return "success";
    }

    @Override
    public String queryUserPower(String userName) {
        CardUser cardUser = new CardUser();
        cardUser.setUserName(userName);
        CardUser cardUser1 = activitiMapper.queryUserPower(cardUser);
        String msg = "";
        if (cardUser1 != null && cardUser1.getGroupId().equals("empGroup")) {
            msg = "success";
        } else {
            msg = "fail";
        }
        return msg;
    }

    private Vacation
    getVac(ProcessInstance instance) {
        Integer days = runtimeService.getVariable(instance.getId(), "days", Integer.class);
        String reason = runtimeService.getVariable(instance.getId(), "reason", String.class);
        Vacation vac = new Vacation();
        vac.setApplyUser(instance.getStartUserId());
        vac.setDays(days);
        vac.setReason(reason);
        Date startTime = instance.getStartTime(); // activiti 6 才有
        vac.setApplyTime(startTime);
        vac.setApplyStatus(instance.isEnded() ? "申请结束" : "等待审批");
        return vac;
    }
}
