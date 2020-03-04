package com.cy.tpes.service.hwxservice;

import com.cy.tpes.aop.MyLog;
import com.cy.tpes.entity.hwxbean.*;
import com.cy.tpes.mapper.hwxmapper.CommonMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * created on 2020/2/18
 *
 * @author:胡文贤
 *
 * 通用模块相关功能
 **/
@Service
public class CommonService
{
	@Autowired
	private CommonMapper commonMapper;

	//查找用户（登录、修改密码）

	@Transactional
	public Worker findUser(String account ){return commonMapper.findUser(account);};

	//登录-获取菜单
	@MyLog("获取菜单")
	@Transactional
	public List<Menu> findMenu(String account){return commonMapper.findMenu(account);};

	//修改密码:传入的是md5密码

	@Transactional
	public int updatePwd(String account, String password){return commonMapper.updatePwd(account,password);};


	@MyLog("获取菜单")
	@Transactional
	public List<Menu> roleMenu(@Param("rid") Long rid){return commonMapper.roleMenu(rid);};


	//返回报告号 查询操作
	@MyLog("创建体检报告单")
	@Transactional
	public Long addReport(CheckupReport checkupReport){return commonMapper.addReport(checkupReport);};

	//返回有多少个项目 查询操作
	@MyLog("获取项目数量")
	@Transactional
	public List<Project> projectNum (@Param("gcid")long gcid){return commonMapper.projectNum(gcid);};

	//给项目绑定报告号 insert操作
	@MyLog("体检项目绑定报告号")
	@Transactional
	public int bindProject(CheckupProject checkupProject){return commonMapper.bindProject(checkupProject);};

	//查询项目细项列表
	@MyLog("获取项目细项数量")
	@Transactional
	public List<Item> ItemNum(@Param("gcid") long gcid,@Param("proid")long proid){return commonMapper.ItemNum(gcid, proid);};


	//插入checkitem 输入项目号
	@MyLog("添加体检细项表")
	@Transactional
	public int addCheckItem(@Param("cpid") long cpid,@Param("iid") long iid){return commonMapper.addCheckItem(cpid,iid);};

	@MyLog("生成体检相关数据")
	@Transactional
	public int addCheckRecord(Long gcid)
	{
		int flag =0;
		//查询有多少个项目
		//		添加报告checkreport记录
		CheckupReport test1 = new CheckupReport();
		test1.setGcid(gcid);
		System.out.println("test:"+test1.getGcid());

//		CheckupReport report = addReport(test1);
//		Long crid = report.getCrid();

		addReport(test1);
		Long crid = test1.getCrid();
		System.out.println("crid: " + crid);

		//查询有多少个项目
		List<Project> proList = projectNum(gcid);
		List<Item> list2 = new ArrayList<Item>(){};

		for (int i = 0; i <proList.size() ; i++)
		{
			//体检项目表 绑定报告号 还有项目号
			CheckupProject projecttest = new CheckupProject();
			projecttest.setCrid(crid);
			projecttest.setProid(proList.get(i).getProid());

			bindProject(projecttest);
			System.out.println("cpid :"+projecttest.getCpid());
			System.out.println("getProid :"+projecttest.getProid());

			list2 = ItemNum(gcid,proList.get(i).getProid());
			for (int j = 0; j <list2.size() ; j++)
			{
				//添加细项目记录
				System.out.println("for2 cpid :"+projecttest.getCpid()+"list2 iid :"+list2.get(j).getIid());
				flag = addCheckItem(projecttest.getCpid(), list2.get(j).getIid());
			}
		}

		return flag;
	}


}
