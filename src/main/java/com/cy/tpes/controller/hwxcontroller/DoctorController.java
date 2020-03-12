package com.cy.tpes.controller.hwxcontroller;

import com.alibaba.fastjson.JSON;
import com.cy.tpes.entity.hwxbean.*;
import com.cy.tpes.entity.yeyentity.YeFileUtils1;
import com.cy.tpes.service.hwxservice.HwxDoctorService;
import com.cy.tpes.util.HwxFileUtils;
import com.cy.tpes.util.hwxpoi.PoiDemoMain;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.Line;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Array;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * created on 2020/2/20
 *
 * 科室医生相关业务
 * @author:胡文贤
 **/
@Controller
public class DoctorController
{


	@Autowired
	private HwxDoctorService doctorService;
	//跳转到项目接收页面
	@RequestMapping(path = "/receitem")
	public String test(HttpServletRequest request){
		return "hwx_receitem";
	}

	//获取病人的信息
	@RequestMapping(path = "/getPatientInfo")
	@ResponseBody
	public String getPatientInfo(HttpServletRequest request){

		//获取病人的卡号
		String pcardnumber = request.getParameter("pcardnumber");
		//获取当前页数
		int offset = Integer.valueOf(request.getParameter("page"));
		int limit = Integer.valueOf(request.getParameter("limit"));
		offset = (offset - 1) * limit ;

//		System.out.println("查询病人信息：  病人卡号：" + pcardnumber);
//		System.out.println("当前页数： " + offset);
//		System.out.println("限制条数： " + limit);
		//查到数据
		Patient patient = doctorService.patientInfo(pcardnumber);
		DataGrid data = new DataGrid();
		if (patient != null)
		{
			//存入session
			request.getSession().setAttribute("pcardnumber",pcardnumber);
			//打到layui数据对象中
			List<Patient>  list1 = new ArrayList<Patient>();
			//将病人信息添加进去
			list1.add(patient);
			int count = list1.size();
			//病人数量
			data.setCount(count);
			data.setData(list1);
			//		打包成session 返回回去
			String jsonstr = JSON.toJSONString(data);
			return jsonstr;
		}else {
				//没有数据
			String jsonstr = JSON.toJSONString(data);
			return jsonstr;
		}


	}

	//获取病人的项目列表
	@RequestMapping(path = "/getProjectList")
	@ResponseBody
	public String getProjectList(HttpServletRequest request){
		//获取病人的卡号
		String pcardnumber = request.getParameter("pcardnumber");
		int offset = Integer.valueOf(request.getParameter("page"));
		int limit = Integer.valueOf(request.getParameter("limit"));
		offset = (offset - 1) * limit ;

		Worker worker = (Worker) request.getSession().getAttribute("worker");
//		System.out.println("卡号：" + pcardnumber + "wid :" + worker.getWid());
//		System.out.println("当前页数" + offset);
//		System.out.println("限制条数" + limit);


		DataGrid data = new DataGrid();
		if (worker!=null){
			Long wid = (Long)worker.getWid();
			//查到项目列表数据
			List<ProjectList> list2 = new ArrayList<ProjectList>();
			System.out.println("-----------wid:" + wid);
			System.out.println("-----------pcardnumber:" + pcardnumber);

			list2 = doctorService.projectList(pcardnumber, wid,offset,limit);

			//打到layui数据对象中s

			int count =list2.size();
			//病人数量
			data.setCount(count);
			data.setData(list2);
			//		打包成session 返回回去
			String jsonstr = JSON.toJSONString(data);
			return jsonstr;
		}
		else {
			String jsonstr = JSON.toJSONString(data);
			return jsonstr;
		}



	}

	/**
	 * 获取
	 * 医生的wid
	 * 病人的卡号pcardnumber
	 * 查看的项目号proid
	 *
	 * 查找该项目的所有细项
	 * @param request
	 * @return
	 */

