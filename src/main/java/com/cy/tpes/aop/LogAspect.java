package com.cy.tpes.aop;

import com.alibaba.fastjson.JSON;
import com.cy.tpes.entity.hwxbean.Log;
import com.cy.tpes.entity.hwxbean.Worker;
import com.cy.tpes.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * created on 2020/2/18
 *
 * @author:胡文贤
 *
 * 系统日志：切面处理类
 **/
@Aspect
@Component
public class LogAspect
{
	@Autowired
	private LogService logService;

//	定义切点 @PointCut
//	在注解的位置切入代码
	@Pointcut("@annotation(com.cy.tpes.aop.MyLog)")
	public void  logPoinCut(){

	}

//	切面 配置通知
	@AfterReturning(returning = "rvt",pointcut = "logPoinCut()")
	public void saveLog(JoinPoint joinPoint,Object rvt){
		System.out.println("切面");
//		保存日志
		Log log = new Log();
//		从切入点植入点通过反射机制获取植入点出的方法
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//		获取切入点所在的方法
		Method method = signature.getMethod();
//		获取操作
		MyLog myLog = method.getAnnotation(MyLog.class);
		if (myLog!=null){
			String value = myLog.value();
			//保存获取的操作
			log.setLtype(value);
		}

//		获取请求的类名
		String className = joinPoint.getTarget().getClass().getName();
//		获取请求的方法名
		String methodName = method.getName();
//		请求的参数
		Object[] args = joinPoint.getArgs();
//		将参数所在的数组转换成json
//		获取方法返回值
//		Object returnValue = joinPoint.proceed(args);

		//获取session
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true);
		Worker worker = (Worker) session.getAttribute("worker");
		log.setWid(worker.getWid());
		log.setLstate("0");
//		System.out.println(worker.getWid());
		String params = JSON.toJSONString(args);
		log.setLmessage(params);
//		System.out.println("params:" + params);
		System.out.println("目标方法返回值"+rvt);
		//返回成功：void 类型 返回值不等于0
		if(rvt != null && rvt.toString().length() != 0&&rvt.toString()!="0") {
			System.out.println("执行成功1");
		}else if(rvt==null) {
			System.out.println("执行成功2");
		}
		//
//		将参数填入日志中
//		log.setLmessage(params);
//      todo 		获取工作人员id wid
//		log.setWid();
//		todo 操作是否成功？ lstate

		System.out.println("log中的信息："+log.toString());
		System.out.println("方法名:"+methodName);
		System.out.println("类名:"+className);
		//todo 插入数据库
		System.out.println(log.getWid() +log.getLtype()+log.getLmessage());
		logService.addLog(log.getWid(),log.getLtype(), log.getLmessage(),log.getLstate());
	}
}
