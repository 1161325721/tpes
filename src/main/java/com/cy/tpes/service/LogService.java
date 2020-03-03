package com.cy.tpes.service;

import com.cy.tpes.mapper.HwxLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * created on 2020/2/18
 *
 * @author:胡文贤
 *
 *
 * 日志记录
 **/
@Service
public class LogService
{
	@Autowired
	private HwxLogMapper hwxLogMapper;

	@Transactional
	public int addLog( long wid, String ltype,String lmessage,String lstate){return hwxLogMapper.addLog(wid,ltype,lmessage,lstate);};

}
