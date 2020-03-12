package com.cy.tpes.controller.hwxcontroller;

import com.cy.tpes.service.hwxservice.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController
{

	@Resource
	private TestService testService;


	//测试地址：http://localhost:8300/tpes/test


	@RequestMapping(path = "test")
	public String test(HttpServletRequest request){
//		request.setAttribute("list",testService.test());
//		return "index";
		String name = "胡文贤";
		testService.test2(name);
		return "hwx_backlogin";



	}
	@RequestMapping(path = "doctor")
	public String doctor(HttpServletRequest request){
		//		request.setAttribute("list",testService.test());
		//		return "index";
		return "hwx_doctor";
	}

	@RequestMapping(path = "director")
	public String director(HttpServletRequest request){
		//		request.setAttribute("list",testService.test());
		//		return "index";
		return "hwx_director";
	}

	@RequestMapping(path = "writesum")
	public String writesum(HttpServletRequest request){
		//		request.setAttribute("list",testService.test());
		//		return "index";
		return "hwx_writesum";
	}

	@RequestMapping(path = "admin")
	public String goadmin(HttpServletRequest request){
		//		request.setAttribute("list",testService.test());
		//		return "index";
		return "hwx_admin";
	}

}
