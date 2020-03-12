package com.cy.tpes.service.hjx;

import com.cy.tpes.entity.hjx.Package;
import com.cy.tpes.entity.hjx.*;
import com.cy.tpes.mapper.hjx.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WxServise {

    @Resource
    private WxGroupClientMapper wxGroupClientMapper;
    @Resource
    private WxPackageMapper wxPackageMapper;
    @Resource
    private WxProjectMapper wxProjectMapper;
    @Resource
    private WxGroupOrderMapper wxGroupOrderMapper;
   @Resource
   private WxPatientMapper wxPatientMapper;
    @Resource
    private WxProblemMapper wxProblemMapper;
    @Resource
    private WxMyProjectMapper wxMyProjectMapper;
    @Resource
    private WxFundTransactionMapper wxFundTransactionMapper;
    @Transactional
    public GroupClient find(long account) {
        return wxGroupClientMapper.find(account);
    }

    @Transactional(rollbackFor = Exception.class)
    public int addGroupClient(GroupClient groupClient) {
        return wxGroupClientMapper.addGroupClient(groupClient);
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateClient(GroupClient groupClientd) {
        return wxGroupClientMapper.updateClient(groupClientd);
    }



    @Transactional
    public List<Package> findcombo(String keywords, String packid) {
        return wxPackageMapper.findcombo(keywords, packid);
    }


    @Transactional
    public List<Project> findproject(String packid) {
        return wxProjectMapper.findproject(packid);
    }

    @Transactional(rollbackFor = Exception.class)
    public int insertOrder(GroupOrder groupOrder) {
        return wxGroupOrderMapper.insertOrder(groupOrder);
    }
    @Transactional(rollbackFor = Exception.class)
    public int insertOrderRelation(long goid, long packid,String pidentitynumber) {
        return wxGroupOrderMapper.insertOrderRelation(goid,packid,pidentitynumber);
    }

    @Transactional
    public List<GroupOrder> findbyGcid(long gcid, String gostate) {
        return wxGroupOrderMapper.findbyGcid(gcid,gostate);
    }

    @Transactional
    public int ordercount(long goid) {
        return wxGroupOrderMapper.ordercount(goid);
    }
    @Transactional
    public int reportcount(long goid) {
        return wxGroupOrderMapper.reportcount(goid);
    }
    @Transactional(rollbackFor = Exception.class)
    public int updateorder(long goid,String gostate,double gorefundpay,double goreceivedpay) {
        return wxGroupOrderMapper.updateorder(goid,gostate,gorefundpay,goreceivedpay);
    }


    @Transactional(rollbackFor = Exception.class)
    public int insertPatient(Patient patient) {
        return wxPatientMapper.insertPatient(patient);
    }
    @Transactional
    public List<Patient> findByidentityCard(String findByidentityCard) {
        return wxPatientMapper.findByidentityCard(findByidentityCard);
    }

    @Transactional
    public List<Patient> findPatientinfo(String pidentitynumber, String pname, String gcname) {
        return wxPatientMapper.findPatientinfo(pidentitynumber,pname,gcname);
    }


    @Transactional(rollbackFor = Exception.class)
    public int updatepatientInfo(String pphone,String pgender,String pid) {
        return wxPatientMapper.updatepatientInfo(pphone,pgender,pid);
    }

    @Transactional
    public List<Problem> findproblem(String problemtittle, int ofset, int limit) {
        return wxProblemMapper.findproblem(problemtittle,ofset,limit);
    }

    @Transactional
    public int problemcount(String problemtittle) {
        return wxProblemMapper.problemcount(problemtittle);
    }

    @Transactional
    public List<GuideCheck> findMyPro(String pidentitynumber,
                                      String pname, int ofset, int limit) {
        return wxMyProjectMapper.findMyPro(pidentitynumber,
                pname,ofset,limit);
    }

    @Transactional
    public int myProCount(String pidentitynumber, String pname) {
        return wxMyProjectMapper.myProCount(pidentitynumber,pname);
    }


    @Transactional
    public List<CheckupProject> findMyItem(String gcid, String cpid) {
        return wxMyProjectMapper.findMyItem(gcid,cpid);
    }

    @Transactional
    public List<GuideCheck> findreport(String pidentitynumber, String pname, int ofset, int limit) {
        return wxMyProjectMapper.findreport(pidentitynumber,pname,ofset,limit);
    }

    @Transactional
    public int myreportcount(String pidentitynumber, String pname) {
        return wxMyProjectMapper.myreportcount(pidentitynumber,pname);
    }

    @Transactional
    public List<CheckupReport> findreportinfo(String gcid, String crid) {
        return wxMyProjectMapper.findreportinfo(gcid,crid);
    }
    @Transactional
    public int insertTrade(FundTransaction fundTransaction) {
        return wxFundTransactionMapper.insertTrade(fundTransaction);
    }



}
