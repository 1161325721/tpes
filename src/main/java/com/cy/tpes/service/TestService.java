package com.cy.tpes.service;

import com.cy.tpes.aop.MyLog;
import com.cy.tpes.entity.hwxbean.Worker;
import com.cy.tpes.mapper.LogMapper;
import com.cy.tpes.mapper.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService
{

	@Resource
	private TestDao testDao;
	@Autowired
	private LogMapper logMapper;
	public List test(){
		return testDao.test();
	}

	@MyLog(value = "测试")
	public Object test2(String name){

		return new Worker();
//		return logMapper.addLog2(1);
	}


}
