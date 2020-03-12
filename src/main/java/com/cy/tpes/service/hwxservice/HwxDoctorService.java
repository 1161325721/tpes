package com.cy.tpes.service.hwxservice;

import com.cy.tpes.aop.MyLog;
import com.cy.tpes.entity.hwxbean.*;
import com.cy.tpes.mapper.hwxmapper.HwxDoctorMapper;
import com.cy.tpes.mapper.hwxmapper.HwxLogMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * created on 2020/2/19
 *
 * @author:胡文贤
 *
 * 医生相关业务
 **/

@Service
public class HwxDoctorService
{
	@Autowired
	private HwxDoctorMapper doctorMapper;
	@Autowired
	private HwxLogMapper hwxLogMapper;
////	查询当前科室 当前卡号 所有项目
//	public List<Object> userProject(String card_num,int did){return doctorMapper.userProject(card_num,did);};
//	//  查询所点击的项目 的所有细项(上边的语句多加一个项目id 多查一个细项表)
//	public List<Object> itemList(@Param("pid")int pid,@Param("card_num")String card_num,@Param("did") int did ){return doctorMapper.itemList(pid,card_num,did);};
//	//	接收项目,修改项目的状态cpstate
//	public int newCpstate(@Param("cpid")int cpid){return newCpstate(cpid);};
//	//	编写总结（）:点击编写就要新建一条
//	public int addSummary(@Param("cpid")int cpid,@Param("cpsummary") String cpsummary){return addSummary(cpid,cpsummary);};
//	//  修改总结（）
//	public int newSummary(@Param("cpid")int cpid,@Param("cpsummary")String cpsummary){return newSummary(cpid,cpsummary);};


	//  查看医生信息
	@MyLog("查看病人信息")
	@Transactional
	public Patient patientInfo(String pcardnumber){return doctorMapper.patientInfo(pcardnumber);};
	//  查看病人的 本科室的项目 有签到的
	@MyLog("查看项目列表")
	@Transactional
	public List<ProjectList> projectList(String pcardnumber, Long wid, int offset, int limit){return doctorMapper.projectList(pcardnumber,wid,offset,limit);};
	//	查看病人所有细项
	@MyLog("查看所有细项")
	@Transactional
	public List<ItemList> itemList(String pcardnumber, Long wid, int proid, int offset, int limit){return doctorMapper.itemList(pcardnumber,wid,proid,offset,limit);};

//	@MyLog("查看所有细项")
//	@Transactional
//	public List<ItemList> itemList2(int cpid){		return doctorMapper.itemList2(cpid);}

	@MyLog("接收病人项目")
	@Transactional
	public int updateCpstate(int cpid,long wid){return doctorMapper.updateCpstate(cpid,wid);};


	@MyLog("查询细项")
	@Transactional
	public ItemList findItem(int iid){return doctorMapper.findItem(iid);};

	@MyLog("医生录入细项")
	@Transactional
	public int InputItem(int ciid, long wid, double checkvalue, String result, Timestamp nowdate){return doctorMapper.InputItem(ciid,wid,checkvalue,result,nowdate);}

	@MyLog("查询项目状态")
	@Transactional
	public CheckupProject getSumState(@Param("cpid")long cpid){return doctorMapper.getSumState(cpid);};

	@MyLog("医生更新小结")
	@Transactional
	public int updateSummary(@Param("cpid") long cpid, @Param("text") String text){return doctorMapper.updateSummary(cpid,text);};

	@MyLog("医生提交小结")
	@Transactional
	public int submitSummary(@Param("cpid") long cpid, @Param("text") String text){return doctorMapper.submitSummary(cpid,text);};

	@MyLog("获取小结")
	@Transactional
	public CheckupProject getSummary(@Param("cpid")long cpid){return doctorMapper.getSummary(cpid);};

	@MyLog("上传图片")
	@Transactional
	public  int addPicAddress(@Param("cpid") long cpid,@Param("path")String path){return doctorMapper.addPicAddress(cpid,path);};

	@MyLog("查找导检单列表")
	@Transactional
	public List<GcList> findGcList(@Param("offset")int offset, @Param("limit")int limit){return doctorMapper.findGcList(offset, limit);};

	@Transactional
	public List<GcList> findGcList2(){return doctorMapper.findGcList2();};


	@MyLog("查找所有所有细项")
	@Transactional
	public List<ItemList> findAllItem(@Param("gcid") Long gcid,@Param("offset")int offset,@Param("limit")int limit){return doctorMapper.findAllItem(gcid,offset,limit);};

	//插入数据库 体检报告表 医生id 日期 三个建议 crid nousedate  sum suggest lifeguide wid
	@MyLog("填写体检报告")
	@Transactional
	public int updateCrSum(@Param("crid")long crid,@Param("nousedate")Timestamp nousedate,@Param("sum")String sum,@Param("suggest")String suggest,@Param("lifeguide")String lifeguide,@Param("wid")long wid){return doctorMapper.updateCrSum(crid, nousedate, sum, suggest, lifeguide, wid);};


	//更新导检单的状态为2 已完成
	@MyLog("更新导检单状态")
	@Transactional
	public int updateGcstate(@Param("gcid")Long gcid, @Param("gcstate") String gcstate){return doctorMapper.updateGcstate(gcid, gcstate);};


//	查询套年项目接收量
	@Transactional
	public List<HwxInfo> yearProj(){return doctorMapper.yearProj();};
	//  查询月项目接收量
	@Transactional
	public List<HwxInfo> mouthProj(){return doctorMapper.mouthProj();};
	//	查询周项目接收量
	@Transactional
	public List<HwxInfo> weekProj(){return doctorMapper.weekProj();};



	//接收量最大的月份
	@Transactional
	public List<HwxInfo> maxMonth(){return doctorMapper.maxMonth();};
	//	接收量最多的科室
	@Transactional
	public List<HwxInfo> maxDepart(){return doctorMapper.maxDepart();};
	//	接收量最少的科室
	@Transactional
	public List<HwxInfo> minDepart(){return doctorMapper.minDepart();};
	//	套餐销量排行榜
	@Transactional
	public  List<HwxInfo> discountList(){return doctorMapper.discountList();};
	//	#科室人员比例  哪个科室占百分之多少
	@Transactional
	public  List<HwxInfo>depart_prop(){return doctorMapper.depart_prop();};
	//  输入订单号，
	@Transactional
	public List<HwxInfo> people_prop(@Param("goid") long goid){return doctorMapper.people_prop(goid);};


	//获取用户个人信息
	@Transactional
	public Pinfo getUserInfo(@Param("gcid") Long gcid){return doctorMapper.getUserInfo(gcid);};

	//获取项目表
	@Transactional
	public List<Project> getTodoList(@Param("gcid") Long gcid){return doctorMapper.getTodoList(gcid);};

	//获取小结列表
	@Transactional
	public List<ProjectList> getSumList(@Param("gcid") Long gcid){return doctorMapper.getSumList(gcid);};

	//获取 建议 总结 生活指导
	@Transactional
	public CheckupReport getReport(@Param("gcid") Long gcid){return doctorMapper.getReport(gcid);};



}
