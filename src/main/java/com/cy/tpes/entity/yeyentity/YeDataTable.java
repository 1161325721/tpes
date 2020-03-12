package com.cy.tpes.entity.yeyentity;

import java.util.List;

/**
 * @ClassName:YeDateTable
 * @Description: TTTT
 * @Date：2020/2/25 21:27
 * @Author ：Administrator
 **/

public class YeDataTable
{
	private int code;
	private String msg = "";
	private long count;
	private List<?> data;

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

	public long getCount()
	{
		return count;
	}

	public void setCount(long count)
	{
		this.count = count;
	}

	public List<?> getData()
	{
		return data;
	}

	public void setData(List<?> data)
	{
		this.data = data;
	}
}

