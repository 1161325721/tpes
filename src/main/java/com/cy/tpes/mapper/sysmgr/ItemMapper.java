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
public interface ItemMapper
{
	public List<Object> findItemByCondition(HashMap<String,Object> condition, RowBounds rowBounds);
	public List<Object> findItemByCondition(HashMap<String,Object> condition);
	public int findItemCountByCondition(HashMap<String,Object> condition);
	public void editItemByCondition(HashMap<String,Object> condition);
	public int addItem(HashMap<String,Object> condition);
	public int addItemList(List<Object> list);
	public int delItem(HashMap<String,Object> condition);

}
