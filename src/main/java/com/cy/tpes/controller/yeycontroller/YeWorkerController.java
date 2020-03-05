package com.cy.tpes.controller.yeycontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.cy.tpes.entity.yeyentity.*;
import com.cy.tpes.service.yeyservice.YeWorkerService;
import com.google.gson.Gson;
import org.apache.ibatis.session.RowBounds;
//import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class YeWorkerController
{

	//备忘（修改地方） 叶正阳
	//依赖注释了一个tomcat重复的
	//静态文件建了个js,css包
	//增加一个bean  multipartResolver,用来解决文件上传
	private static String bodyTemp = null;
	private static String subjectTemp = null;

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getCommonsMultipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(20971520);
		multipartResolver.setMaxInMemorySize(1048576);
		return multipartResolver;
	}


	@RequestMapping("/to/{id}")
	//单纯的界面跳转
	public String methodAdmin(@PathVariable("id") String id){
		System.out.println(id);
		String result = id.substring(0,id.indexOf('.'));
		System.out.println(result);
		return result;
	}

	@Resource
	private YeWorkerService yeWorkerService;
	//
	@RequestMapping("/yefirst.do")
	public String toIndex(){
		return "yelogin";
	}

	@RequestMapping("/yelogin.do")
	public String loginToMian(String waccount, String wpass, HttpServletRequest request){
		YeWorker worker =yeWorkerService.workerLogin(waccount);
		if(worker!=null){
			String workAccount = worker.getWaccount();
			System.out.println("workAccount:"+workAccount);
			request.getSession().setAttribute("yeWorkerAccount",workAccount);
			Map<String, List<YeMenu>> menuMap = yeWorkerService.selectMenu(workAccount);
			request.setAttribute("menuMap",menuMap);
			for(Map.Entry<String, List<YeMenu>> entry : menuMap.entrySet()){
				System.out.println(entry.getKey());
			}
			return "yemaintest";
		}
		return "yelogin";
	}

	//文件上传
	@RequestMapping("/yefileupload.do")
	@ResponseBody
	public JSONObject fileUp(@RequestParam("file") MultipartFile multipartFile,HttpServletRequest request){
		int code = 0;
		String msg = null;

		//获取表格数据,把方法分装到service里
		Map<String, Object> tableContentMap = yeWorkerService.getTableContent(code, msg, multipartFile);
		code = (int)tableContentMap.get("code");
		//表格数据类型不匹配，直接结束
		if(code ==1){
			msg = (String)tableContentMap.get("msg");
			//回传
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("code",code);
			jsonObject.put("msg",msg);
			return jsonObject;
		}
		long gcAccount = (long)tableContentMap.get("gcAccount");
		System.out.println("单位账号："+gcAccount);
		ArrayList<YePatient> dataPatientList = (ArrayList<YePatient>)tableContentMap.get("data");

		//调用业务
		//判断单位账号是否存在
		YeGroupClient yeGroupClient = yeWorkerService.patientAccountExist(gcAccount);
		//单位账号不存在，直接结束
		if(yeGroupClient==null){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("code",1);
			jsonObject.put("msg","提示：账号不存在");
			return jsonObject;
		}
		System.out.println("2222222222222222");

		//调用业务，判断账号中余额是否足够,足够扣款(嵌套业务：插入订单表、订单与套餐关系表、病人表、导检单表)
		JSONObject jsonObject1 = yeWorkerService.judgeAndAccount(dataPatientList,gcAccount,request);
		//否则结束，提示余额不足
		if(jsonObject1.get("code")!=null&&(int)jsonObject1.get("code")==1){
			return jsonObject1;
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code",code);
		System.out.println("1111111111111111111");
		return jsonObject;
	}

	@RequestMapping("/yeregister.do")
	public String entityRegister(HttpSession session,String wname, String waccount, String wpass, String wphone, String wemail ){
		System.out.println(waccount+wpass+wphone);
		boolean register = yeWorkerService.groupClientRegister(waccount, wname, wpass, wphone, wemail);
		String result = "yeregister";
		if(register){
			result = "yeindex";
		}
		session.setAttribute("yeregister","注册失败");
		return result;
	}
	//跳转到单人开单的页面，需先获取可供选择的套餐
	@RequestMapping("/yesinglebill.do")
	public String toSingleBill(HttpServletRequest request){
		List<YePackage> yePackages = yeWorkerService.selectAllPackage();
		request.getSession().setAttribute("packageList",yePackages);
		return "yesinglebill";
	}

	//单人开单表单提交
	@RequestMapping("/yesinglebillform.do")
	public String singleBillForm(HttpServletRequest request,String waccount,String wpass,String sphone, String sname,String sex,String spackage,String sid){
		//查询单位的账号密码是否存在
		YeGroupClient yeGroupClient = yeWorkerService.patientAccountExist(Long.valueOf(waccount));
		if(yeGroupClient!=null){
			if(yeGroupClient.getGcpass().equals(wpass)){
				ArrayList<YePatient> arrPatient = new ArrayList<>();
				YePatient yePatient = new YePatient();
				System.out.println("package:"+spackage);
				yePatient.setPname(sname);
				yePatient.setPidentitynumber(sid);
				yePatient.setPphone(sphone);
				yePatient.setGcid(Long.valueOf(waccount));
				yePatient.setPgender(Long.valueOf(sex));
				yePatient.setPackname(spackage);
				arrPatient.add(yePatient);

				JSONObject jsonObject = yeWorkerService.judgeAndAccount(arrPatient, Long.valueOf(waccount), request);
				if(jsonObject.get("code")!=null&&(int)jsonObject.get("code")==1){
					String msg = (String)jsonObject.get("msg");
					request.getSession().setAttribute("singlebill",msg);
					return "yesinglebill";
				}
				return "yeindex";
			} else{
				request.getSession().setAttribute("singlebill","密码错误");
				return "yesinglebill";
			}
		}
		request.getSession().setAttribute("singlebill","账号不存在");
		return "yesinglebill";
	}

	@RequestMapping("/yeExcelGcPatient.do")
	public String outGcPatientExcel(HttpServletResponse response, HttpServletRequest request, String gcaccount) throws IOException
	{

		String result = null;
		System.out.println("gcaccount:"+gcaccount);
		JSONObject jsonObject = yeWorkerService.excelOut(response, request, gcaccount);
		if(jsonObject.get("code")!=null&&(int)jsonObject.get("code")==1){
			request.setAttribute("downLoad",(String)jsonObject.get("msg"));
			result = "yeexcelout";
			return result;
		}
		return result;
	}

	//下载上传excel的模板和套餐信息
	@RequestMapping("/yedownloadmodel.do")
	public void downloadModelAndPackageMessage(HttpServletRequest request, HttpServletResponse response){
		yeWorkerService.modelDownload(response,request);
	}

	//病人列表分页
	@RequestMapping("/yePatientTable.do")
	@ResponseBody
	public YeDataTable fileTableMethod(String gcname, String date , String name, String phone, HttpSession session, String page, String limit, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		System.out.println("yePatientTable");
		System.out.println("date:"+date+"name:"+name+"phone:"+phone);
		//		System.out.println("useraccount:"+account);
		//		String page = request.getParameter("page");
		//		String limit = request.getParameter("limit");

		int pageInt = Integer.valueOf(page);
		int limitInt = Integer.valueOf(limit);

		RowBounds rowBounds = new RowBounds((pageInt-1)*limitInt,limitInt);
		System.out.println("offset:"+(pageInt-1)*limitInt);
		System.out.println("limit:"+limit);

		List<Object> list = yeWorkerService.patientTableMethod(name,gcname,phone,date,rowBounds);
		ArrayList<YePatient> arr = new ArrayList<>();
		arr = (ArrayList<YePatient>)list.get(0);
		long sumCount = (long)list.get(1);
		System.out.println("sumCount:"+sumCount);

		YeDataTable dataForTable = new YeDataTable();
		dataForTable.setCode(0);
		dataForTable.setMsg("");
		dataForTable.setCount(sumCount);
		dataForTable.setData(arr);
		return dataForTable;
	}

	//资金列表分页
	@RequestMapping("/yeFundTable.do")
	@ResponseBody
	public YeDataTable FundTableMethod(String name,String date ,String type, String account,HttpSession session, String page, String limit, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		System.out.println("yeFundTable");
		System.out.println("date:"+date+"name:"+name+"type:"+type+"account:"+account);

		int pageInt = Integer.valueOf(page);
		int limitInt = Integer.valueOf(limit);

		RowBounds rowBounds = new RowBounds((pageInt-1)*limitInt,limitInt);
		System.out.println("offset:"+(pageInt-1)*limitInt);
		System.out.println("limit:"+limit);

		List<Object> list = yeWorkerService.fundTableMethod(name,account,type,date,rowBounds);
		ArrayList<YeFundTransaction> arr = new ArrayList<>();
		arr = (ArrayList<YeFundTransaction>)list.get(0);
		long sumCount = (long)list.get(1);
		System.out.println("sumCount:"+sumCount);

		for (int i = 0; i < arr.size(); i++)
		{
			Timestamp ftdate = arr.get(i).getFtdate();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String stringDate = simpleDateFormat.format(ftdate);
			arr.get(i).setStringDate(stringDate);
		}

		YeDataTable dataForTable = new YeDataTable();
		dataForTable.setCode(0);
		dataForTable.setMsg("");
		dataForTable.setCount(sumCount);
		dataForTable.setData(arr);
		return dataForTable;
	}


	//订单列表分页
	@RequestMapping("/yeOrderTable.do")
	@ResponseBody
	public YeDataTable FundTableMethod(String name,String date , String account,HttpSession session, String page, String limit, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		System.out.println("yeOrderTable");
		System.out.println("date:"+date+"name:"+name+"account:"+account);

		int pageInt = Integer.valueOf(page);
		int limitInt = Integer.valueOf(limit);

		RowBounds rowBounds = new RowBounds((pageInt-1)*limitInt,limitInt);
		System.out.println("offset:"+(pageInt-1)*limitInt);
		System.out.println("limit:"+limit);

		List<Object> list = yeWorkerService.orderTableMethod(name,account,date,rowBounds);
		ArrayList<YeGroupOrder> arr = new ArrayList<>();
		arr = (ArrayList<YeGroupOrder>)list.get(0);
		long sumCount = (long)list.get(1);
		System.out.println("sumCount:"+sumCount);

		for (int i = 0; i < arr.size(); i++)
		{
			Timestamp ftdate = arr.get(i).getGoordertime();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String stringDate = simpleDateFormat.format(ftdate);
			arr.get(i).setSgoordertime(stringDate);
		}

		YeDataTable dataForTable = new YeDataTable();
		dataForTable.setCode(0);
		dataForTable.setMsg("");
		dataForTable.setCount(sumCount);
		dataForTable.setData(arr);
		return dataForTable;
	}


	//报告列表分页
	@RequestMapping("/yeReportTable.do")
	@ResponseBody
	public YeDataTable reportTableMethod(String pname,String packname,String goid,HttpSession session, String page, String limit, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		System.out.println("reportTableMethod");

		int pageInt = Integer.valueOf(page);
		int limitInt = Integer.valueOf(limit);

		RowBounds rowBounds = new RowBounds((pageInt-1)*limitInt,limitInt);
		System.out.println("offset:"+(pageInt-1)*limitInt);
		System.out.println("limit:"+limit);

		List<Object> list = yeWorkerService.selectReport(packname,pname,goid,rowBounds);
		ArrayList<YeCheckupReport> arr = new ArrayList<>();
		arr = (ArrayList<YeCheckupReport>)list.get(0);
		long sumCount = (long)list.get(1);
		System.out.println("sumCount:"+sumCount);

		YeDataTable dataForTable = new YeDataTable();
		dataForTable.setCode(0);
		dataForTable.setMsg("");
		dataForTable.setCount(sumCount);
		dataForTable.setData(arr);
		return dataForTable;
	}

	//重复上次订单
	@RequestMapping("/yereorder.do")
	public String repeatOrder(HttpServletRequest request,HttpServletResponse response,String gcaccount){
		//先获取单位上次订单的信息
		List<YePatient> yePatients = yeWorkerService.selectLastGroupPatientMessage(Long.valueOf(gcaccount));
		if(yePatients.size()<1){
			request.setAttribute("code",1);
			request.setAttribute("msg","无历史订单信息");
			return "yereorder";
		}
		for (int i = 0; i <yePatients.size() ; i++)
		{
			yePatients.get(i).setGcaccount(Long.valueOf(gcaccount));
		}

		//调用之前的业务
		JSONObject jsonObject = yeWorkerService.judgeAndAccount(yePatients,Long.valueOf(gcaccount),request);
		if(jsonObject.get("code")!=null&&(int)jsonObject.get("code")==1){
			String msg = null;
			if(jsonObject.get("msg")!=null){
				msg = (String)jsonObject.get("msg");
			}
			request.setAttribute("msg",msg);

		} else{
			request.setAttribute("msg","开单成功");
		}
		return "yereorder";
	}

	//前往支付宝第三方网关支付
	@RequestMapping(value = "/yealipay.do" , produces = "text/html; charset=UTF-8")
	@ResponseBody
	public void goAlipay(HttpServletResponse response,String amount,String gcaccount) throws AlipayApiException, IOException
	{

		System.out.println("充值的单位账号："+gcaccount+"充值的金额："+amount);

		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(YeAlipayConfig.gatewayUrl, YeAlipayConfig.app_id, YeAlipayConfig.merchant_private_key, "json", YeAlipayConfig.charset, YeAlipayConfig.alipay_public_key, YeAlipayConfig.sign_type);
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(YeAlipayConfig.return_url);
		alipayRequest.setNotifyUrl(YeAlipayConfig.notify_url);
		//现在交易记录表插入一条信息
//		String type = "充值";
//		YeFundTransaction yeFundTransaction = new YeFundTransaction();
//		yeFundTransaction.setFtamount(Double.valueOf(amount));
//		yeFundTransaction.setFttype(type);
//		yeFundTransaction.setGcaccount(Long.valueOf(gcaccount));
//		long l = yeWorkerService.insertFund(yeFundTransaction);
//		String order_num = yeFundTransaction.getFtid()+"";

		long maxID = yeWorkerService.selecFund();
		Random random = new Random();
		maxID += (random.nextInt(10000)+10000);
		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = maxID+"";
		//付款金额，必填
		String total_amount = amount;
		//订单名称，必填
		String subject = "充值";
		subjectTemp = subject;
		//商品描述，可空
		String body = gcaccount;
		bodyTemp = body;
		// 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
		String timeout_express = "1c";
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
				+ "\"total_amount\":\""+ total_amount +"\","
				+ "\"subject\":\""+ subject +"\","
				+ "\"body\":\""+ body +"\","
				+ "\"timeout_express\":\""+ timeout_express +"\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//请求
		String result = alipayClient.pageExecute(alipayRequest).getBody();
		System.out.println(result);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(result);//直接将完整的表单html输出到页面
		response.getWriter().flush();
		response.getWriter().close();
//		return result;
	}

	//支付宝同步通知页面
	@RequestMapping(value = "/alipayReturnNotice")
	public ModelAndView alipayReturnNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {
		System.out.println("支付成功, 进入同步通知接口...");
		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
//			System.out.println("valueStr:"+valueStr);
			//乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}

		//调用SDK验证签名
		boolean signVerified = AlipaySignature.rsaCheckV1(params, YeAlipayConfig.alipay_public_key, YeAlipayConfig.charset, YeAlipayConfig.sign_type);
		ModelAndView mv = new ModelAndView("yewelcome");

//		String workAccount = (String)request.getSession().getAttribute("yeWorkerAccount");
//		Map<String, List<YeMenu>> menuMap = yeWorkerService.selectMenu(workAccount);
//		request.setAttribute("menuMap",menuMap);

		//——请在这里编写您的程序（以下代码仅作参考）——
		if(signVerified) {
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//付款金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");


			System.out.println(out_trade_no+"   "+trade_no + "   " +total_amount);


			//交易记录表插入一条数据
			YeFundTransaction yeFundTransaction = new YeFundTransaction();
			yeFundTransaction.setFtamount(Double.valueOf(total_amount));
			yeFundTransaction.setFttype(subjectTemp);
			yeFundTransaction.setGcaccount(Long.valueOf(bodyTemp));//之前把账号暂存在描述中
			long l = yeWorkerService.insertFund(yeFundTransaction);
			String order_num = yeFundTransaction.getFtid()+"";

			//修改单位账号余额
			long recharge = yeWorkerService.recharge(bodyTemp, total_amount);
			if(recharge>0){
				System.out.println("充值成功");
			}
			// 修改订单状态为支付成功，已付款; 同时新增支付流水
//			orderService.updateOrderStatus(out_trade_no, trade_no, total_amount);

//			Order order = orderService.getOrderById(out_trade_no);
//			Product product = productService.getProductById(order.getProductId());
//			log.info("********************** 支付成功(支付宝同步通知) **********************");
//			log.info("* 订单号: {}", out_trade_no);
//			log.info("* 支付宝交易号: {}", trade_no);
//			log.info("* 实付金额: {}", total_amount);
//			log.info("* 购买产品: {}", product.getName());
//			log.info("***************************************************************");
			mv.addObject("out_trade_no", out_trade_no);
			mv.addObject("trade_no", trade_no);
			mv.addObject("total_amount", total_amount);
//			mv.addObject("productName", product.getName());
		}else {
			System.out.println("支付, 验签失败...");
		}
		return mv;
	}

	//异步通知，必须要公网，以下不发生作用
	@RequestMapping(value = "/alipayNotifyNotice")
	@ResponseBody
	public String alipayNotifyNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {

		System.out.println("支付成功, 进入异步通知接口...");

		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			/*valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");*/
			params.put(name, valueStr);
		}

		//调用SDK验证签名
		boolean signVerified = AlipaySignature.rsaCheckV1(params, YeAlipayConfig.alipay_public_key, YeAlipayConfig.charset, YeAlipayConfig.sign_type);

		//——请在这里编写您的程序（以下代码仅作参考）——

   /* 实际验证过程建议商户务必添加以下校验：
   1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
   2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
   3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
   4、验证app_id是否为该商户本身。
   */
		//验证成功
		if(signVerified) {
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

			//付款金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

			//订单名称
			String subject = new String(request.getParameter("subject").getBytes("ISO-8859-1"),"UTF-8");

			//订单描述
			String body= new String(request.getParameter("body").getBytes("ISO-8859-1"),"UTF-8");

			System.out.println("trade_status"+trade_status);
			if(trade_status.equals("TRADE_FINISHED")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序

				//注意： 尚自习的订单没有退款功能, 这个条件判断是进不来的, 所以此处不必写代码
				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
			}else if (trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序

				//注意：
				//付款完成后，支付宝系统发送该交易状态通知

			}
			System.out.println("支付成功...");
		}else {//验证失败
			System.out.println("支付, 验签失败...");
		}
		return "success";
	}

	@RequestMapping("/checkForGcaccount.do")
	@ResponseBody
	public JSONObject checkAccount(String gcaccount){
		System.out.println("checkForGcaccount.do");
		System.out.println(gcaccount);
		YeGroupClient yeGroupClient = yeWorkerService.patientAccountExist(Long.valueOf(gcaccount));
		JSONObject jsonObject = new JSONObject();
		if(yeGroupClient!=null){
			jsonObject.put("code",0);
			System.out.println("code:"+0);
		} else{
			jsonObject.put("code",1);
			System.out.println("code:"+1);
		}
		return jsonObject;
	}

	//申请退款（或者说是提请结算）
	@RequestMapping("/yerefund.do")
	public String refundMethod(HttpServletRequest request,String gcaccount ,String gcpass,String order){
		JSONObject jsonObject = yeWorkerService.refundMethod(gcaccount, gcpass, order);
		if((int)jsonObject.get("code")!=0){
			String msg = (String)jsonObject.get("msg");
			request.setAttribute("msg",msg);
		} else{
			request.setAttribute("msg","申请退款成功");
		}
		return "yerefund";
	}

	@RequestMapping("/print.do")
	public String printInvoce(String goid,HttpServletRequest request){
		System.out.println("goid:"+goid);
		YeGroupOrder yeGroupOrder = yeWorkerService.forPrint(goid);
		if(yeGroupOrder.getGoinvoicingstate()==0){
			request.setAttribute("msg","提示：发票打印");
			return "yeorderquery";
		}
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateNow = simpleDateFormat.format(date);
		System.out.println("12345"+yeGroupOrder.getGoid());
		System.out.println("goid:"+goid);
		System.out.println("goinvoicingnumber:"+yeGroupOrder.getGoinvoicingnumber());
		System.out.println(yeGroupOrder.getGcname());
		System.out.println(yeGroupOrder.getGcaccount());


		request.setAttribute("goid",yeGroupOrder.getGoid());
		request.setAttribute("godate",dateNow);
		request.setAttribute("gcname",yeGroupOrder.getGcname());
		request.setAttribute("gcaccount",yeGroupOrder.getGcaccount());
		request.setAttribute("waccount",yeGroupOrder.getGcaccount());
		request.setAttribute("goprepay",yeGroupOrder.getGoprepay());
		request.setAttribute("gorefundpay",yeGroupOrder.getGorefundpay());
		request.setAttribute("goreceivedpay",yeGroupOrder.getGoreceivedpay());
		request.setAttribute("",yeGroupOrder.getGoinvoicingnumber());

		return "yeinvoice";
	}

	@RequestMapping("/editAppointDayMethod.do")
	@ResponseBody
	public String editAppointDay(String data,String formData){
		Gson gson = new Gson();
		Map<String,Object> map = new HashMap<>();
		Map<String,String> map2 = new HashMap<>();
		Map mapTable = gson.fromJson(data, map.getClass());
		Map mapForm = gson.fromJson(formData, map.getClass());
		Map mapTable2 = gson.fromJson(data, map2.getClass());
		JSONObject jsonObject = JSON.parseObject(data);
		System.out.println(jsonObject.get("goid"));
		System.out.println(mapTable2.get("goid"));
		System.out.println(mapTable.get("goid"));
		System.out.println(mapTable.get("goid").getClass());
		System.out.println(mapForm.get("days"));
		String msg = null;
		long l = yeWorkerService.editDays(mapTable, mapForm);
		if(l>0){
			msg = "success";
		} else{
			msg = "fail";
		}
		return msg;
	}



}
