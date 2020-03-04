package com.cy.tpes.entity.sysmgr;

import java.util.List;

public class RespDate
{
	private int code;
	private String msg;
	private List<Object> data;

	public RespDate()
	{
	}

	public RespDate(int code, String msg, List<Object> data)
	{
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

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
		return "RespDate{" + "code=" + code + ", msg='" + msg + '\'' + ", data=" + data + '}';
	}
}
