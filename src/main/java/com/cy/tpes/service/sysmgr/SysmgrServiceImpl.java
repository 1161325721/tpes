package com.cy.tpes.service.sysmgr;

import com.cy.tpes.entity.sysmgr.LayuiTableDate;
import com.cy.tpes.mapper.sysmgr.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("SysmgrService")
public class SysmgrServiceImpl implements SysmgrService
{

	@Resource
	private GroupClientMapper groupClientMapper;
	@Resource
	private ItemMapper itemMapper;
	@Resource
	private ProjectMapper projectMapper;
	@Resource
	private PackageMapper packageMapper;
	@Resource
	private DepartMentMapper departMentMapper;
	@Resource
	private MenuMapper menuMapper;
	@Resource
	private RoleMapper roleMapper;
	@Resource
	private WorkerMapper workerMapper;
	@Resource
	private LogMapper logMapper;
	@Resource
	private ParameterMapper parameterMapper;

	@Override
	public LayuiTableDate reqGroupListByCondition(HashMap<String, Object> condition, RowBounds rowBounds)
	{
		LayuiTableDate date = new LayuiTableDate();
		date.setCount(groupClientMapper.findGroupCountByCondition(condition));
		date.setData(groupClientMapper.findGroupByCondition(condition,rowBounds));
		return date;
	}

	@Override
	public void editGroup(HashMap<String, Object> condition)
	{
		groupClientMapper.editGroupByCondition(condition);
	}

	@Override
	public LayuiTableDate reqItemListByCondition(HashMap<String, Object> condition, RowBounds rowBounds)
	{
		LayuiTableDate date = new LayuiTableDate();
		date.setCount(itemMapper.findItemCountByCondition(condition));
		date.setData(itemMapper.findItemByCondition(condition,rowBounds));
		return date;
	}

	@Override
	public LayuiTableDate reqItemListByCondition(HashMap<String, Object> condition)
	{

		LayuiTableDate date = new LayuiTableDate();
		date.setData(itemMapper.findItemByCondition(condition));
		return date;
	}


	@Override
	public void editItem(HashMap<String, Object> condition)
	{
		itemMapper.editItemByCondition(condition);
	}

	@Override
	public int addItem(HashMap<String, Object> condition)
	{
		int i = 0;
		try
			{
				i = itemMapper.addItem(condition);
			}catch (Exception e){
				System.out.println("添加失败");
			}
			System.out.println(condition.get("iid"));

		return i;
	}

	@Override
	public int delItem(HashMap<String, Object> condition)
	{
		return itemMapper.delItem(condition);
	}

	@Override
	public LayuiTableDate reqProjectListByCondition(HashMap<String, Object> condition, RowBounds rowBounds)
	{
		LayuiTableDate data = new LayuiTableDate();
		data.setCount(projectMapper.findCountByCondition(condition));
		data.setData(projectMapper.findByCondition(condition,rowBounds));
		return data;
	}

	@Override
	public LayuiTableDate reqProjectListByCondition(HashMap<String, Object> condition)
	{
		LayuiTableDate date = new LayuiTableDate();
		date.setCount(projectMapper.findCountByCondition(condition));
		date.setData(projectMapper.findByCondition(condition));
		return date;
	}

	@Override
	public void editProject(HashMap<String, Object> condition)
	{
		projectMapper.editByCondition(condition);
	}

	@Override
	public int addProject(HashMap<String, Object> condition)
	{
		int i = 0;
		try
		{
			i = projectMapper.add(condition);
		}catch (Exception e){
			System.out.println("添加失败");
		}
		return i;
	}

	@Override
	public int delProject(HashMap<String, Object> condition)
	{
		return projectMapper.del(condition);
	}

	@Override
	public List<Object> reqProjectItem(HashMap<String, Object> condition)
	{
		return projectMapper.findProjectItem(condition);
	}

	@Override
	public int editProjectItem(HashMap<String, Object> condition)
	{
		int i = 1;
		List<Integer> list = (List<Integer>)condition.get("list");
		System.out.println(condition);
		try
		{
		if (list.size()>0){
			projectMapper.addProjectItem(condition);
		}
			projectMapper.delProjectItem(condition);
		}catch (Exception e){
			i = 0;
		}

		return i;
	}

