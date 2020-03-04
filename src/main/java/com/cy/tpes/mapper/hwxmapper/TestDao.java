package com.cy.tpes.mapper.hwxmapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestDao
{
	public List<Object> test();


}
