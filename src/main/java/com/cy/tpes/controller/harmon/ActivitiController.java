package com.cy.tpes.controller.harmon;


import com.cy.tpes.entity.harmon.*;
import com.cy.tpes.entity.hwxbean.Worker;
import com.cy.tpes.service.harmon.ActivitiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller

public class ActivitiController {

    @Autowired
    private ActivitiService activitiService;

    //    后台流程人员配置页面
    @RequestMapping("/queryWorkerGroup")
    public @ResponseBody
    CardTableMsg queryWorkerGroup(CardAllConditionMsg conditionMsg) {
        conditionMsg.setOffset((conditionMsg.getPage() - 1) * conditionMsg.getLimit());
        CardTableMsg cardTableMsg = activitiService.queryWorkerGroup(conditionMsg);
        return cardTableMsg;
    }

    //    后台流程人员配置
    @RequestMapping("/addUser")
    public @ResponseBody
    String addUser(CardUser cardUser) {
        String msg = activitiService.addUser(cardUser);
        return msg;
    }
    //    开单权限检测
    @RequestMapping("/queryUserPower")
    public @ResponseBody
    String queryUserPower(HttpServletRequest request) {
        Worker keeper = (Worker) request.getSession().getAttribute("worker");
        String userName = keeper.getWname();
        String msg = activitiService.queryUserPower(userName);
        return msg;
    }
    //开单
    @RequestMapping("/startVac")
    public @ResponseBody
    String startVac(Vacation vac, HttpServletRequest request) {
        Worker keeper = (Worker) request.getSession().getAttribute("worker");
        String userName = keeper.getWname();
        String msg = activitiService.startVac(userName, vac);
        return msg;

    }

    //    我的请购单
    @RequestMapping("/myVac")
    public @ResponseBody
    CardTableMsg myVac(CardAllConditionMsg conditionMsg, HttpServletRequest request) {
        Worker keeper = (Worker) request.getSession().getAttribute("worker");
        String userName = keeper.getWname();
        CardTableMsg cardTableMsg = activitiService.myVac(userName);
        return cardTableMsg;
    }
    //    我的请购记录
    @RequestMapping("/myVacRecord")
    public @ResponseBody
    CardTableMsg myVacRecord(CardAllConditionMsg conditionMsg, HttpServletRequest request) {
        Worker keeper = (Worker) request.getSession().getAttribute("worker");
        String userName = keeper.getWname();
        CardTableMsg cardTableMsg = activitiService.myVacRecord(userName);
        return cardTableMsg;
    }
    //    我的审核
    @RequestMapping("/myAudit")
    public @ResponseBody
    CardTableMsg myAudit(HttpServletRequest request) {
        Worker manage = (Worker) request.getSession().getAttribute("worker");
        String userName = manage.getWname();
        return activitiService.myAudit(userName);
    }
    //我的审核列表
    @RequestMapping("/myAuditRecord")
    public @ResponseBody
    CardTableMsg myAuditRecord(HttpServletRequest request) {
        Worker manage = (Worker) request.getSession().getAttribute("worker");
        String userName = manage.getWname();
        return activitiService.myAuditRecord(userName);
    }
    //审核处理
    @RequestMapping("/passAudit")
    public @ResponseBody
    String passAudit(@RequestBody VacTask vacTask, HttpServletRequest request) {
        Worker manage = (Worker) request.getSession().getAttribute("worker");
        String userName = manage.getWname();
        return activitiService.passAudit(userName, vacTask);
    }


}
