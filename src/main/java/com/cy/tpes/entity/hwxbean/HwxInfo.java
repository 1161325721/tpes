package com.cy.tpes.entity.hwxbean;

/**
 * created on 2020/3/5
 *
 * @author:胡文贤
 **/
public class HwxInfo
{
	private String name;
	private int record;


	public HwxInfo(String name, int record)
	{
		super();
		this.name = name;
		this.record = record;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getRecord()
	{
		return record;
	}

	public void setRecord(int record)
	{
		this.record = record;
	}
}
