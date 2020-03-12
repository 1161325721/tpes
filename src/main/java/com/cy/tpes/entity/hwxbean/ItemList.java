package com.cy.tpes.entity.hwxbean;

/**
 * created on 2020/2/20
 *
 * @author:胡文贤
 *
 * 项目列表
 **/
public class ItemList
{

	//checkitem所有信息
	private long ciid;
	private long cpid;
	private long iid;
	private double cicheckvalue;
	private String cicompareresult;
	private long wid;
	private String cidate;
	//项目报告部分信息
	private String sumType;
	private String pic;
	private String cpdate;
	private String cpsummary;
	private String range;
	//item所有信息
	private String iname;
	private String iunit;
	private double imax;
	private double imin;
	//医生姓名
	private String wname;
	//所属科室
	private String dname;
	private String proname;

	//报告 总结 建议 生活知道
	private String crlifeguide ;
	private String crsuggest;
	private String crsummary;
	private String workid;
	private String crdate;


	//是否在有效期内
	private String timestate;
	//是否完成所有项目
	private String receitem;


	public long getCiid() {
		return ciid;
	}

	public void setCiid(long ciid) {
		this.ciid = ciid;
	}

	public long getCpid() {
		return cpid;
	}

	public void setCpid(long cpid) {
		this.cpid = cpid;
	}

	public long getIid() {
		return iid;
	}

	public void setIid(long iid) {
		this.iid = iid;
	}

	public double getCicheckvalue() {
		return cicheckvalue;
	}

	public void setCicheckvalue(double cicheckvalue) {
		this.cicheckvalue = cicheckvalue;
	}

	public String getCicompareresult() {
		return cicompareresult;
	}

	public void setCicompareresult(String cicompareresult) {
		this.cicompareresult = cicompareresult;
	}

	public long getWid() {
		return wid;
	}

	public void setWid(long wid) {
		this.wid = wid;
	}

	public String getCidate() {
		return cidate;
	}

	public void setCidate(String cidate) {
		this.cidate = cidate;
	}

	public String getSumType() {
		return sumType;
	}

	public void setSumType(String sumType) {
		this.sumType = sumType;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getCpdate() {
		return cpdate;
	}

	public void setCpdate(String cpdate) {
		this.cpdate = cpdate;
	}

	public String getCpsummary() {
		return cpsummary;
	}

	public void setCpsummary(String cpsummary) {
		this.cpsummary = cpsummary;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getIname() {
		return iname;
	}

	public void setIname(String iname) {
		this.iname = iname;
	}

	public String getIunit() {
		return iunit;
	}

	public void setIunit(String iunit) {
		this.iunit = iunit;
	}

	public double getImax() {
		return imax;
	}

	public void setImax(double imax) {
		this.imax = imax;
	}

	public double getImin() {
		return imin;
	}

	public void setImin(double imin) {
		this.imin = imin;
	}

	public String getWname() {
		return wname;
	}

	public void setWname(String wname) {
		this.wname = wname;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getProname() {
		return proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	public String getCrlifeguide() {
		return crlifeguide;
	}

	public void setCrlifeguide(String crlifeguide) {
		this.crlifeguide = crlifeguide;
	}

	public String getCrsuggest() {
		return crsuggest;
	}

	public void setCrsuggest(String crsuggest) {
		this.crsuggest = crsuggest;
	}

	public String getCrsummary() {
		return crsummary;
	}

	public void setCrsummary(String crsummary) {
		this.crsummary = crsummary;
	}

	public String getWorkid() {
		return workid;
	}

	public void setWorkid(String workid) {
		this.workid = workid;
	}

	public String getCrdate() {
		return crdate;
	}

	public void setCrdate(String crdate) {
		this.crdate = crdate;
	}

	public String getTimestate() {
		return timestate;
	}

	public void setTimestate(String timestate) {
		this.timestate = timestate;
	}

	public String getReceitem() {
		return receitem;
	}

	public void setReceitem(String receitem) {
		this.receitem = receitem;
	}
}
