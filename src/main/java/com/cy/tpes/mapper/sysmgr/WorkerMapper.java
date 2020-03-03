package com.cy.tpes.mapper.sysmgr;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface WorkerMapper
{
	public List<Object> findByCondition(HashMap<String, Object> condition, RowBounds rowBounds);
	public int findCountByCondition(HashMap<String, Object> condition);
	public int editByCondition(HashMap<String, Object> condition);
	public int add(HashMap<String, Object> condition);

}