	@RequestMapping(path = "/getItemList")
	@ResponseBody
	public String getItemList(HttpServletRequest request){
		System.out.println("调用getItemList");
		String str = "";
		//病人的卡号pcardnumber
//		String pcardnumber = request.getParameter("pcardnumber");
//		//医生的wid
//		int wid = Integer.valueOf(request.getParameter("wid"));
//		//查看的项目号proid
//		int proid = Integer.valueOf(request.getParameter("proid"));

		String jsonString = request.getParameter("data");
		int offset = Integer.valueOf(request.getParameter("page"));
		int limit = Integer.valueOf(request.getParameter("limit"));
		offset = (offset - 1) * limit ;

//
//		System.out.println("itemlist data:" + jsonString);
//		System.out.println("当前页数" + offset);
//		System.out.println("限制条数" + limit);

		Gson gson = new Gson();
		ProjectList temp = gson.fromJson(jsonString, ProjectList.class);
		if (temp!=null){
//			System.out.println("temp.getCpid();"+temp.getCpid());
//			System.out.println("temp.get" + temp.getProid());
//			System.out.println("pcardnumber"+ request.getSession().getAttribute("pcardnumber"));
			int cpid =(int)temp.getCpid();
			int proid =(int) temp.getProid();
			CheckupProject checkupProject = doctorService.getSummary(cpid);
			request.getSession().setAttribute("checkupProject", checkupProject);
			String cpsummary = checkupProject.getCpsummary();
			request.getSession().setAttribute("cpsummary", cpsummary);
			Worker worker = (Worker)request.getSession().getAttribute("worker");
			Long wid = (Long)worker.getWid();
			String pcardnumber =(String)request.getSession().getAttribute("pcardnumber");

			//		System.out.println("病人卡号 " + pcardnumber + "医生id" + wid + "项目号" + proid);
			List<ItemList> itemLists = new ArrayList<ItemList>();
			itemLists = doctorService.itemList(pcardnumber, wid, proid,offset,limit);
			for (int i = 0; i <itemLists.size() ; i++)
			{
				String itemCptime = itemLists.get(i).getCpdate().toString();
				String cptime = itemCptime
						.toString()
						.substring(0, itemCptime.toString().indexOf("."));;
				itemLists.get(i).setCpdate(cptime);//去0后存进去
				System.out.println("对象中的检测值："+itemLists.get(i).getCicheckvalue());
				String minStr = String.valueOf(itemLists.get(i).getImin());
				String maxStr = String.valueOf(itemLists.get(i).getImax());
				itemLists.get(i).setRange(minStr+"-"+maxStr);
				System.out.println("参考值范围：" + itemLists.get(i).getRange());
			}

			//打包成json对象发回去
			DataGrid dataGrid = new DataGrid();
			dataGrid.setCount(itemLists.size());
			dataGrid.setData(itemLists);

			str = JSON.toJSONString(dataGrid);
			System.out.println("getitemlist发给前台的str"+str);

		}
		return  str;
	}

	@RequestMapping(path = "/receProj")
	@ResponseBody
	public String receiveProject(HttpServletRequest request){
		String str = "";
		//接收传上来的参数
		String data = request.getParameter("data");
		Long wid = (Long) request.getSession().getAttribute("wid");
		ProjectList projList = new ProjectList();
		Gson gson = new Gson();
		//将data转为对象，从对象中获取cpid
		projList = gson.fromJson(data,ProjectList.class);
		//判断是否可以接收
//		System.out.println("data" + data);
//		System.out.println("projList.getCpstate()"+projList.getCpstate());
//		System.out.println("projList.getGostate()"+projList.getGostate());
		if (projList.getCpstate()==0&&projList.getGostate().equals("1"))
		{
			//未接收 已支付
			int cpid = (int) projList.getCpid();
			//添加小结在修改check project的wid
			long gcid = projList.getGcid();
			int flag = doctorService.updateCpstate(cpid,wid);
			if (flag==1){
				//更新导检单的状态
				doctorService.updateGcstate(gcid, "1");

			}

			str = "true";
			System.out.println(str);
			return str;
		} else if (projList.getGostate().equals("0"))
		{
			//未支付
			str="nopay";
			System.out.println(str);
			return str;
		}else if (projList.getCpstate()==1){
			//已经接受过了
			str="already";
			System.out.println(str);
			return str;
		}
		return str;
	}


