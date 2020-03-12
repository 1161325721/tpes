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
public interface LogMapper
{
	public List<Object> findByCondition(HashMap<String, Object> condition, RowBounds rowBounds);
	public List<Object> findByCondition(HashMap<String, Object> condition);
	public List<Object> findByCondition();
	public int findCountByCondition(HashMap<String, Object> condition);
	public int del(HashMap<String, Object> condition);

}
