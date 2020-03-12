package com.cy.tpes.mapper.sysmgr;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface GroupClientMapper
{
	public List<Object> findGroupByCondition(HashMap<String, Object> condition, RowBounds rowBounds);
	public List<Object> findGroupByCondition(HashMap<String, Object> condition);
	public int findGroupCountByCondition(HashMap<String, Object> condition);
	public int add(HashMap<String, Object> condition);
	public void editGroupByCondition(HashMap<String, Object> condition);

}
