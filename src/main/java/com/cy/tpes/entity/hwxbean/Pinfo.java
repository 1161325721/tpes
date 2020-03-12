package com.cy.tpes.entity.hwxbean;

/**
 * created on 2020/3/9
 *
 * @author:胡文贤
 * 打印报告用
 **/
public class Pinfo
{
	//单位名称
	private String gcname;
	//单位id
	private Long gcid;
	//导检单号id
	private Long gchid;
	//姓名
	private String pname;

	private int pgender;

	private Long pphone;


	public String getGcname()
	{
		return gcname;
	}

	public void setGcname(String gcname)
	{
		this.gcname = gcname;
	}

	public Long getGcid()
	{
		return gcid;
	}

	public void setGcid(Long gcid)
	{
		this.gcid = gcid;
	}

	public Long getGchid()
	{
		return gchid;
	}

	public void setGchid(Long gchid)
	{
		this.gchid = gchid;
	}

	public String getPname()
	{
		return pname;
	}

	public void setPname(String pname)
	{
		this.pname = pname;
	}

	public int getPgender()
	{
		return pgender;
	}

	public void setPgender(int pgender)
	{
		this.pgender = pgender;
	}

	public Long getPphone()
	{
		return pphone;
	}

	public void setPphone(Long pphone)
	{
		this.pphone = pphone;
	}
}
