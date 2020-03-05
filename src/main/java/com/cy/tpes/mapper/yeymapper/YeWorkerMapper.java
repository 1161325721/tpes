package com.cy.tpes.mapper.yeymapper;/**
 * @ClassName:TestMapper
 * @Description: TTTT
 * @Date：2020/2/11 14:45
 * @Author ：Administrator
 **/

import com.cy.tpes.entity.yeyentity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.ArrayList;
import java.util.List;

/**
 *@program: demo5
 *@description:
 *@author: liu yan
 *@create: 2020-02-11 14:45
 */
@Mapper
public interface YeWorkerMapper
{
	//登录查体检人员信息
	public YeWorker selectWorker(@Param("waccount") String account);
	//查询单位账号是否存在
	public YeGroupClient selectGcAccount(long gcAccount);
	//查询所有套餐信息
	public ArrayList<YePackage> selectAllPackage ();
	//查询体检工作人员菜单
	public ArrayList<YeMenu> selectMenu(@Param("waccount") String waccount);
	//查询单个套餐的总费用
	public long selectPackageCharge(@Param("packageName") String packageName);
	//查询账号余额
	public long selectClientBalance( long account);
	//扣款，更改余额
	public long updateGcBalance(@Param("gcbalance") double balance,@Param("gcaccount") long account);
	//插入订单数据
	public long insertGroupOrder(YeGroupOrder yeGroupOrder);
	//查询套餐的折扣
	public long selectPackageDiscount(String packageName);
	//查询最后一条订单记录
	public long selectGroupOrderIdLast();
	//插入订单与套餐关系表
	public long insertGroupOrderAndPackageRelation(ArrayList<YeOrderPackageRelation> arr);
	//查询同一单位的所有病人,通过单位id
	public List<YePatient> selectPatient(long gcid);
	//查询同一单位所有病人，通过单位账号
	public List<YePatient> selectPatientByGcAccount(long gcaccount);
	//查询同一单位所有处于正在处理状态的病人
	public List<YePatient> selectPatientOnState(@Param("gcid") long gcid ,@Param("pstate") long state);
	//改变病人的状态
	public long updatePatientState(@Param("pstate") long state, @Param("identity") String identity ,@Param("gcid") long gcid,@Param("packname") String packname);
	//批量插入病人信息
	public long insertPatient(List<YePatient> list);
	//插入导检单表
	public long insertGuideCheck(YeGuideCheck guideCheck);
	//更新病人的卡号
	public long updatePatientCardNumber(@Param("pcardnumber") String card, @Param("pid") long pid);
	//单位注册
	public long insertGroupClient(@Param("gcname") String gcname, @Param("gcaccount") String account ,@Param("gcpass") String pass,@Param("gcbalance") double balance,@Param("gcphone") String phone, @Param("gcstate") int state,@Param("gcemail") String email);
	//更新发票号
	public long updateGOInvoicing(@Param("num") long num ,@Param("goid") long goid);
	//删除单位原有病人信息
	public long deletePatientByGdID(long gcid);
	//病人表格查询具体数据集合
	public List<YePatient> selectPatientForTable(@Param("name") String name , @Param("gcname") String gcname, @Param("date") java.sql.Date date, @Param("phone") String phone, RowBounds rowBounds);
	//病人表格查询具体数据条数
	public long selectCountPatientForTable(@Param("name") String name , @Param("gcname") String gcname, @Param("date") java.sql.Date date, @Param("phone") String phone, RowBounds rowBounds);
	//查询单位最新订单的病人开单信息
	public List<YePatient> selectNewestGroupOrder(long gcaccount);
	//插入交易记录表
	public long insertFundTransaction(YeFundTransaction yeFundTransaction);
	//获得交易记录表最大id
	public long selectMaxFundID();
	//查询交易记录表数据
	public List<YeFundTransaction> selectFundTransaction(@Param("name") String name ,@Param("account") long account,@Param("date") java.sql.Date date, @Param("type") String type,RowBounds rowBounds);
	//查询交易记录标条数
	public long selectCountFund(@Param("name") String name ,@Param("account") long account,@Param("date") java.sql.Date date, @Param("type") String type,RowBounds rowBounds);
	//查询报告表数据
	public List<YeCheckupReport> selectForReport (@Param("goid") long goid , @Param("packname") String packname, @Param("pname") String pname, RowBounds rowBounds);
	//查询报告表条数
	public long selectCountForReport (@Param("goid") long goid ,@Param("packname") String packname,@Param("pname") String pname,RowBounds rowBounds);




	//查询所有将要过期的订单id
	public List<YeGroupOrder> selectOrderOutOfDeal();
	//查询素有订单下的所有导检单
	public List<YeGuideCheck> selectGcByGoId(long goid);
	//查询套餐对应的折扣
	public long selectForDiscount (long packid);
	//根据导检单号，查询未完成项目的总价
	public double selectGuideChargeNODeal(long gcid);
	//单位账户余额加上退款
	public long updateClientBalanceDecrease(@Param("gcbalance") double balance,@Param("gcid") long gcid);
	//修改过期导检单的状态0
	public long updateGuideCheckState(@Param("gcstate") String gcstate ,@Param("gcid") long gcid);
	//根据导检单id修改体检项目表的id
	public long updateCheckupProject(@Param("gcid") long gcid,@Param("cpstate") long cpstate);
	//修改过期订单的状态,退款金额和实收金额
	public long  updateOrderOutOfDeal(@Param("gorefundpay") double gorefundpay,@Param("goid") long goid);
	//根据订单号查询订单信息
	public YeGroupOrder selecOrderByGoid(long goid);


	//查询订单表数据
	public List<YeGroupOrder> selectOrderByCondition(@Param("gcname") String gcname , @Param("gcaccount") long gcaccount, @Param("date") java.sql.Date date,RowBounds rowBounds);
	//查询订单表条数
	public long selectCountOrderByCondition(@Param("gcname") String gcname , @Param("gcaccount") long gcaccount, @Param("date") java.sql.Date date,RowBounds rowBounds);

	//根据订单id，查询单条订单的信息
	public YeGroupOrder selectOrderForOne(@Param("goid") long goid);
	//延长订单有效期
	public long editDay(@Param("golasttime") long days ,@Param("goid") long goid);
}