	//更新表格数据
	@RequestMapping(path = "/updateItem")
	@ResponseBody
	public String updateItem(HttpServletRequest request){
		//todo 提醒医生输入的只能是数字,体检的细项可能有小数 展示先能改数据就好
		String str = "";
		String data = request.getParameter("data");
		String field = request.getParameter("field");
		double value = Double.valueOf(request.getParameter("value"));
//		data存入session
//		request.getSession().setAttribute("data",data);


		Gson gson = new Gson();
		ItemList item = new ItemList();
		System.out.println("data:" + data);
		item = gson.fromJson(data, ItemList.class);
//		System.out.println("item.cpid"+item.getCpid());
//		System.out.println("data "+data);
//		System.out.println("field"+field);
//		System.out.println("value"+value);
		//谁修改的wid
		int iid = (int)item.getIid();
		int ciid = (int)item.getCiid();
		//当前的id是多少wid
		Worker worker = (Worker)request.getSession().getAttribute("worker");
		Long wid = (Long) worker.getWid();
		//当前时间获取
//		System.out.println("wid" + wid);//ok
//		System.out.println("检测值"+item.getCicheckvalue());//ok
//		System.out.println("iid" + item.getIid());
//		System.out.println("ciid"+item.getCiid());
		//检测值跟该项的标准对比
//		imin imax是多少
		ItemList item2 = new ItemList();
		item2 = doctorService.findItem(iid);
		double imax = item2.getImax();
		double imin = item2.getImin();
		double checkValue = value;//ok
		String cicompareResult = "";
		if (imax==0&&imin==0){
//			没有标准的 就不显示
			cicompareResult = "";
		} else if (checkValue < imin)
		{
			//偏低
			cicompareResult = "↓";

		} else if (imin<=checkValue&&checkValue<=imax)
		{
//			正常
			cicompareResult = "";
		} else if (checkValue > imax)
		{
//			偏高
			cicompareResult = "↑";
		}
		//当前时间
		Timestamp nowtime = new Timestamp(System.currentTimeMillis());//ok
		int res = doctorService.InputItem(ciid, wid, checkValue, cicompareResult, nowtime);
		System.out.println("是否成功" + res);
		return str;
	}


	@RequestMapping(path = "/getCpid")
	@ResponseBody
	public String getCpid(HttpServletRequest request){
		System.out.println("----------getCpid:获取cpid------");
		String str = "";

		String jsonString = request.getParameter("data");
		Gson gson = new Gson();
		ProjectList temp = gson.fromJson(jsonString, ProjectList.class);
		if (temp != null)
		{
//			设置cpid
			request.getSession().setAttribute("cpid", temp.getCpid());
//			request
			str = String.valueOf(temp.getCpid());
			//将check_project对象存到session中
			CheckupProject checkupProject = new CheckupProject();
			long cpid = temp.getCpid();
			checkupProject = doctorService.getSummary(temp.getCpid());
			String tempStr = gson.toJson(checkupProject);


//			System.out.println("cpid"+cpid);
//			System.out.println("data:" + jsonString);
//			System.out.println("tempStr"+tempStr);
//			System.out.println("summary"+checkupProject.getCpsummary());
			//存入session
			request.getSession().setAttribute("cpid", cpid);
			request.getSession().setAttribute("cpsummary",checkupProject.getCpsummary());
			request.getSession().setAttribute("checkupProject",checkupProject);

			str = JSON.toJSONString(checkupProject);
			System.out.println("----------getCpid:获取cpid结束------");

		}
		return str;
	}


	@RequestMapping(path = "/summary")
	@ResponseBody
	public String updateSummary(HttpServletRequest request)
	{
		String str = "";
		String text = request.getParameter("textval");
		String type = request.getParameter("type");
		Long cpid = (Long) request.getSession().getAttribute("cpid");
		System.out.println("提交小结： " + cpid);
		//获取发上来的文本内容 还有编辑方式  是编辑还是提交
		//查询cpid是否还可以被修改
		CheckupProject cpObj = doctorService.getSumState(cpid);
		System.out.println("tostring：" + cpObj.toString());
		System.out.println("cpid :"+cpid);
		long flag = cpObj.getSumState();
		System.out.println("cpid" + cpid);
		System.out.println("flag" + flag);
		if (flag==0){
			if (text.equals(""))
			{
				//输入内容为空
				str = "输入内容为空";
				System.out.println("str"+str);
				return str;
			} else if (type.equals("edit"))
			{
				doctorService.updateSummary(cpid,text);
				str = "编辑成功";
				System.out.println(str);
				return str;
			}else if (type.equals("submit"))
			{
				doctorService.submitSummary(cpid,text);
				str = "提交成功";
				System.out.println(str);
				return str;
			}
		}else {
			str = "该项目已完结，无法在修改";
			return str;
		}

		return str;
	}


