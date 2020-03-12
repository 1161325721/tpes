package com.cy.tpes.controller.hwxcontroller;

/**
 * created on 2020/2/17
 *
 * @author:胡文贤
 **/

import com.alibaba.fastjson.JSON;
import com.cy.tpes.entity.hwxbean.*;
import com.cy.tpes.service.hwxservice.CommonService;
import com.cy.tpes.util.harmon.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 通用功能控制器
 *
 */

@Controller
public class CommonController {
    @Autowired
    private CommonService commonService;


    //登录:根据账号查找密码，找到的md5密码和输入的密码加密后进行对比，再查询这个权限的用户的菜单再根据权限跳转到不同的界面
    @RequestMapping(path = "/dologin")
    @ResponseBody
    public String login(HttpServletRequest request) {
        String str = "";
        //获取账号
        String account = request.getParameter("login_account");
        //获取密码
        String inputPwd = request.getParameter("login_password");
        Worker worker = commonService.findUser(account);
        //返回前台的json字符串
        String jsonString = "";
        if (worker != null) {
            //如果密码比对正确
            String truePwd = worker.getWpass();
            if (truePwd.equals(inputPwd)) {
                //登录成功

                System.out.println("登录成功");
                request.getSession().setAttribute("worker", worker);
                request.getSession().setAttribute("wid", worker.getWid());
                request.getSession().setAttribute("wname", worker.getWname());
                request.getSession().setAttribute("rname", worker.getRname());
                request.getSession().setAttribute("dname", worker.getDname());
                request.getSession().setAttribute("waccount", worker.getWaccount());
//				test----------

//--------------------------
//				获取角色的权限
                long rid = worker.getRid();

//				查询角色拥有的菜单
                List<Menu> menuList = new ArrayList<Menu>();
                menuList = commonService.roleMenu(rid);

//				遍历list存到map中 map设置到session中

                HashMap<String, ArrayList<Menu>> map = new HashMap<>();

                for (int i = 0; i < menuList.size(); i++) {
                    System.out.println("firse" + menuList.get(i).getMparentid());
                    System.out.println("second" + menuList.get(i).getMid());
                    System.out.println("path" + menuList.get(i).getMname());
                }
                //存入map中

                for (int i = 0; i < menuList.size(); i++) {
                    //从集合中获取元素
                    Menu item = menuList.get(i);
                    //判断这个名字是否存在于一级菜单
                    if (map.containsKey(item.getFirstname())) {
                        map.get(item.getFirstname()).add(item);
                    } else {
                        //不存在
                        ArrayList<Menu> li1 = new ArrayList<>();
                        //加到一级菜单中
                        li1.add(item);
                        //加到map里f
                        map.put(item.getFirstname(), li1);
                    }
                }
                //把菜单存到session中
                request.getSession().setAttribute("menuMap", map);

//				Collection<ArrayList<Menu>> c = map.values();
//				Iterator<ArrayList<Menu>> it3 = c.iterator();
//				while(it3.hasNext()){
//					ArrayList<Menu> stu = it3.next();
//					System.out.println("stu :"+stu);
//				}
                //登录邮箱提示功能
                sendEmailWithLoginInfo(request, worker);

            } else {
                //登录失败
                System.out.println("密码错误");
                str = "nopass";
                return "nopass";
            }


            //session存入菜单


            jsonString = JSON.toJSONString(worker);
            System.out.println("worker字符串：" + jsonString);
        } else {
            jsonString = "false";
            return "false";
        }
        return jsonString;
    }


    //退出
    @RequestMapping(path = "/logout")
    public String logout(HttpServletRequest request) {
        //		request.setAttribute("list",testService.test());
        //		return "index";
        //清空session
        request.getSession().invalidate();
        return "hwx_backlogin";
    }
    //修改密码，输入账号，输入密码，输入新密码 旧密码加密验证是否与数据库相同，相同的话 将新密码加密后存到数据库中

    //后台主页
    @RequestMapping(path = "/back/")
    public String backindex(HttpServletRequest request) {
        //		request.setAttribute("list",testService.test());
        //		return "index";
        //清空session
        request.getSession().invalidate();
        return "hwx_backlogin";
    }

    //跳转到年项目接收 统计页面
    @RequestMapping(path = "/stat/yearProj")
    public String yearProj(HttpServletRequest request) {
        //		request.setAttribute("list",testService.test());
        //		return "index";
        //清空session
        return "hwx_sale_stat";
    }


    //跳转到年项目接收 统计页面
    @RequestMapping(path = "/stat/maxMonth")
    public String maxMonth(HttpServletRequest request) {
        return "hwx_max_month";
    }

    //跳转到科室 统计页面
    @RequestMapping(path = "/stat/department_stat")
    public String department_stat(HttpServletRequest request) {
        return "hwx_department_stat";
    }

    @RequestMapping(path = "/stat/discount")
    public String discount(HttpServletRequest request) {
        return "hwx_discount";
    }

    @RequestMapping(path = "/stat/people_stat")
    public String people_stat(HttpServletRequest request) {
        return "hwx_people_stat";
    }


    @RequestMapping(path = "/resetpwd")
    @ResponseBody
    public String resetPwd(HttpServletRequest request) {
        String str = "";
        //获取wid
        Long wid = (Long) request.getSession().getAttribute("wid");
        //获取帐号
        String waccount = (String) request.getSession().getAttribute("waccount");
        //获取密码
        String oldpwd = request.getParameter("oldpwd");
        String newpwd = request.getParameter("newpwd");
        System.out.println("验证密码是否正确-----waccount:  " + waccount + "旧密码:  " + oldpwd);
        //		根据wid 查询角色信息  对比密码是否正确
        Worker worker = commonService.findUser(waccount);
        //		验证输入密码是否正确
        if (worker.getWpass().equals(oldpwd)) {
            System.out.println("密码正确");
//			修改密码
            if (commonService.updatePwd(waccount, newpwd) > 0) {
                System.out.println("密码修改成功");
                str = "true";
            }

        } else {
            System.out.println("密码不正确");
            str = "fail";
        }

        System.out.println("str: " + str);
        return str;
    }

    @RequestMapping(path = "/veryPwd")
    @ResponseBody
    public String veryPwd(HttpServletRequest request) {
        String str = "";
        //获取wid
        Long wid = (Long) request.getSession().getAttribute("wid");
        //获取帐号
        String waccount = (String) request.getSession().getAttribute("waccount");
        //获取密码
        String oldpwd = request.getParameter("oldpwd");
        System.out.println("验证密码是否正确-----waccount:  " + waccount + "旧密码:  " + oldpwd);
        //		根据wid 查询角色信息  对比密码是否正确
        Worker worker = commonService.findUser(waccount);
        //		验证输入密码是否正确
        if (worker.getWpass().equals(oldpwd)) {
            System.out.println("密码正确");
            str = "true";
        } else {
            System.out.println("密码不正确");
            str = "fail";
        }

        return str;
    }



        //登录邮箱提示功能
    private void sendEmailWithLoginInfo(HttpServletRequest request, Worker worker) {
        String loginIp = EmailUtil.getRemoteIp(request);
        String msg = "温馨提示:您的账户 " + worker.getWaccount() + " 于 " + new Date().toLocaleString() + " 在IP: " + loginIp + " 登录!(tips:IP: 0:0:0:0:0:0:0:1为测试/服务器一体时特殊IPV6现象)";
        boolean b = EmailUtil.sendEmail("253690271@qq.com", msg);
        System.out.println("发送情况 + " + b);
        System.out.println(msg);
    }

}
