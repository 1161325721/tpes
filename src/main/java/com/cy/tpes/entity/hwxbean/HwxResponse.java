package com.cy.tpes.entity.hwxbean;

/**
 * created on 2020/2/24
 *
 * @author:胡文贤
 *
 * 上传文件响应信息response
 **/
public class HwxResponse
{
	private int code;
	private String msg;
	private String data;

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

	public String getData()
	{
		return data;
	}

	public void setData(String data)
	{
		this.data = data;
	}
}
