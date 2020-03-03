package com.cy.tpes.mapper.sysmgr;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: tpes
 * @ClassName: ItemMapper
 * @description:
 * @author: JX190728
 * @create: 2020-02-18 12:57
 **/

@Mapper
public interface CheckMapper
{
	public List<Object> findGuideCheckByCondition(HashMap<String, Object> condition, RowBounds rowBounds);
	public List<Object> findGuideCheckByCard(HashMap<String,Object> condition, RowBounds rowBounds);
	public List<Object> findGuideCheckByCondition(HashMap<String,Object> condition);
	public int findGuideCheckCountByCondition(HashMap<String,Object> condition);
	public Object findPackById(Map map);
	public Object findProjectById(Map map);
	public Object findItemById(Map map);
	public Object findDepartmentById(Map map);
	public Object findPatientById(Map map);
	public List<Object> findGuideCheckByID(Map map);
	public int addBarcode(Map map);
}
