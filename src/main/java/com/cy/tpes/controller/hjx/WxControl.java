package com.cy.tpes.controller.hjx;

import com.cy.tpes.entity.hjx.*;
import com.cy.tpes.entity.hjx.Package;
import com.cy.tpes.service.hjx.WxServise;
import com.cy.tpes.tools.Md5utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author Alan
 */
@RestController
@RequestMapping("/wx/")
public class WxControl {
    @Resource
    private WxServise wxServise;

    /**
     * 团检单位登录业务
     * @param account
     * @param pwd
     * @param request
     * @return
     */
    @RequestMapping("login")
    public String login(long account, String pwd,
                        HttpServletRequest request) {
        System.out.println("微信小程序正在调用登录。。。");
        String md5Pwd = Md5utils.getMD5LowerCase(pwd);
        GroupClient groupClient = wxServise.find(account);
        if (groupClient != null) {
            if (groupClient.getGcpass().equals(md5Pwd)) {
                if ("1".equals(groupClient.getGcstate())) {
                    HttpSession session = request.getSession();
                    session.setAttribute("wxgroupClient", groupClient);
                    return "success";
                } else {
                    return "fasleError";
                }
            } else {
                return "myfalse";
            }
        } else {
            return "myfalse";
        }
    }

    /**
     * 团检单位注册业务
     *
     * @param groupClient
     * @param request
     * @return
     */
    @RequestMapping("reg")
    public String reg(GroupClient groupClient,
                      HttpServletRequest request) {

        System.out.println("groupClient.getGcemail() = " + groupClient.getGcemail());
        System.out.println("微信小程序正在调用注册。。。");
        GroupClient groupClient1 = wxServise.find(groupClient.getGcaccount());
        if (groupClient1 == null) {
            String md5Pwd = Md5utils.getMD5LowerCase(groupClient.getGcpass());
            groupClient.setGcpass(md5Pwd);
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.now();
            String regTime = df.format(localDateTime);
            groupClient.setGcregisterdate(regTime);
            int num = wxServise.addGroupClient(groupClient);
            if (num > 0) {
                return "success";
            } else {
                return "myfalse";
            }
        } else {
            return "myfalse";
        }


    }

    /**
     * 套餐查询业务
     *
     * @param keywords
     * @return
     */
    @RequestMapping("combo")
    public List<Package> combo(String keywords) {

        System.out.println("套餐列表查询");
        List<Package> mylist = wxServise.findcombo(keywords, null);
        return mylist;
    }

    /**
     * 套餐项目详情业务
     *
     * @param packid
     * @return
     */
    @RequestMapping("detail")
    public List<HashMap<String, Object>> detail(String packid, Long goid) {
        List<Package> list = wxServise.findcombo(null, packid);
        long discount = 0;
        for (Package aPackage : list) {
            discount = aPackage.getPackdiscount();
        }
        List<Project> mylist = wxServise.findproject(packid);
        List<HashMap<String, Object>> itemlist = new ArrayList<>();
        List<HashMap<String, Object>> all = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        long num = 0;
        for (Project project : mylist) {
            HashMap<String, Object> getItem1 = new HashMap<>();
            String first = project.getPname();
            List<Item> mychild = project.getChild();
            StringBuilder sf = new StringBuilder();
            for (Item item : mychild) {
                String itemname = item.getIname();
                sf.append(itemname + ",");
            }
            String mysf = sf.toString();
            String s = mysf.substring(0, mysf.length() - 1);
            getItem1.put("project", first);
            getItem1.put("item", s);
            getItem1.put("sexy", project.getPgender());
            getItem1.put("price", "单价为：" + project.getPcharge());
            num = num + project.getPcharge();
            itemlist.add(getItem1);
        }
        map.put("child", itemlist);
        double dis = ((double) discount / 100);
        double numdiscount = num * dis;
        //保留两位小数并四舍五入
        BigDecimal bigDecimal = new BigDecimal(numdiscount);
        //这里的 2 就是你要保留几位小数。
        double f1 = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        map.put("numdiscount", f1);
        map.put("num", num);
        if (goid != null) {
            int countnum = wxServise.ordercount(goid);
            map.put("countnum", countnum);
        }


        all.add(map);
        return all;
    }

