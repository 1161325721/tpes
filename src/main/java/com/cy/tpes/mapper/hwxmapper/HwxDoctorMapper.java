package com.cy.tpes.mapper.hwxmapper;

import com.cy.tpes.entity.*;
import com.cy.tpes.entity.hwxbean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import javax.sound.sampled.Line;
import java.security.PublicKey;
import java.sql.Timestamp;
import java.util.List;

@Mapper
@Component
public interface HwxDoctorMapper
{
////	查询当前科室 当前卡号 所有项目
//	public List<Object> userProject(@Param("card_num")String card_num,@Param("did")int did);
////  查询所点击的项目 的所有细项(上边的语句多加一个项目id 多查一个细项表)
//	public List<Object> itemList(@Param("pid")int pid,@Param("card_num")String card_num,@Param("did") int did );
////	接收项目,修改项目的状态cpstate
//	public int newCpstate(@Param("cpid")int cpid);
////	编写总结（）
//	public int addSummary(@Param("cpid")int cpid,@Param("cpsummary") String cpsummary);
////  修改总结（）
//	public int newSummary(@Param("cpid")int cpid,@Param("cpsummary")String cpsummary);


//	查看病人信息
	public Patient patientInfo(@Param("pcardnumber") String pcardnumber);
//  查看病人的 本科室的项目
	public List<ProjectList> projectList(@Param("pcardnumber") String pcardnumber, @Param("wid")Long wid, @Param("offset") int offset, @Param("limit") int limit);
//	查看病人所有细项
	public List<ItemList> itemList(@Param("pcardnumber")String pcardnumber, @Param("wid") Long wid, @Param("proid")int proid, @Param("offset") int offset, @Param("limit") int limit);

	//	查看病人所有细项目通过cpid查看
//	public List<ItemList> itemList2(@Param("cpid") int cpid, @Param("offset") int offset, @Param("limit") int limit);

	//	修改项目的接收状态 cpstate
	public int updateCpstate(@Param("cpid") int cpid,@Param("wid")long wid);

	/**
	 * 查找细项
	 * @param iid
	 * @return
	 */
	public ItemList findItem(@Param("iid")int iid);

	/**
	 *输入细项
	 * @param ciid
	 * @param wid
	 * @param checkvalue
	 * @param result
	 * @param nowdate
	 * @return
	 */
	public int InputItem(@Param("ciid")int ciid, @Param("wid")Long wid, @Param("checkvalue")double checkvalue, @Param("result")String result, @Param("nowdate") Timestamp nowdate);

	/**
	 * 获取小结状态,已提交的不能再编辑
	 * @param cpid
	 * @return
	 */
	public CheckupProject getSumState(@Param("cpid")long cpid);

	/**
	 * 编辑小结
	 * @param cpid
	 * @param text
	 * @return
	 */
	public int updateSummary(@Param("cpid") long cpid, @Param("text") String text);

	/**
	 * 提交小结
	 * @param cpid
	 * @param text
	 * @return
	 */
	public int submitSummary(@Param("cpid") long cpid, @Param("text") String text);

	public CheckupProject getSummary(@Param("cpid")long cpid);

	public int  addPicAddress(@Param("cpid")long cpid,@Param("path") String path);

	public List<GcList> findGcList(@Param("offset")int offset, @Param("limit")int limit);

	public List<GcList> findGcList2();


	public List<ItemList> findAllItem(@Param("gcid") Long gcid,@Param("offset")int offset,@Param("limit")int limit);


	//插入数据库 体检报告表 医生id 日期 三个建议 crid nousedate  sum suggest lifeguide wid
	public int updateCrSum(@Param("crid")long crid,@Param("nousedate")Timestamp nousedate,@Param("sum")String sum,@Param("suggest")String suggest,@Param("lifeguide")String lifeguide,@Param("wid")long wid);

	//更新导检单的状态为2 已完成
	public int updateGcstate(@Param("gcid")Long gcid, @Param("gcstate") String gcstate);

//	查询套年项目接收量
	public List<HwxInfo> yearProj();
//  查询月项目接收量
	public List<HwxInfo> mouthProj();
//	查询周项目接收量
	public List<HwxInfo> weekProj();
	//接收量最大的月份
	public List<HwxInfo> maxMonth();
//	接收量最多的科室
	public List<HwxInfo> maxDepart();
//	接收量最少的科室
	public List<HwxInfo> minDepart();
//	套餐销量排行榜
	public  List<HwxInfo> discountList();
//	#科室人员比例  哪个科室占百分之多少
	public  List<HwxInfo> depart_prop();

//	#输入订单 单位报名体检人数和实际体检人数对比
	public List<HwxInfo> people_prop(@Param("goid") long goid);
//#输入单位号码 根据单位的号码 进行查询 由多少人没有体检


	//获取用户个人信息
	public Pinfo getUserInfo(@Param("gcid") Long gcid);

	//获取项目表
	public List<Project> getTodoList(@Param("gcid") Long gcid);

	//获取小结列表
	public List<ProjectList> getSumList(@Param("gcid") Long gcid);

	//获取 建议 总结 生活指导
	public CheckupReport getReport(@Param("gcid") Long gcid);


}

