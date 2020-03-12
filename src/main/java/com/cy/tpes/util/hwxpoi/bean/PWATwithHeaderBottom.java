package com.cy.tpes.util.hwxpoi.bean;

/**
 * created on 2020/3/9
 *
 * @author:胡文贤
 **/
public class PWATwithHeaderBottom extends PoiWordAutoTable
{
	private static final long serialVersionUID = 6775968049120051793L;

	/* 表格上方 段落内容 */
	private String title = "";
	/* 表格下方 段落内容 */
	private String bottom = "";

	public PWATwithHeaderBottom(int rows, int cols){
		super(rows, cols);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBottom() {
		return bottom;
	}

	public void setBottom(String bottom) {
		this.bottom = bottom;
	}

}
