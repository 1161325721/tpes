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
public interface ProjectMapper
{
	public List<Object> findByCondition(HashMap<String, Object> condition, RowBounds rowBounds);
	public List<Object> findByCondition(HashMap<String, Object> condition);
	public int findCountByCondition(HashMap<String, Object> condition);
	public void editByCondition(HashMap<String, Object> condition);
	public int add(HashMap<String, Object> condition);
	public int del(HashMap<String, Object> condition);
	public int addProjectItem(HashMap<String, Object> condition);
	public int delProjectItem(HashMap<String, Object> condition);
	public List<Object> findProjectItem(HashMap<String, Object> condition);

}