	@PostMapping("/projectPictureUpload")
	@ResponseBody
	public String projectPictureUpload(HttpServletRequest request,@RequestParam(value = "projectImg",required = true) MultipartFile file){
		System.out.println("进入文件上传");
		long cpid = (Long)request.getSession().getAttribute("cpid");
		System.out.println("cpid"+cpid);
		HwxResponse hwxResponse = new HwxResponse();
		String jsonstr = "";
		//将图片上传到服务器
		if(file.isEmpty()){
			hwxResponse.setCode(205);
			hwxResponse.setMsg("项目地址不能为空");
			jsonstr=JSON.toJSONString(hwxResponse);
			System.out.println("-----文件为空------");
			return jsonstr;
		}
		//原始文件名
		String originalFilename = file.getOriginalFilename();
		//文件后缀
		String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
		//图片名称为uuid+图片后缀防止冲突
		String fileName = UUID.randomUUID().toString()+"."+suffix;
		String os = System.getProperty("os.name");
		//文件保存路径
		String filePath="";
		String savePath = request.getSession().getServletContext().getRealPath("/resources/hwxupload/");

		if(os.toLowerCase().startsWith("win")){
			//windows下的路径
			//todo 改为相对路径 添加数据库记录
			filePath = "resources/hwxupload/";
		}else {
			filePath = savePath;
		}
		try {
			//写入图片
			Boolean writePictureflag = HwxFileUtils.uploadFile(file.getBytes(),savePath,fileName);
			if(writePictureflag == false){
				//上传图片失败
				hwxResponse.setCode(205);
				hwxResponse.setMsg("文件上传失败");
				jsonstr=JSON.toJSONString(hwxResponse);
				return jsonstr;
			}
			//上传成功后，将可以访问的完整路径返回
			String fullImgpath = filePath+fileName;
			hwxResponse.setCode(0);
			hwxResponse.setMsg("文件上传成功");
			hwxResponse.setData(fullImgpath);
			System.out.println("文件名：" + fileName);
			System.out.println("文件目录" + filePath);

			//写入数据库
			int temp = doctorService.addPicAddress(cpid, fullImgpath);
//			System.out.println("是否成功" + temp);
			jsonstr=JSON.toJSONString(hwxResponse);
			return jsonstr;
		} catch (Exception e) {
			e.printStackTrace();
			//上传图片失败
			hwxResponse.setCode(205);
			hwxResponse.setMsg("文件上传失败");
			jsonstr=JSON.toJSONString(hwxResponse);
			return jsonstr;
		}
	}

