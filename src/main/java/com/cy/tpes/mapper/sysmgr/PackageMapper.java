package com.cy.tpes.mapper.sysmgr;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.HashMap;
import java.util.List;

/**
 * @program: tpes
 * @ClassName: ItemMapper
 * @description:
 * @author: JX190728
 * @create: 2020-02-18 12:57
 **/

@Mapper
public interface PackageMapper
{
	public List<Object> findByCondition(HashMap<String, Object> condition, RowBounds rowBounds);
	public List<Object> findByCondition(HashMap<String, Object> condition);
	public int findCountByCondition(HashMap<String, Object> condition);
	public int editByCondition(HashMap<String, Object> condition);
	public int add(HashMap<String, Object> condition);
	public int del(HashMap<String, Object> condition);
	public int addPackageProject(HashMap<String, Object> condition);
	public int delPackageProject(HashMap<String, Object> condition);
	public List<Object> findPackageProject(HashMap<String, Object> condition);

}
