package com.cy.tpes.controller.sysmgr;


import com.baidu.aip.face.AipFace;
import com.baidu.aip.util.Base64Util;
import com.cy.tpes.entity.hwxbean.Menu;
import com.cy.tpes.entity.hwxbean.Worker;
import com.cy.tpes.entity.sysmgr.LayuiTableDate;
import com.cy.tpes.entity.sysmgr.RespDate;
import com.cy.tpes.service.hwxservice.CommonService;
import com.cy.tpes.service.sysmgr.CheckService;
import com.cy.tpes.service.sysmgr.FrontIndexService;
import com.cy.tpes.service.sysmgr.SysmgrService;
import com.google.gson.Gson;
import org.apache.ibatis.session.RowBounds;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/front/")
public class FrontIndexController
{

	@Resource
	private FrontIndexService frontIndexService;
	@Resource
	private SysmgrService sysmgrService;
	@Resource
	private CheckService checkService;
	@Resource
	private CommonService commonService;


	/**
	 * @author: JX190728
	 * @methodName: faceLogin
	 * @description: 人脸识别登录
	 * @param img
	 * @param request
	 * @return: java.lang.Object
	 */
	@RequestMapping(path = "faceLogin",produces = "application/json")
	@ResponseBody
	public Object faceLogin(String img,HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Object ob = frontIndexService.imageSearch(img);
		if (null!=ob){
			HashMap<String,Object> condition = new HashMap<>();
			condition.put("wid",ob);
			LayuiTableDate d = sysmgrService.reqWorkerListByCondition(condition);
			if (d.getCount()==0){
				return false;
			}
			Gson g = new Gson();
			Worker worker = g.fromJson(g.toJson(d.getData().get(0)),Worker.class);
			System.out.println(g.toJson(worker));
			session.setAttribute("worker", worker);
			session.setAttribute("wid", worker.getWid());
			session.setAttribute("wname", worker.getWname());
			session.setAttribute("rname", worker.getRname());
			session.setAttribute("dname", worker.getDname());
			session.setAttribute("waccount", worker.getWaccount());
			long rid = worker.getRid();
			List<Menu> menuList = new ArrayList<Menu>();
			menuList = commonService.roleMenu(rid);
			HashMap<String,ArrayList<Menu>> map = new HashMap<>();
			for (int i = 0; i <menuList.size() ; i++)
			{
				System.out.println("firse"+menuList.get(i).getMparentid());
				System.out.println("second"+menuList.get(i).getMid());
				System.out.println("path"+menuList.get(i).getMname());
			}
			for (int i = 0; i < menuList.size(); i++)
			{
				//从集合中获取元素
				Menu item= menuList.get(i);
				//判断这个名字是否存在于一级菜单
				if (map.containsKey(item.getFirstname()))
				{
					map.get(item.getFirstname()).add(item);
				}else{
					//不存在
					ArrayList<Menu>li1 = new ArrayList<>();
					//加到一级菜单中
					li1.add(item);
					//加到map里f
					map.put(item.getFirstname(),li1);
				}
			}
			//把菜单存到session中
			session.setAttribute("menuMap",map);
			return true;
		}
		return false;
	}


	@RequestMapping(path = "faceEdit",produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public Object faceEdit(@RequestParam("file") MultipartFile file, String wid,HttpServletRequest request){
		RespDate respDate = new RespDate();
		try
		{
			byte[] bytes = file.getBytes();
			String imageEncode  = Base64Util.encode(bytes);
			//判断照片是否符合规范
			if (!frontIndexService.imageDetect(imageEncode)){
				respDate.setCode(0);
				respDate.setMsg("图片不符合规范");
				System.out.println("图片不符合规范");
				return respDate;
			}
			//判断是否在云端存在该用户，如果有则更新，否则新增
			if (frontIndexService.findUser(wid)){
				System.out.println("云端已有用户记录，跟新，用户Id:"+wid);
				frontIndexService.updateUser(wid,imageEncode);
				respDate.setCode(1);
				return respDate;
			}
			//新增用户
			System.out.println("云端无记录，新增，用户Id:"+wid);
			frontIndexService.addface(imageEncode,wid);
			respDate.setCode(1);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return respDate;
	}

	@RequestMapping(path = "index")
	public ModelAndView toPage(String gcid, HttpServletRequest request){




		HashMap<String,Object> map = new HashMap<>();
		map.put("wcount",sysmgrService.reqWorkerListByCondition(null).getCount());
		map.put("dcount",sysmgrService.reqWorkerListByCondition(null).getCount());
		map.put("chcount",((LayuiTableDate)checkService.reqGuideCheck(null)).getCount());
		map.put("gccount",sysmgrService.reqGroupListByCondition(null).getCount());

		request.setAttribute("info",map);



		System.out.println(new Gson().toJson(map));



		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/front_index");
		return modelAndView;
	}


}
