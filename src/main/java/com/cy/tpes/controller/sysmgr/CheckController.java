package com.cy.tpes.controller.sysmgr;


import com.cy.tpes.entity.sysmgr.RespDate;
import com.cy.tpes.service.sysmgr.CheckService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Controller
@RequestMapping("/check/")
public class CheckController
{

	@Resource
	private CheckService checkService;

	@RequestMapping(path = "reqCheck",produces = "application/json")
	@ResponseBody
	public Object reqCheck(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition)
	{
		return checkService.reqGuideCheck(condition);
	}

	@RequestMapping(path = "reqCheckList",produces = "application/json")
	@ResponseBody
	public Object reqCheckList(@RequestParam(value = "condition",required=false) HashMap<String,Object> condition)
	{
		return checkService.reqCheckById(condition);
	}

	@RequestMapping(path = "barcode",produces = "application/json")
	@ResponseBody
	public Object barcode(HttpServletRequest request,String iid,String pid,String gcid){
		RespDate respDate = new RespDate();
		BarcodeUtil barcodeUtil = new BarcodeUtil();
		System.out.println(iid);
		System.out.println(pid);
		System.out.println(gcid);
		try{
			Date d = new Date();
			String path = request.getServletContext().getRealPath("")+"resources/images/";
			String time = new SimpleDateFormat("yyyyMMdd").format(d);

			String msg = "G"+gcid+"I"+iid+"P"+pid+"T"+time;
			String imgPath = path + msg;
			System.out.println(imgPath);
			barcodeUtil.generateFile(msg, imgPath+".png");

			HashMap map = new HashMap();
			map.put("pid",pid);
			map.put("iid",iid);
			map.put("bcnumber",msg);
			map.put("bcdate",d);
			checkService.addBarcode(map);

			respDate.setMsg("/resources/images/"+msg+".png");
			respDate.setCode(1);
		}catch(Exception e){
			respDate.setCode(0);
		}
		return respDate;
	}

	@RequestMapping(path = "reqCheckByCard",produces = "application/json")
	@ResponseBody
	public Object reqCheckByCard(int page,
	                           int limit,
	                           @RequestParam(value = "condition",required=false) HashMap<String,Object> condition)
	{

		RowBounds rowBounds = new RowBounds((page-1)*limit,limit);
		return checkService.reqCheckByCard(condition,rowBounds);
	}

}
