package com.cy.tpes.entity.hwxbean;

/**
 * created on 2020/2/27
 * 总检医生查看的信息
 * @author:胡文贤
 **/
public class GcList
{
	//导检单id
	private Long gcid;
	//订单预约时间 timestamp类型要截取
	private String goappointdate;
	//订单有效期
	private String golasttime;
	//是否完成所有的项目 0没有全部完成  1完成全部 套餐完成状态
	private String packState;
	//报告id
	private Long crid;
	//套餐名
	private String packname;
	//有效期范围 日期形式
	private String timeRange;
	//订单状态
	private String gostate;
	private String gcstate;
	//体检人姓名
	private String pname;
	//项目接收状态
	private Long cpstate;

	private String crlifeguide ;
	private String crsuggest ;
	private Long workid ;
	private String crdate;
	private String crsummary;


	public String getCrlifeguide()
	{
		return crlifeguide;
	}

	public void setCrlifeguide(String crlifeguide)
	{
		this.crlifeguide = crlifeguide;
	}

	public String getCrsuggest()
	{
		return crsuggest;
	}

	public void setCrsuggest(String crsuggest)
	{
		this.crsuggest = crsuggest;
	}

	public Long getWorkid()
	{
		return workid;
	}

	public void setWorkid(Long workid)
	{
		this.workid = workid;
	}

	public String getCrdate()
	{
		return crdate;
	}

	public void setCrdate(String crdate)
	{
		this.crdate = crdate;
	}

	public String getCrsummary()
	{
		return crsummary;
	}

	public void setCrsummary(String crsummary)
	{
		this.crsummary = crsummary;
	}

	public Long getCpstate()
	{
		return cpstate;
	}

	public void setCpstate(Long cpstate)
	{
		this.cpstate = cpstate;
	}

	public String getPname()
	{
		return pname;
	}

	public void setPname(String pname)
	{
		this.pname = pname;
	}

	public String getGcstate()
	{
		return gcstate;
	}

	public void setGcstate(String gcstate)
	{
		this.gcstate = gcstate;
	}

	public String getGostate()
	{
		return gostate;
	}

	public void setGostate(String gostate)
	{
		this.gostate = gostate;
	}

	public Long getGcid()
	{
		return gcid;
	}

	public void setGcid(Long gcid)
	{
		this.gcid = gcid;
	}

	public String getGoappointdate()
	{
		return goappointdate;
	}

	public void setGoappointdate(String goappointdate)
	{
		this.goappointdate = goappointdate;
	}

	public String getGolasttime()
	{
		return golasttime;
	}

	public void setGolasttime(String golasttime)
	{
		this.golasttime = golasttime;
	}

	public String getPackState()
	{
		return packState;
	}

	public void setPackState(String packState)
	{
		this.packState = packState;
	}

	public Long getCrid()
	{
		return crid;
	}

	public void setCrid(Long crid)
	{
		this.crid = crid;
	}

	public String getPackname()
	{
		return packname;
	}

	public void setPackname(String packname)
	{
		this.packname = packname;
	}

	public String getTimeRange()
	{
		return timeRange;
	}

	public void setTimeRange(String timeRange)
	{
		this.timeRange = timeRange;
	}
}
