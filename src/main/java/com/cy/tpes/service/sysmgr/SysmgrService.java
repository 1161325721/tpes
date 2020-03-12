package com.cy.tpes.service.sysmgr;

import com.cy.tpes.entity.sysmgr.LayuiTableDate;
import org.apache.ibatis.session.RowBounds;

import java.util.HashMap;
import java.util.List;

/**
 * @program: tpes
 * @ClassName: SysmgrService
 * @description: 系统管理sercive
 * @author: JX190728
 * @create: 2020-02-16 11:09
 **/
public interface SysmgrService
{

	public LayuiTableDate reqGroupListByCondition(HashMap<String,Object> condition, RowBounds rowBounds);
	public LayuiTableDate reqGroupListByCondition(HashMap<String,Object> condition);
	public void editGroup(HashMap<String,Object> condition);
	public int addGroup(HashMap<String,Object> condition);


	public LayuiTableDate reqItemListByCondition(HashMap<String,Object> condition, RowBounds rowBounds);
	public LayuiTableDate reqItemListByCondition(HashMap<String,Object> condition);
	public void editItem(HashMap<String,Object> condition);
	public int addItem(HashMap<String,Object> condition);
	public int delItem(HashMap<String,Object> condition);

	public LayuiTableDate reqProjectListByCondition(HashMap<String,Object> condition, RowBounds rowBounds);
	public LayuiTableDate reqProjectListByCondition(HashMap<String,Object> condition);
	public void editProject(HashMap<String,Object> condition);
	public int addProject(HashMap<String,Object> condition);
	public int delProject(HashMap<String,Object> condition);
	public List<Object> reqProjectItem(HashMap<String,Object> condition);
	public int editProjectItem(HashMap<String,Object> condition);

	public LayuiTableDate reqPackageListByCondition(HashMap<String,Object> condition, RowBounds rowBounds);
	public int editPackage(HashMap<String,Object> condition);
	public int addPackage(HashMap<String,Object> condition);
	public int delPackage(HashMap<String,Object> condition);
	public List<Object> reqPackageProject(HashMap<String,Object> condition);
	public int editPackageProject(HashMap<String,Object> condition);

	public  List<Object> reqAllDepartmentList();
	public LayuiTableDate reqDepartmentListByCondition(HashMap<String,Object> condition, RowBounds rowBounds);
	public int editDepartment(HashMap<String,Object> condition);
	public int addDepartment(HashMap<String,Object> condition);
	public int delDepartment(HashMap<String,Object> condition);

	public LayuiTableDate reqMenuListByCondition(HashMap<String,Object> condition, RowBounds rowBounds);
	public LayuiTableDate reqMenuListByCondition(HashMap<String,Object> condition);
	public int editMenu(HashMap<String,Object> condition);
	public int addMenu(HashMap<String,Object> condition);
	public int delMenu(HashMap<String,Object> condition);

	public LayuiTableDate reqRoleListByCondition(HashMap<String,Object> condition, RowBounds rowBounds);
	public LayuiTableDate reqRoleListByCondition(HashMap<String,Object> condition);
	public int editRole(HashMap<String,Object> condition);
	public int addRole(HashMap<String,Object> condition);
	public int delRole(HashMap<String,Object> condition);
	public List<Object> reqRoleMenu(HashMap<String,Object> condition);
	public int editRoleMenu(HashMap<String,Object> condition);

	public LayuiTableDate reqWorkerListByCondition(HashMap<String,Object> condition, RowBounds rowBounds);
	public LayuiTableDate reqWorkerListByCondition(HashMap<String,Object> condition);
	public int editWorker(HashMap<String,Object> condition);
	public int addWorker(HashMap<String,Object> condition);
	public int delWorker(HashMap<String,Object> condition);

	public LayuiTableDate reqParameterListByCondition(HashMap<String,Object> condition, RowBounds rowBounds);
	public LayuiTableDate reqParameterListByCondition(HashMap<String,Object> condition);
	public int editParameter(HashMap<String,Object> condition);
	public int addParameter(HashMap<String,Object> condition);
	public int delParameter(HashMap<String,Object> condition);

	public LayuiTableDate reqLogListByCondition(HashMap<String,Object> condition, RowBounds rowBounds);
	public LayuiTableDate reqLogListByCondition(HashMap<String,Object> condition);
	public int delLog(HashMap<String,Object> condition);

	public List<Object> reqRoleMenuBar(HashMap<String,Object> condition);

}
