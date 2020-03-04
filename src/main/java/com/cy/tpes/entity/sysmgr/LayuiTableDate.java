package com.cy.tpes.entity.sysmgr;

import java.util.List;

/**
 * @program: 20191223
 * @ClassName: LayuiTableDate
 * @description: layui动态表格返回的数据
 * @author: JX190728
 * @create: 2019-12-24 10:25
 **/
public class LayuiTableDate
{
	private int code;
	private String msg;
	private int count;
	private List<Object>data;

	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public List<Object> getData()
	{
		return data;
	}

	public void setData(List<Object> data)
	{
		this.data = data;
	}

	@Override
	public String toString()
	{
		return "LayuiTableDate{" + "code=" + code + ", msg='" + msg + '\'' + ", count=" + count + ", data=" + data + '}';
	}
}
