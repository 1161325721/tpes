package com.cy.tpes.service.sysmgr;

import org.apache.ibatis.session.RowBounds;

import java.util.HashMap;
import java.util.Map;

public interface CheckService
{

	public Object reqGuideCheck();
	public Object reqGuideCheck(HashMap<String,Object> condition);
	public Object reqCheckByCard(HashMap<String,Object> condition, RowBounds rowBounds);
	public Object reqCheckById(HashMap<String,Object> condition);
	public int addBarcode(Map map);

}
