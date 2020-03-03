package com.cy.tpes.entity.hwxbean;

/**
 * created on 2020/2/20
 *
 * @author:胡文贤
 *
 * 病人的项目列表
 **/
public class ProjectList
{
	//体检项目与小结id
	private long cpid;
	private long proid;
	private String pname;
	private int cpstate;
	private int did;
	private String dname;
	private int sum_state;
	private String gostate;
	private String sum_type;
	private String wname;
	private String cpsummary;
	private Long gcid;

	public Long getGcid()
	{
		return gcid;
	}

	public void setGcid(Long gcid)
	{
		this.gcid = gcid;
	}

	public String getCpsummary()
	{
		return cpsummary;
	}

	public void setCpsummary(String cpsummary)
	{
		this.cpsummary = cpsummary;
	}

	public String getWname()
	{
		return wname;
	}

	public void setWname(String wname)
	{
		this.wname = wname;
	}

	public String getSum_type()
	{
		return sum_type;
	}

	public void setSum_type(String sum_type)
	{
		this.sum_type = sum_type;
	}

	public long getCpid()
	{
		return cpid;
	}

	public void setCpid(long cpid)
	{
		this.cpid = cpid;
	}

	public long getProid()
	{
		return proid;
	}

	public void setProid(long proid)
	{
		this.proid = proid;
	}

	public String getPname()
	{
		return pname;
	}

	public void setPname(String pname)
	{
		this.pname = pname;
	}

	public int getCpstate()
	{
		return cpstate;
	}

	public void setCpstate(int cpstate)
	{
		this.cpstate = cpstate;
	}

	public int getDid()
	{
		return did;
	}

	public void setDid(int did)
	{
		this.did = did;
	}

	public String getDname()
	{
		return dname;
	}

	public void setDname(String dname)
	{
		this.dname = dname;
	}

	public int getSum_state()
	{
		return sum_state;
	}

	public void setSum_state(int sum_state)
	{
		this.sum_state = sum_state;
	}

	public String getGostate()
	{
		return gostate;
	}

	public void setGostate(String gostate)
	{
		this.gostate = gostate;
	}
}
