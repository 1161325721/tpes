package com.cy.tpes.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface LogMapper
{
	public int addLog(@Param("wid") long wid,@Param("ltype") String ltype,@Param("lmessage") String lmessage,@Param("lstate") String lstate);

}