	/**
	 * 获取导检单列表
	 * @param request
	 * @return
	 */
	@RequestMapping(path = "/getGcList")
	@ResponseBody
	public String getGcList(HttpServletRequest request){
		String str = "";
		int offset = Integer.valueOf(request.getParameter("page"));
		int limit = Integer.valueOf(request.getParameter("limit"));
		offset = (offset - 1) * limit ;
		System.out.println("getGcList------" + "offset" + offset + "limit" + limit);
		//调用方法
		List<GcList> gcList = new ArrayList<GcList>();
		List<GcList> gcList2 = new ArrayList<GcList>();

		gcList = doctorService.findGcList(offset,limit);
		gcList2 = doctorService.findGcList2();
		//设置有效期范围range
		for (int i = 0; i <gcList.size() ; i++)
		{
			String starttime =gcList.get(i).getGoappointdate();
			//字符串先转为时间加上有效期   转为结束时间
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
			try
			{
				Date startDate = simpleDateFormat.parse(starttime);
				Calendar calendar1 = Calendar.getInstance();
				calendar1.setTime(startDate);
				calendar1.add(calendar1.DATE,+Integer.valueOf(gcList.get(i).getGolasttime()) );

				gcList.get(i).setTimeRange(starttime + "~" + simpleDateFormat.format(calendar1.getTime()));
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
		}

		//gcList中的数据还要多加一些
		DataGrid data = new DataGrid();

		data.setCount(gcList2.size());
		data.setData(gcList);

		String jsonString = JSON.toJSONString(data);
		return jsonString;
	}

	@RequestMapping(path = "/getAllItem")
	@ResponseBody
	public String getAllItem(HttpServletRequest request)
	{

		String jsonStr = "";
		String str = "";
		int offset = Integer.valueOf(request.getParameter("page"));
		int limit = Integer.valueOf(request.getParameter("limit"));
		String data = request.getParameter("data");
		offset = (offset - 1) * limit;
		System.out.println("getGcList------" + "offset" + offset + "limit" + limit);
		System.out.println("data：" + data);
		Gson gson = new Gson();

		if (data.length()>1){
			GcList temp = new GcList();

			temp = gson.fromJson(data, GcList.class);
			long  gcid = temp.getGcid();
			//		保存信息到session中
			//		System.out.println("gchid: " + gchid + "crid: " + crid + "wid： " + wid);
			//gcid\
			request.getSession().setAttribute("gchid", gcid);
			//crid
			request.getSession().setAttribute("crid", temp.getCrid());
			//
			List<ItemList>  list1 = new ArrayList<ItemList>();
			list1 = doctorService.findAllItem(gcid, offset, limit);

			for (int i = 0; i <list1.size() ; i++)
			{
				System.out.println("对象中的检测值："+list1.get(i).getCicheckvalue());
				String minStr = String.valueOf(list1.get(i).getImin());
				String maxStr = String.valueOf(list1.get(i).getImax());
				list1.get(i).setRange(minStr+"-"+maxStr);
				System.out.println("参考值范围：" + list1.get(i).getRange());
			}

			DataGrid dataGrid = new DataGrid();
			dataGrid.setCount(list1.size());
			dataGrid.setData(list1);
			//打包发给前端
			 jsonStr = gson.toJson(dataGrid);
			System.out.println("getAllItem发给前端的字符串" + jsonStr);
		}else {
			List<ItemList>  list1 = new ArrayList<ItemList>();
			DataGrid dataGrid = new DataGrid();
			dataGrid.setCount(list1.size());
			dataGrid.setData(list1);
			//打包发给前端
			jsonStr = gson.toJson(dataGrid);
			System.out.println("getAllItem发给前端的字符串" + jsonStr);
		}
		return jsonStr;
	}



	@RequestMapping(path = "/addSum")
	@ResponseBody
	public String addSum(HttpServletRequest request){
		String str = "";
		//获取总结
		//获取建议
		//获取生活保健指导
		String sum = request.getParameter("doctorsum");
		String suggest = request.getParameter("suggest");
		String lifeguide = request.getParameter("lifeguide");
		//获取医生wid  session
		Long wid = (Long)request.getSession().getAttribute("wid");
		//获取当前时间 转为timestamp
		Date date = new Date();
		Timestamp nousedate = new Timestamp(date.getTime());
		//获取导检单号 todo 在点击查看的时候就要更新到 session里
		Long gchid = (Long)request.getSession().getAttribute("gchid");
		//获取报告号
		Long crid = (Long)request.getSession().getAttribute("crid");
		//测试获取数据
		System.out.println("gchid: " + gchid + "crid: " + crid + "wid： " + wid);
		System.out.println("-总结-:" + sum + "-建议-:" + suggest + "-指导-:" + lifeguide);
		System.out.println("当前时间："+nousedate);

		//插入数据库 体检报告表 医生id 日期 三个建议 crid nousedate  sum suggest lifeguide wid
		int flag1 =doctorService.updateCrSum(crid, nousedate, sum, suggest, lifeguide, wid);
		//更新导检单的状态为2 已完成 crid
		int flag2 =doctorService.updateGcstate(gchid, "2");

		if (flag1==1&&flag2==1){
			str = "提交成功";
		}else {
			str = "提交失败";
		}
		return str;
	}


//	年销量 月销量 周销量
@RequestMapping(path = "/yearProj")
@ResponseBody
public String yearProj(HttpServletRequest request){
	String str = "";
	ArrayList<HwxInfo> arrayList = new ArrayList<>();
	List<HwxInfo> list = doctorService.yearProj();

	for (int i = 0; i <list.size() ; i++)
	{
		arrayList.add(new HwxInfo(list.get(i).getName(),list.get(i).getRecord()));
	}
	Gson gson = new Gson();
	String jsonStr = gson.toJson(arrayList);

	return jsonStr;
}

	@RequestMapping(path = "/mouthProj")
	@ResponseBody
	public String mouthProj(HttpServletRequest request){
		String str = "";
		ArrayList<HwxInfo> arrayList = new ArrayList<>();
		List<HwxInfo> list = doctorService.mouthProj();

		for (int i = 0; i <list.size() ; i++)
		{
			arrayList.add(new HwxInfo(list.get(i).getName(),list.get(i).getRecord()));
		}
		Gson gson = new Gson();
		String jsonStr = gson.toJson(arrayList);

		return jsonStr;
	}

	@RequestMapping(path = "/weekProj")
	@ResponseBody
	public String weekProj(HttpServletRequest request){
		String str = "";
		ArrayList<HwxInfo> arrayList = new ArrayList<>();
		List<HwxInfo> list = doctorService.weekProj();

		for (int i = 0; i <list.size() ; i++)
		{
			arrayList.add(new HwxInfo(list.get(i).getName(),list.get(i).getRecord()));
		}
		Gson gson = new Gson();
		String jsonStr = gson.toJson(arrayList);

		return jsonStr;
	}

//	工作量统计
@RequestMapping(path = "/maxMonth")
@ResponseBody
public String maxMonth(HttpServletRequest request){
	String str = "";
	ArrayList<HwxInfo> arrayList = new ArrayList<>();
	List<HwxInfo> list = doctorService.maxMonth();

	for (int i = 0; i <list.size() ; i++)
	{
		arrayList.add(new HwxInfo(list.get(i).getName(),list.get(i).getRecord()));
	}
	Gson gson = new Gson();
	String jsonStr = gson.toJson(arrayList);
	return jsonStr;
}



@RequestMapping(path = "/maxDepart")
@ResponseBody
public String maxDepart(HttpServletRequest request){
	String str = "";
	ArrayList<HwxInfo> arrayList = new ArrayList<>();
	List<HwxInfo> list = doctorService.maxDepart();

	for (int i = 0; i <list.size() ; i++)
	{
		arrayList.add(new HwxInfo(list.get(i).getName(),list.get(i).getRecord()));
	}
	Gson gson = new Gson();
	String jsonStr = gson.toJson(arrayList);
	return jsonStr;
}


//	折扣排行榜
@RequestMapping(path = "/minDepart")
@ResponseBody
public String minDepart(HttpServletRequest request){
	String str = "";
	ArrayList<HwxInfo> arrayList = new ArrayList<>();
	List<HwxInfo> list = doctorService.minDepart();

	for (int i = 0; i <list.size() ; i++)
	{
		arrayList.add(new HwxInfo(list.get(i).getName(),list.get(i).getRecord()));
	}
	Gson gson = new Gson();
	String jsonStr = gson.toJson(arrayList);
	return jsonStr;
}


@RequestMapping(path = "/discountList")
@ResponseBody
public String discountList(HttpServletRequest request){
		String str = "";
		ArrayList<HwxInfo> arrayList = new ArrayList<>();
		List<HwxInfo> list = doctorService.discountList();
		for (int i = 0; i <list.size() ; i++)
		{
			arrayList.add(new HwxInfo(list.get(i).getName(),list.get(i).getRecord()));
		}
		Gson gson = new Gson();
		String jsonStr = gson.toJson(arrayList);
		return jsonStr;
	}

//  实际体检人数/总人数比例
@RequestMapping(path = "/depart_prop")
@ResponseBody
public String depart_prop(HttpServletRequest request){
	String str = "";
	ArrayList<HwxInfo> arrayList = new ArrayList<>();
	List<HwxInfo> list = doctorService.depart_prop();
	for (int i = 0; i <list.size() ; i++)
	{
		arrayList.add(new HwxInfo(list.get(i).getName(),list.get(i).getRecord()));
	}
	Gson gson = new Gson();
	String jsonStr = gson.toJson(arrayList);
	return jsonStr;
}


//people_prop
@RequestMapping(path = "/people_prop")
@ResponseBody
public String people_prop(HttpServletRequest request){
	String str = "";
	long goid = Long.valueOf(request.getParameter("goid"));
	System.out.println("goid :" + goid);
	ArrayList<HwxInfo> arrayList = new ArrayList<>();
	List<HwxInfo> list = doctorService.people_prop(goid);
	for (int i = 0; i <list.size() ; i++)
	{
		arrayList.add(new HwxInfo(list.get(i).getName(),list.get(i).getRecord()));
	}
	Gson gson = new Gson();
	String jsonStr = gson.toJson(arrayList);
	return jsonStr;
}

	@RequestMapping(path = "/printDoc")
	@ResponseBody
	public String  printDoc(HttpServletRequest request, HttpServletResponse response){
		System.out.println("打印报告");
		String str = "";

		//todo  获取真实的gcid
		String temp = request.getParameter("gcid");

		Long gcid =Long.valueOf(temp);
		List<ItemList>  list1 = new ArrayList<ItemList>();
		System.out.println("printdoc gcid:   "+ gcid);
		Pinfo  pinfo = new Pinfo();
		List<Project>  projectList = new ArrayList<Project>();
		List<ProjectList>  projectListList = new ArrayList<ProjectList>();
		CheckupReport  checkupReport= new CheckupReport();


		//获取用户所有信息
		pinfo = doctorService.getUserInfo(gcid);
		//待体检项目
		projectList = doctorService.getTodoList(gcid);
		//获取细项列表
		list1 = doctorService.findAllItem(gcid, 1, 100);
		//获取小结列表
		projectListList = doctorService.getSumList(gcid);
		//获取总结
		checkupReport = doctorService.getReport(gcid);

		String docfilename = pinfo.getPname()+"-"+pinfo.getGchid()+".docx";
		//把文件名存到session中
		request.getSession().setAttribute("downname", docfilename);

		for (int i = 0; i <list1.size() ; i++)
		{
			System.out.println("对象中的检测值："+list1.get(i).getCicheckvalue());
			String minStr = String.valueOf(list1.get(i).getImin());
			String maxStr = String.valueOf(list1.get(i).getImax());
			list1.get(i).setRange(minStr+"-"+maxStr);
			System.out.println("参考值范围：" + list1.get(i).getRange());
		}



		try
		{
			PoiDemoMain poiDemoMain = new PoiDemoMain();
			poiDemoMain.createWord(list1,pinfo,projectList,projectListList,checkupReport);
		} catch (Exception e)
		{
			e.printStackTrace();
		}



		//文件下载
//		String docfilename = pinfo.getPname()+"-"+pinfo.getGchid()+".docx";
//		String outputUrl = "./word/"+docfilename;
//
//		//5.创建文件名
//		String fileName = docfilename;
//		//6.获取输出流对象
//		ServletOutputStream outputStream = null;
//		try
//		{
//			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
//			//7.获取mimeType
//			ServletContext servletContext = request.getServletContext();
//			String mimeType = servletContext.getMimeType(docfilename);
//			System.out.println(mimeType);
//			//8.获取浏览器信息,对文件名进行重新编码
//			fileName = YeFileUtils1.filenameEncoding(docfilename,request);
//			//9.设置信息头
//			response.setHeader("Content-Disposition","attachment;filename="+docfilename);
//			response.setContentType("application/octet-stream");
//
//			//10.拿到文件写入文件,关闭流
//			File myDocFile = new File(outputUrl);
//			String filename2 = myDocFile.getName();
//			String ext = filename2.substring(filename2.lastIndexOf(".") + 1).toUpperCase();
//			InputStream fis = new BufferedInputStream(new FileInputStream(outputUrl));
//			System.out.println("文件路径" + outputUrl);
//
//			byte[] buffer = new byte[fis.available()];
//			      fis.read(buffer);
//			      fis.close();
//			toClient.write(buffer);
//			toClient.flush();
//			toClient.close();
//
//		} catch (IOException e)
//		{
//			e.printStackTrace();
//		}

		return "生成成功";
	}



	@RequestMapping(path = "/downDoc")
	@ResponseBody
	public ModelAndView downDoc(HttpServletRequest request, HttpServletResponse response)throws IOException{
		System.out.println("进入文件下载:");
		String filename = (String) request.getParameter("gcid");
		//文件名
		filename = filename + ".docx";
		String path = "./word/";
		//文件路径
		String fullname = path+filename;
		System.out.println("文件路径： " + fullname);
		File file = new File(fullname);

		//如果文件不存在
		if (!file.exists()) {
			ModelAndView mv=new ModelAndView();
			mv.addObject("warning","您要下载的资源已被删除！！");
			mv.setViewName("back/jsp/downerror");
			return mv;
		}
		String downloadFileName = new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
		response.setHeader("content-disposition", "attachment;filename=" + downloadFileName);
		//读取要下载的文件，保存到文件输入流
		FileInputStream in = new FileInputStream(fullname);
		//创建输出流
		OutputStream out = response.getOutputStream();
		//创建缓冲区	
		byte[] buffer = new byte[1024];
		int len = 0;
		//循环将输入流中的内容读取到缓冲区当中
		while ((len = in.read(buffer)) > 0) {
			//输出缓冲区的内容到浏览器，实现文件下载
			out.write(buffer, 0, len);
		}
		//关闭文件输入流
		in.close();
		//关闭输出流
		out.close();
		return null;
	}

}
