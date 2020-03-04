package com.cy.tpes.mapper.sysmgr;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.HashMap;
import java.util.List;


@Mapper
public interface RoleMapper
{
	public List<Object> findByCondition(HashMap<String, Object> condition, RowBounds rowBounds);
	public List<Object> findByCondition(HashMap<String, Object> condition);
	public int findCountByCondition(HashMap<String, Object> condition);
	public void editByCondition(HashMap<String, Object> condition);
	public int add(HashMap<String, Object> condition);
	public int del(HashMap<String, Object> condition);
	public int addRoleMenu(HashMap<String, Object> condition);
	public int delRoleMenu(HashMap<String, Object> condition);
	public List<Object> findRoleMenu(HashMap<String, Object> condition);
	public List<Object> findRoleMenuBar(HashMap<String, Object> condition);

}