	@Override
	public LayuiTableDate reqPackageListByCondition(HashMap<String, Object> condition, RowBounds rowBounds)
	{
		LayuiTableDate data = new LayuiTableDate();
		data.setCount(packageMapper.findCountByCondition(condition));
		data.setData(packageMapper.findByCondition(condition,rowBounds));
		return data;
	}

	@Override
	public int editPackage(HashMap<String, Object> condition)
	{
		int i = 1;
		try
		{
			packageMapper.editByCondition(condition);
		}catch (Exception e){
			i = 0;
		}
		return i;

	}

	@Override
	public int addPackage(HashMap<String, Object> condition)
	{
		int i = 0;
		try
		{
			i = packageMapper.add(condition);
		}catch (Exception e){
			System.out.println("添加失败");
		}
		return i;
	}

	@Override
	public int delPackage(HashMap<String, Object> condition)
	{
		return packageMapper.del(condition);
	}

	@Override
	public List<Object> reqPackageProject(HashMap<String, Object> condition)
	{
		return packageMapper.findPackageProject(condition);
	}

	@Override
	public int editPackageProject(HashMap<String, Object> condition)
	{
		int i = 1;
		List list = (List)condition.get("list");
		try
		{
			if (list.size()>0){
				packageMapper.addPackageProject(condition);
			}
			packageMapper.delPackageProject(condition);
		}catch (Exception e){
			i = 0;
		}

		return i;
	}

	@Override
	public List<Object> reqAllDepartmentList()
	{
		return departMentMapper.findAllDepartment();
	}

	@Override
	public LayuiTableDate reqDepartmentListByCondition(HashMap<String, Object> condition, RowBounds rowBounds)
	{
		LayuiTableDate data = new LayuiTableDate();
		data.setCount(departMentMapper.findCountByCondition(condition));
		data.setData(departMentMapper.findByCondition(condition,rowBounds));
		return data;
	}

	@Override
	public int editDepartment(HashMap<String, Object> condition)
	{
		int i = 1;
		try
		{
			departMentMapper.editByCondition(condition);
		}catch (Exception e){
			i = 0;
		}
		return i;
	}

	@Override
	public int addDepartment(HashMap<String, Object> condition)
	{
		int i = 0;
		try
		{
			i = departMentMapper.add(condition);
		}catch (Exception e){
			System.out.println("添加失败");
		}
		return i;
	}

	@Override
	public int delDepartment(HashMap<String, Object> condition)
	{
		return departMentMapper.del(condition);
	}

	@Override
	public LayuiTableDate reqMenuListByCondition(HashMap<String, Object> condition, RowBounds rowBounds)
	{
		LayuiTableDate data = new LayuiTableDate();
		data.setCount(menuMapper.findCountByCondition(condition));
		data.setData(menuMapper.findByCondition(condition,rowBounds));
		return data;
	}

	@Override
	public LayuiTableDate reqMenuListByCondition(HashMap<String, Object> condition)
	{
		LayuiTableDate data = new LayuiTableDate();
		data.setCount(menuMapper.findCountByCondition(condition));
		data.setData(menuMapper.findByCondition(condition));
		return data;
	}

	@Override
	public int editMenu(HashMap<String, Object> condition)
	{
		int i = 1;
		try
		{
			menuMapper.editByCondition(condition);
		}catch (Exception e){
			i = 0;
		}
		return i;
	}

	@Override
	public int addMenu(HashMap<String, Object> condition)
	{

		int i = 0;
		try
		{
			i = menuMapper.add(condition);
		}catch (Exception e){
			System.out.println("添加失败");
		}
		return i;
	}

	@Override
	public int delMenu(HashMap<String, Object> condition)
	{
		return menuMapper.del(condition);
	}

	@Override
	public LayuiTableDate reqRoleListByCondition(HashMap<String, Object> condition, RowBounds rowBounds)
	{
		LayuiTableDate data = new LayuiTableDate();
		data.setCount(roleMapper.findCountByCondition(condition));
		data.setData(roleMapper.findByCondition(condition,rowBounds));
		return data;
	}

