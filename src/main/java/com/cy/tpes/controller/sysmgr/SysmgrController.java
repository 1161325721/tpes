package com.cy.tpes.controller.sysmgr;

import com.cy.tpes.entity.sysmgr.RespDate;
import com.cy.tpes.service.sysmgr.SysmgrService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: tpes
 * @ClassName: MgrController
 * @description: 管理工作站-系统管理业务
 * @author: JX190728
 * @create: 2020-02-16 09:13
 **/

@Controller
@RequestMapping("/sysmgr/")
public class SysmgrController
{

	@Resource
	private SysmgrService sysmgrService;


	@RequestMapping(path = "index")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/admin");


		HashMap<String,List> menu = new HashMap<>();

		List l = sysmgrService.reqRoleMenuBar(null);
		for (int i = 0; i < l.size(); i++)
		{
			Map m = (Map) l.get(i);
			String fname = (String) m.get("fname");

			if (null != menu.get(fname)){
				menu.get(fname).add(m);
			}else {
				ArrayList list = new ArrayList();
				list.add(m);
				menu.put(fname,list);
			}

		}

		System.out.println(menu);
		request.getSession().setAttribute("menuMap",menu);


		return modelAndView;
	}


	@RequestMapping(path = "page/{pageName}")
	public ModelAndView toPage(@PathVariable("pageName") String pageName){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/"+pageName);
		return modelAndView;
	}

	@RequestMapping(path = "imageUpload",produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public Object imageUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request){
		System.out.println(file.getOriginalFilename());
		RespDate respDate = new RespDate();
		try{
			String path = request.getServletContext().getRealPath("")+"resources/images/";
			String time = new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date());
			String filename = file.getOriginalFilename();
			String filetype = filename.substring(filename.lastIndexOf("."));
			String imgPath = path + time + filetype;
			System.out.println(imgPath);
			file.transferTo(new File(imgPath));
			respDate.setMsg("/resources/images/"+time+filetype);
			respDate.setCode(1);
		}catch(Exception e){
			respDate.setCode(0);
		}
		return respDate;
	}


	@RequestMapping(path = "reqGroupList",produces = "application/json")
	@ResponseBody
	public Object reqGroupList(int page,
	                           int limit,
	                           @RequestParam(value = "condition",required=false) HashMap<String,Object> condition)
	{

		RowBounds rowBounds = new RowBounds((page-1)*limit,limit);
		return sysmgrService.reqGroupListByCondition(condition,rowBounds);
	}

	@RequestMapping(path = "editGroup",produces = "application/json")
	@ResponseBody
	public Object editGroup(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition){
		sysmgrService.editGroup(condition);
		return "true";
	}

	@RequestMapping(path = "reqItemList",produces = "application/json")
	@ResponseBody
	public Object reqItemList(int page,
	                           int limit,
	                           @RequestParam(value = "condition",required=false) HashMap<String,Object> condition)
	{

		RowBounds rowBounds = new RowBounds((page-1)*limit,limit);
		return sysmgrService.reqItemListByCondition(condition,rowBounds);
	}

	@RequestMapping(path = "reqAllItemList",produces = "application/json")
	@ResponseBody
	public Object reqAllItemList(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition)
	{
		return sysmgrService.reqItemListByCondition(condition);
	}

	@RequestMapping(path = "editItem",produces = "application/json")
	@ResponseBody
	public Object editItem(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition){
		sysmgrService.editItem(condition);
		return "true";
	}

	@RequestMapping(path = "addItem",produces = "application/json")
	@ResponseBody
	public Object addItem(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition){
		String msg = "false";
		if (sysmgrService.addItem(condition)>0){
			msg = "true";
		}
		return msg;
	}

	@RequestMapping(path = "delItem",produces = "application/json")
	@ResponseBody
	public Object delItem(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition){

		return sysmgrService.delItem(condition);
	}





	@RequestMapping(path = "reqProjectList",produces = "application/json")
	@ResponseBody
	public Object reqProjectList(int page,
	                          int limit,
	                          @RequestParam(value = "condition",required=false) HashMap<String,Object> condition)
	{

		RowBounds rowBounds = new RowBounds((page-1)*limit,limit);
		return sysmgrService.reqProjectListByCondition(condition,rowBounds);
	}

	@RequestMapping(path = "reqAllProjectList",produces = "application/json")
	@ResponseBody
	public Object reqAllProjectList(
	                             @RequestParam(value = "condition",required=false) HashMap<String,Object> condition)
	{
		return sysmgrService.reqProjectListByCondition(condition);
	}

	@RequestMapping(path = "addProject",produces = "application/json")
	@ResponseBody
	public Object addProject(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition){
		String msg = "false";
		if (sysmgrService.addProject(condition)>0){
			msg = "true";
		}
		return msg;
	}

	@RequestMapping(path = "editProject",produces = "application/json")
	@ResponseBody
	public Object editProject(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition){
		sysmgrService.editProject(condition);
		return "true";
	}

	@RequestMapping(path = "delProject",produces = "application/json")
	@ResponseBody
	public Object delProject(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition){

		return sysmgrService.delProject(condition);
	}

	@RequestMapping(path = "reqProjectItemList",produces = "application/json")
	@ResponseBody
	public Object reqPrrojectItemList(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition)
	{
		return sysmgrService.reqProjectItem(condition);
	}

	@RequestMapping(path = "editProjectItem",produces = "application/json")
	@ResponseBody
	public Object editProjectItem(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition){
		String msg = "false";
		if(sysmgrService.editProjectItem(condition)>0){
			msg="true";
		}
		return msg;
	}


	@RequestMapping(path = "reqPackageList",produces = "application/json")
	@ResponseBody
	public Object reqPackageList(int page,
	                             int limit,
	                             @RequestParam(value = "condition",required=false) HashMap<String,Object> condition)
	{

		RowBounds rowBounds = new RowBounds((page-1)*limit,limit);
		return sysmgrService.reqPackageListByCondition(condition,rowBounds);
	}

	@RequestMapping(path = "addPackage",produces = "application/json")
	@ResponseBody
	public Object addPackage(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition){
		String msg = "false";
		if (sysmgrService.addPackage(condition) > 0){
			msg = "true";
		}
		return msg;
	}

	@RequestMapping(path = "editPackage",produces = "application/json")
	@ResponseBody
	public Object editPackage(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition){
		String msg = "false";
		if (sysmgrService.editPackage(condition) > 0){
			msg = "true";
		}
		return msg;
	}

	@RequestMapping(path = "delPackage",produces = "application/json")
	@ResponseBody
	public Object delPackage(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition){
		String msg = "false";
		if (sysmgrService.delPackage(condition) > 0){
			msg = "true";
		}
		return msg;
	}

	@RequestMapping(path = "reqPackageProjectList",produces = "application/json")
	@ResponseBody
	public Object reqPackageProjectList(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition)
	{
		return sysmgrService.reqPackageProject(condition);
	}

	@RequestMapping(path = "editPackageProject",produces = "application/json")
	@ResponseBody
	public Object editPackageProject(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition){
		String msg = "false";
		if(sysmgrService.editPackageProject(condition)>0){
			msg="true";
		}
		return msg;
	}



	@RequestMapping(path = "reqMenuList",produces = "application/json")
	@ResponseBody
	public Object reqMenuList(int page,
	                             int limit,
	                             @RequestParam(value = "condition",required=false) HashMap<String,Object> condition)
	{

		RowBounds rowBounds = new RowBounds((page-1)*limit,limit);
		return sysmgrService.reqMenuListByCondition(condition,rowBounds);
	}

	@RequestMapping(path = "reqAllMenuList",produces = "application/json")
	@ResponseBody
	public Object reqAllMenuList(
			@RequestParam(value = "condition",required=false) HashMap<String,Object> condition)
	{
		return sysmgrService.reqMenuListByCondition(condition);
	}


	@RequestMapping(path = "addMenu",produces = "application/json")
	@ResponseBody
	public Object addMenu(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition){
		String msg = "false";
		if (sysmgrService.addMenu(condition) > 0){
			msg = "true";
		}
		return msg;
	}

	@RequestMapping(path = "editMenu",produces = "application/json")
	@ResponseBody
	public Object editMenu(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition){
		String msg = "false";
		if (sysmgrService.editMenu(condition) > 0){
			msg = "true";
		}
		return msg;
	}

	@RequestMapping(path = "delMenu",produces = "application/json")
	@ResponseBody
	public Object delMenu(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition){
		String msg = "false";
		if (sysmgrService.delMenu(condition) > 0){
			msg = "true";
		}
		return msg;
	}


	@RequestMapping(path = "reqRoleList",produces = "application/json")
	@ResponseBody
	public Object reqRoleList(int page,
	                             int limit,
	                             @RequestParam(value = "condition",required=false) HashMap<String,Object> condition)
	{

		RowBounds rowBounds = new RowBounds((page-1)*limit,limit);
		return sysmgrService.reqRoleListByCondition(condition,rowBounds);
	}

	@RequestMapping(path = "reqAllRoleList",produces = "application/json")
	@ResponseBody
	public Object reqAllRoleList(
	                          @RequestParam(value = "condition",required=false) HashMap<String,Object> condition)
	{

		return sysmgrService.reqRoleListByCondition(condition);
	}

	@RequestMapping(path = "addRole",produces = "application/json")
	@ResponseBody
	public Object addRole(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition){
		String msg = "false";
		if (sysmgrService.addRole(condition) > 0){
			msg = "true";
		}
		return msg;
	}

	@RequestMapping(path = "editRole",produces = "application/json")
	@ResponseBody
	public Object editRole(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition){
		String msg = "false";
		if (sysmgrService.editRole(condition) > 0){
			msg = "true";
		}
		return msg;
	}

	@RequestMapping(path = "delRole",produces = "application/json")
	@ResponseBody
	public Object delRole(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition){
		String msg = "false";
		if (sysmgrService.delRole(condition) > 0){
			msg = "true";
		}
		return msg;
	}

	@RequestMapping(path = "reqRoleMenu",produces = "application/json")
	@ResponseBody
	public Object reqRoleMenu(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition)
	{
		return sysmgrService.reqRoleMenu(condition);
	}

	@RequestMapping(path = "editRoleMenu",produces = "application/json")
	@ResponseBody
	public Object editRoleMenu(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition){
		String msg = "false";
		if(sysmgrService.editRoleMenu(condition)>0){
			msg="true";
		}
		return msg;
	}


	@RequestMapping(path = "reqWorkerList",produces = "application/json")
	@ResponseBody
	public Object reqWorkerList(int page,
	                          int limit,
	                          @RequestParam(value = "condition",required=false) HashMap<String,Object> condition)
	{

		RowBounds rowBounds = new RowBounds((page-1)*limit,limit);
		return sysmgrService.reqWorkerListByCondition(condition,rowBounds);
	}

	@RequestMapping(path = "addWorker",produces = "application/json")
	@ResponseBody
	public Object addWorker(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition){
		String msg = "false";
		if (sysmgrService.addWorker(condition) > 0){
			msg = "true";
		}
		return msg;
	}

	@RequestMapping(path = "editWorker",produces = "application/json")
	@ResponseBody
	public Object editWorker(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition){
		String msg = "false";
		if (sysmgrService.editWorker(condition) > 0){
			msg = "true";
		}
		return msg;
	}

	@RequestMapping(path = "delWorker",produces = "application/json")
	@ResponseBody
	public Object delWorker(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition){
		String msg = "false";
		if (sysmgrService.delWorker(condition) > 0){
			msg = "true";
		}
		return msg;
	}


	@RequestMapping(path = "reqAllDepartmentList",produces = "application/json")
	@ResponseBody
	public Object reqAllDepartmentList()
	{
		return sysmgrService.reqAllDepartmentList();
	}

	@RequestMapping(path = "reqDepartmentList",produces = "application/json")
	@ResponseBody
	public Object reqDepartmentList(int page,
	                          int limit,
	                          @RequestParam(value = "condition",required=false) HashMap<String,Object> condition)
	{

		RowBounds rowBounds = new RowBounds((page-1)*limit,limit);
		return sysmgrService.reqDepartmentListByCondition(condition,rowBounds);
	}

	@RequestMapping(path = "addDepartment",produces = "application/json")
	@ResponseBody
	public Object addDepartment(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition){
		String msg = "false";
		if (sysmgrService.addDepartment(condition) > 0){
			msg = "true";
		}
		return msg;
	}

	@RequestMapping(path = "editDepartment",produces = "application/json")
	@ResponseBody
	public Object editDepartment(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition){
		String msg = "false";
		if (sysmgrService.editDepartment(condition) > 0){
			msg = "true";
		}
		return msg;
	}

	@RequestMapping(path = "delDepartment",produces = "application/json")
	@ResponseBody
	public Object delDepartment(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition){
		String msg = "false";
		if (sysmgrService.delDepartment(condition) > 0){
			msg = "true";
		}
		return msg;
	}


	@RequestMapping(path = "reqAllParameterList",produces = "application/json")
	@ResponseBody
	public Object reqAllParameterList(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition)
	{
		return sysmgrService.reqParameterListByCondition(condition);
	}

	@RequestMapping(path = "reqParameterList",produces = "application/json")
	@ResponseBody
	public Object reqParameterList(int page,
	                                int limit,
	                                @RequestParam(value = "condition",required=false) HashMap<String,Object> condition)
	{

		RowBounds rowBounds = new RowBounds((page-1)*limit,limit);
		return sysmgrService.reqParameterListByCondition(condition,rowBounds);
	}

	@RequestMapping(path = "addParameter",produces = "application/json")
	@ResponseBody
	public Object addParameter(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition){
		String msg = "false";
		if (sysmgrService.addParameter(condition) > 0){
			msg = "true";
		}
		return msg;
	}

	@RequestMapping(path = "editParameter",produces = "application/json")
	@ResponseBody
	public Object editParameter(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition){
		String msg = "false";
		if (sysmgrService.editParameter(condition) > 0){
			msg = "true";
		}
		return msg;
	}

	@RequestMapping(path = "delParameter",produces = "application/json")
	@ResponseBody
	public Object delParameter(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition){
		String msg = "false";
		if (sysmgrService.delParameter(condition) > 0){
			msg = "true";
		}
		return msg;
	}


	@RequestMapping(path = "reqLogList",produces = "application/json")
	@ResponseBody
	public Object reqLogList(int page,
	                               int limit,
	                               @RequestParam(value = "condition",required=false) HashMap<String,Object> condition)
	{

		RowBounds rowBounds = new RowBounds((page-1)*limit,limit);
		return sysmgrService.reqLogListByCondition(condition,rowBounds);
	}

	@RequestMapping(path = "delLog",produces = "application/json")
	@ResponseBody
	public Object delLog(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition){
		String msg = "false";
		if (sysmgrService.delLog(condition) > 0){
			msg = "true";
		}
		return msg;
	}

}
