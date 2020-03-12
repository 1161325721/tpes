package com.cy.tpes.util;

/**
 * created on 2020/2/18
 *
 * @author:胡文贤
 *
 * 登录成功后，获取用户信息，用于日志中获取用户id
 **/
public class WokerUtil
{
	private static int wid =0;

	public int getWid()
	{
		return wid;
	}

	public void setWid(int wid)
	{
		this.wid = wid;
	}
}
