package com.cy.tpes.mapper;

import com.cy.tpes.entity.hwxbean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

///*
//	@author：胡文贤
//	@date :2/18
//  通用功能：工作人员登录、修改密码、获取菜单列表
//
// */
@Mapper
@Component
public interface CommonMapper
{
	//查找用户（登录、修改密码）
	public Worker findUser(@Param("account") String account);

	//登录-获取菜单
	public List<Menu> findMenu(@Param("account")String account);

	//修改密码:传入的是md5密码
	public int updatePwd(@Param("account")String account,@Param("password") String password);

	//权限对应的菜单
	public List<Menu> roleMenu(@Param("rid") Long rid);





	//返回报告号 查询操作
	public Long addReport(CheckupReport checkupReport);

	//返回有多少个项目 查询操作
	public List<Project> projectNum (@Param("gcid")long gcid);

	//给项目绑定报告号 insert操作
	public int bindProject(CheckupProject checkupProject);

	//查询项目细项列表
	public List<Item> ItemNum(@Param("gcid") long gcid,@Param("proid")long proid);

	//插入checkitem 输入项目号
	public int addCheckItem(@Param("cpid") long cpid,@Param("iid") long iid);






}