    /**
     * 下单流程业务
     *
     * @param request
     * @param groupOrder
     * @param packid
     * @param formData
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping("order")
    public String order(HttpServletRequest request,
                        GroupOrder groupOrder,
                        long packid,
                        String formData)
            throws JsonProcessingException {
        System.out.println("上交订单 groupOrder= ");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap = objectMapper.readValue(formData,
                new TypeReference<Map<String, Object>>() {});
        HttpSession session = request.getSession();
        GroupClient groupClient = (GroupClient) session.getAttribute("wxgroupClient");
        GroupClient groupClient1 = wxServise.find(groupClient.getGcaccount());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String regTime = df.format(localDateTime);
        groupOrder.setGoordertime(regTime);
        groupOrder.setGcid(groupClient1.getGcid());
        if (groupClient1.getGcbalance() > groupOrder.getGoprepay()) {
            double s = groupClient1.getGcbalance() - groupOrder.getGoprepay();
            GroupClient groupClientd = new GroupClient();
            groupClientd.setGcid(groupClient.getGcid());
            groupClientd.setGcbalance(s);
            FundTransaction fundTransaction=new FundTransaction();
            fundTransaction.setFttype("下订单");
            fundTransaction.setFtamount( groupOrder.getGoprepay());
            fundTransaction.setGcaccount(groupClient.getGcaccount());
            fundTransaction.setFtdate(regTime);

            int i1=wxServise.insertTrade(fundTransaction);
            int i = wxServise.updateClient(groupClientd);
            GroupClient groupClient2 = wxServise.find(groupClient.getGcaccount());
            session.setAttribute("wxgroupClient", groupClient2);
            int num = wxServise.insertOrder(groupOrder);
            long goid = groupOrder.getGoid();
            Patient patient = new Patient();
            patient.setGcid(groupClient.getGcid());
            String str = "";
            for (Map.Entry<String, Object> stringObjectEntry :
                    jsonMap.entrySet()) {
                String mynum = (String) stringObjectEntry.getValue();
                patient.setPidentitynumber(mynum);
                List<Patient> mylist = wxServise.findByidentityCard(mynum);
                if (mylist.size() == 0) {
                    int num1 = wxServise.insertPatient(patient);
                    long pid = patient.getPid();
                    int num2 = wxServise.insertOrderRelation(goid, packid, mynum);
                    if (num1 > 0 & num2 > 0) {
                        str = "success";
                    } else {
                        str = "false";
                    }
                } else {
                    int num2 = wxServise.insertOrderRelation(goid, packid, mynum);
                    if (num2 > 0) {
                        str = "success";
                    } else {
                        str = "false";
                    }
                }
            }
            if (num > 0 && str == "success" && i > 0) {
                return "success";
            } else {
                return "false";
            }
        } else {
            return "payfalse";
        }


    }


    /**
     * 订单业务
     *
     * @return
     */
    @RequestMapping("orderDetail")
    public List<GroupOrder> orderDetail(HttpServletRequest request, String stageid) {
        HttpSession session = request.getSession();
        GroupClient groupClient = (GroupClient) session.getAttribute("wxgroupClient");
        List<GroupOrder> list = wxServise.findbyGcid(groupClient.getGcid(), stageid);
        //检测只支付订单的时间
        for (GroupOrder groupOrder : list) {
            long id = groupOrder.getGoid();
            double goprepay = groupOrder.getGoprepay();
            String appointdate = groupOrder.getGoappointdate();
            long validity = groupOrder.getGolasttime();
            Date date1 = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String nowDate = format.format(date1);
            if (appointdate.compareTo(nowDate) < 0 && groupOrder.getGostate().equals("1")) {
                Calendar c = Calendar.getInstance();
                Date date = null;
                try {
                    date = new SimpleDateFormat("yy-MM-dd").parse(appointdate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                c.setTime(date);
                int day = c.get(Calendar.DATE);
                c.set(Calendar.DATE, (int) (day + validity));
                String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
                if (dayAfter.compareTo(nowDate) <= 0) {
                    System.out.println("将订单改为已取消或者已完成");
                    int num = wxServise.reportcount(id);
                    if (num == 0) {
                        System.out.println("将订单改为已取消");
                        double s = groupClient.getGcbalance() + goprepay;
                        GroupClient groupClientd = new GroupClient();
                        groupClientd.setGcid(groupClient.getGcid());
                        groupClientd.setGcbalance(s);

                        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        LocalDateTime localDateTime = LocalDateTime.now();
                        String regTime = df.format(localDateTime);
                        FundTransaction fundTransaction=new FundTransaction();
                        fundTransaction.setFttype("取消订单");
                        fundTransaction.setFtamount( goprepay);
                        fundTransaction.setGcaccount(groupClient.getGcaccount());
                        fundTransaction.setFtdate(regTime);
                     int   i2=wxServise.insertTrade(fundTransaction);


                        int i1 = wxServise.updateClient(groupClientd);
                        int i = wxServise.updateorder(id, "2", goprepay, 0);
                        GroupClient groupClient1 = wxServise.find(groupClient.getGcaccount());
                        session.setAttribute("wxgroupClient", groupClient1);

                    } else {
                        System.out.println("将订单改为已完成");
                        int countnum = wxServise.ordercount(id);
                        double price = goprepay / countnum;
                        double s = groupClient.getGcbalance() + goprepay - (price * num);
                        GroupClient groupClientd = new GroupClient();
                        groupClientd.setGcid(groupClient.getGcid());
                        groupClientd.setGcbalance(s);

                        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        LocalDateTime localDateTime = LocalDateTime.now();
                        String regTime = df.format(localDateTime);
                        FundTransaction fundTransaction=new FundTransaction();
                        fundTransaction.setFttype("完成订单应收款");
                        fundTransaction.setFtamount( goprepay - (price * num));
                        fundTransaction.setGcaccount(groupClient.getGcaccount());
                        fundTransaction.setFtdate(regTime);
                       int i2=wxServise.insertTrade(fundTransaction);



                        int i1 = wxServise.updateClient(groupClientd);
                        int i = wxServise.updateorder(id, "0", goprepay - (price * num),
                                price * num);
                        GroupClient groupClient1 = wxServise.find(groupClient.getGcaccount());
                        session.setAttribute("wxgroupClient", groupClient1);
                    }
                }
            }

        }
        //重新查询一遍
        List<GroupOrder> list1 = wxServise.findbyGcid(groupClient.getGcid(), stageid);
        return list1;
    }


    /**
     * 取消订单业务
     *
     * @return
     */
    @RequestMapping("cancelorder")
    public String cancelorder(HttpServletRequest request, String groupOrder) throws JsonProcessingException {
        HttpSession session = request.getSession();
        GroupClient groupClient = (GroupClient) session.getAttribute("wxgroupClient");
        ObjectMapper om = new ObjectMapper();
        GroupOrder groupOrder1 = om.readValue(groupOrder, GroupOrder.class);
        double goprepay = groupOrder1.getGoprepay();
        long goid = groupOrder1.getGoid();
        System.out.println("将订单改为已取消");
        int num = wxServise.reportcount(goid);
        int i1 = 0;
        int i2=0;
        int i = 0;
        if (num == 0) {
            System.out.println("将订单改为已取消");
            double s = groupClient.getGcbalance() + goprepay;
            GroupClient groupClientd = new GroupClient();
            groupClientd.setGcid(groupClient.getGcid());
            groupClientd.setGcbalance(s);

            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.now();
            String regTime = df.format(localDateTime);
            FundTransaction fundTransaction=new FundTransaction();
            fundTransaction.setFttype("取消订单");
            fundTransaction.setFtamount( goprepay);
            fundTransaction.setGcaccount(groupClient.getGcaccount());
            fundTransaction.setFtdate(regTime);
             i2=wxServise.insertTrade(fundTransaction);

            i1 = wxServise.updateClient(groupClientd);
            i = wxServise.updateorder(goid, "2", goprepay, 0);
            GroupClient groupClient1 = wxServise.find(groupClient.getGcaccount());
            session.setAttribute("wxgroupClient", groupClient1);

        } else {
            return "allowfalse";

        }
        if (i1 > 0 && i > 0) {
            return "success";

        } else {
            return "myfalse";

        }


    }

    /**
     * 查询单位信息
     *
     * @param request
     * @return
     */
    @RequestMapping("myaccount")
    public List<Object> myaccount(HttpServletRequest request) {
        HttpSession session = request.getSession();
        GroupClient groupClient = (GroupClient) session.getAttribute("wxgroupClient");
        List<Object> list = new ArrayList<>();
        list.add(groupClient);
        return list;
    }

    /**
     * 修改信息
     *
     * @param request
     * @param groupClient1
     * @return
     */
    @RequestMapping("updatainfo")
    public String updatainfo(HttpServletRequest request, GroupClient groupClient1) {
        HttpSession session = request.getSession();
        GroupClient groupClient = (GroupClient) session.getAttribute("wxgroupClient");
        System.out.println(groupClient.getGcpass());
        groupClient1.setGcid(groupClient.getGcid());
        if (groupClient.getGcpass() != null) {
            String md5pwd = Md5utils.getMD5LowerCase(groupClient.getGcpass());
            groupClient.setGcpass(md5pwd);
        }
        int i1 = wxServise.updateClient(groupClient1);
        if (i1 > 0) {
            GroupClient groupClient2 = wxServise.find(groupClient.getGcaccount());
            session.setAttribute("wxgroupClient", groupClient2);
            return "success";
        } else {
            return "myfalse";
        }
    }


    /**
     * 问题解惑展示
     *
     * @param keywords
     * @param page
     * @return
     */
    @RequestMapping("problem")
    public List<Object> problem(String keywords, Integer page) {
        System.out.println("page = " + page);
        List<Object> list = new ArrayList<>();
        int mycount = wxServise.problemcount(keywords);
        int limit = 5;
        int ofset = 0;
        if (page != null) {
            ofset = (page - 1) * limit;
        }
//        int allpage=mycount%limit==0?(mycount/limit):(mycount/limit)+1;
        int allpage = (mycount + limit - 1) / limit;

        Map<Object, Object> mymap = new HashMap<>();
        mymap.put("allpage", allpage);
        List<Problem> myproblem = wxServise.findproblem(keywords, ofset, limit);
        mymap.put("list", myproblem);
        list.add(mymap);
        return list;
    }


    /**
     * 历史项目展示
     *
     * @param idcard
     * @param myname
     * @return
     */
    @RequestMapping("myproject")
    public List<Object> myproject(String idcard, String myname, Integer page) {
        System.out.println("idcard = " + idcard + ", myname = " + myname);
        List<Object> list = new ArrayList<>();
        Map<Object, Object> mymap = new HashMap<>();
        int mycount = wxServise.myProCount(idcard,
                myname);
        int limit = 5;
        int ofset = 0;
        if (page != null) {
            ofset = (page - 1) * limit;
        }
        int allpage = (mycount + limit - 1) / limit;
        mymap.put("allpage", allpage);
        List<GuideCheck> list1 = wxServise.findMyPro(idcard,
                myname, ofset, limit);
        mymap.put("list", list1);
        list.add(mymap);
        return list;
    }

    /**
     * 细项报告展示
     *
     * @param gcid
     * @param cpid
     * @return
     */
    @RequestMapping("myitem")
    public List<CheckupProject> myitem(String gcid, String cpid) {
        System.out.println("gcid = " + gcid + ", cpid = " + cpid);
        List<CheckupProject> list = wxServise.findMyItem(gcid, cpid);
        return list;
    }

    /**
     * 历史报告展示
     *
     * @param idcard
     * @param myname
     * @param page
     * @return
     */
    @RequestMapping("report")
    public List<Object> report(String idcard, String myname, Integer page) {
        System.out.println("idcard = " + idcard + ", myname = " + myname);
        List<Object> list = new ArrayList<>();
        Map<Object, Object> mymap = new HashMap<>();
        int mycount = wxServise.myreportcount(idcard,
                myname);
        int limit = 5;
        int ofset = 0;
        if (page != null) {
            ofset = (page - 1) * limit;
        }
        int allpage = (mycount + limit - 1) / limit;
        mymap.put("allpage", allpage);
        List<GuideCheck> list1 = wxServise.findreport(idcard, myname, ofset, limit);
        mymap.put("list", list1);
        list.add(mymap);
        return list;
    }

    /**
     * 报告信息展示
     *
     * @param gcid
     * @param crid
     * @return
     */
    @RequestMapping("reportInfo")
    public List<CheckupReport> reportInfo(String gcid, String crid) {
        System.out.println("gcid = " + gcid + ", crid = " + crid);
        List<CheckupReport> list = wxServise.findreportinfo(gcid, crid);
        return list;
    }

    /**
     * 获取病人信息
     *
     * @param idcard
     * @param myname
     * @param unit
     * @return
     */
    @RequestMapping("patientInfo")
    public List<Patient> patientInfo(String idcard, String myname, String unit) {
        System.out.println("idcard = " + idcard + ", myname = " + myname);
        List<Patient> list = wxServise.findPatientinfo(idcard, myname,
                unit);
        return list;
    }

    /**
     * 修改 病人信息
     *
     * @param phone
     * @param sexy
     * @param id
     * @return
     */
    @RequestMapping("updatapatientinfo")
    public String updatapatientinfo(String phone, String sexy, String id) {
        System.out.println("phone = " + phone + ", sexy = " + sexy + ", id = " + id);
        int i = wxServise.updatepatientInfo(phone, sexy, id);
        if (i > 0) {
            return "success";
        } else {
            return "false";
        }
    }
}