	@Override
	public LayuiTableDate reqRoleListByCondition(HashMap<String, Object> condition)
	{
		LayuiTableDate data = new LayuiTableDate();
		data.setCount(roleMapper.findCountByCondition(condition));
		data.setData(roleMapper.findByCondition(condition));
		return data;
	}

	@Override
	public int editRole(HashMap<String, Object> condition)
	{
		int i = 1;
		try
		{
			roleMapper.editByCondition(condition);
		}catch (Exception e){
			i = 0;
		}
		return i;
	}

	@Override
	public int addRole(HashMap<String, Object> condition)
	{
		int i = 0;
		try
		{
			i = roleMapper.add(condition);
		}catch (Exception e){
			System.out.println("添加失败");
		}
		return i;
	}

	@Override
	public int delRole(HashMap<String, Object> condition)
	{
		return menuMapper.del(condition);
	}

	@Override
	public List<Object> reqRoleMenu(HashMap<String, Object> condition)
	{
		return roleMapper.findRoleMenu(condition);
	}

	@Override
	public int editRoleMenu(HashMap<String, Object> condition)
	{
		int i = 1;
		List list = (List)condition.get("list");
		try
		{
			if (list.size()>0){
				roleMapper.addRoleMenu(condition);
			}
			roleMapper.delRoleMenu(condition);
		}catch (Exception e){
			i = 0;
		}

		return i;
	}

	@Override
	public LayuiTableDate reqWorkerListByCondition(HashMap<String, Object> condition, RowBounds rowBounds)
	{
		LayuiTableDate data = new LayuiTableDate();
		data.setCount(workerMapper.findCountByCondition(condition));
		data.setData(workerMapper.findByCondition(condition,rowBounds));
		return data;
	}

	@Override
	public int editWorker(HashMap<String, Object> condition)
	{
		int i = 1;
		try
		{
			workerMapper.editByCondition(condition);
		}catch (Exception e){
			i = 0;
		}
		return i;
	}

	@Override
	public int addWorker(HashMap<String, Object> condition)
	{
		int i = 0;
		try
		{
			i = workerMapper.add(condition);
		}catch (Exception e){
			System.out.println("添加失败");
		}
		return i;
	}

	@Override
	public int delWorker(HashMap<String, Object> condition)
	{
		return 0;
	}

	@Override
	public LayuiTableDate reqParameterListByCondition(HashMap<String, Object> condition, RowBounds rowBounds)
	{
		LayuiTableDate data = new LayuiTableDate();
		data.setCount(parameterMapper.findCountByCondition(condition));
		data.setData(parameterMapper.findByCondition(condition,rowBounds));
		return data;
	}

	@Override
	public LayuiTableDate reqParameterListByCondition(HashMap<String, Object> condition)
	{

		LayuiTableDate data = new LayuiTableDate();
		data.setCount(parameterMapper.findCountByCondition(condition));
		data.setData(parameterMapper.findByCondition(condition));
		return data;
	}

	@Override
	public int editParameter(HashMap<String, Object> condition)
	{
		int i = 1;
		try
		{
			parameterMapper.editByCondition(condition);
		}catch (Exception e){
			i = 0;
		}
		return i;
	}

	@Override
	public int addParameter(HashMap<String, Object> condition)
	{
		int i = 0;
		try
		{
			i = parameterMapper.add(condition);
		}catch (Exception e){
			System.out.println("添加失败");
		}
		return i;
	}

	@Override
	public int delParameter(HashMap<String, Object> condition)
	{
		return parameterMapper.del(condition);
	}

	@Override
	public LayuiTableDate reqLogListByCondition(HashMap<String, Object> condition, RowBounds rowBounds)
	{
		LayuiTableDate data = new LayuiTableDate();
		data.setCount(logMapper.findCountByCondition(condition));
		data.setData(logMapper.findByCondition(condition,rowBounds));
		return data;
	}


	@Override
	public int delLog(HashMap<String, Object> condition)
	{
		return logMapper.del(condition);
	}

	@Override
	public List<Object> reqRoleMenuBar(HashMap<String, Object> condition)
	{
		HashMap map = new HashMap<String, Object>();
		map.put("rid",9);
		List l = roleMapper.findRoleMenuBar(map);


		return l;
	}


}
