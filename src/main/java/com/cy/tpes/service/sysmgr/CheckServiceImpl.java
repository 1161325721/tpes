package com.cy.tpes.service.sysmgr;

import com.cy.tpes.entity.sysmgr.LayuiTableDate;
import com.cy.tpes.mapper.sysmgr.CheckMapper;
import com.google.gson.Gson;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service("checkService")
public class CheckServiceImpl implements CheckService

{

	@Resource
	private CheckMapper checkMapper;

	@Override
	public Object reqGuideCheck()
	{
		Map m = new HashMap<String,Object>();
		m.put("pcardnumber",1);
		System.out.println(1);
//		System.out.println(new Gson().toJson(checkMapper.findGuideCheckByCard(m)));
//		return checkMapper.findGuideCheckByCard(m);
		return null;
	}

	@Override
	public Object reqGuideCheck(HashMap<String, Object> condition)
	{
		LayuiTableDate date = new LayuiTableDate();
		date.setData(checkMapper.findGuideCheckByCondition(condition));
		date.setCount(checkMapper.findGuideCheckCountByCondition(condition));
		return date;
	}

	@Override
	public Object reqCheckByCard(HashMap<String,Object> condition, RowBounds rowBounds)
	{
		LayuiTableDate date = new LayuiTableDate();
		date.setCount(checkMapper.findGuideCheckCountByCondition(condition));
		date.setData(checkMapper.findGuideCheckByCondition(condition,rowBounds));
		return date;
	}

	@Override
	public Object reqCheckById(HashMap<String, Object> condition)
	{
		LayuiTableDate date = new LayuiTableDate();
		date.setData(checkMapper.findGuideCheckByID(condition));
		return date;
	}

	@Override
	public int addBarcode(Map condition)
	{
		int i = 0;
		try
		{
			i = checkMapper.addBarcode(condition);
		}catch (Exception e){
			System.out.println("添加失败");
			System.out.println(e);
		}
		System.out.println(condition.get("iid"));

		return i;
	}
}
