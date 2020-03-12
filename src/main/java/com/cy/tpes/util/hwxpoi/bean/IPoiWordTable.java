package com.cy.tpes.util.hwxpoi.bean;

import java.io.Serializable;

public interface IPoiWordTable extends Serializable
{
	/**
	 * 设置表格内容
	 */
	void setCell(int row, int col, String text);

	/**
	 * 读取表格内容
	 */
	String getCell(int row, int col);

	/** 读取表格行数 */
	int getRows();

	/** 读取表格列数 */
	int getCols();
}

