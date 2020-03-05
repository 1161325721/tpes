package com.cy.tpes.service.yeyservice;

import com.alibaba.fastjson.JSONObject;
import com.cy.tpes.entity.yeyentity.*;
import com.cy.tpes.mapper.yeymapper.YeWorkerMapper;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

@Service
public class YeWorkerService
{
	@Resource
	private YeWorkerMapper yeWorkerMapper;
	//体检人员登录
	@Transactional(propagation = Propagation.SUPPORTS)
	public YeWorker workerLogin(String account){
		YeWorker worker = yeWorkerMapper.selectWorker(account);
//		获取体检人员菜单
		return worker;
	}

	//判断该账号是否存在
	@Transactional(propagation = Propagation.SUPPORTS)
	public YeGroupClient patientAccountExist(long gcaccount){
		YeGroupClient yeGroupClient = yeWorkerMapper.selectGcAccount(gcaccount);
		return yeGroupClient;
	}

	@Transactional
	public Map<String , List<YeMenu>> selectMenu(String waccount){
		ArrayList<YeMenu> yeMenus = yeWorkerMapper.selectMenu(waccount);
		System.out.println("Account:"+waccount);

		for (int i = 0; i <yeMenus.size() ; i++)
		{
			System.out.println(yeMenus.get(i).getFirstname());
		}
		Map<String , List<YeMenu>> map = new LinkedHashMap<>();
		for (int i = 0; i < yeMenus.size(); i++)
		{
			if(map.containsKey(yeMenus.get(i).getFirstname())){
				map.get(yeMenus.get(i).getFirstname()).add(yeMenus.get(i));
			} else{
				List<YeMenu> arr = new ArrayList<>();
				arr.add(yeMenus.get(i));
				map.put(yeMenus.get(i).getFirstname(),arr);
			}
		}
		return map;
	}

	//获取上传表格数据，不涉及数据库操作
	public Map<String, Object> getTableContent(int code, String msg, MultipartFile multipartFile){
		HashMap<String, Object> map = new HashMap<>();
		ArrayList<YePatient> listPackage = new ArrayList<>();
		long gcAccount = 0;
		try {
			System.out.println(multipartFile.getOriginalFilename());
			CommonsMultipartFile cFile = (CommonsMultipartFile) multipartFile;
			DiskFileItem fileItem = (DiskFileItem) cFile.getFileItem();
			InputStream inputStream = fileItem.getInputStream();

			//1、获取文件输入流
			//				InputStream inputStream = new FileInputStream("C:\\Users\\Administrator\\Desktop\\ExcelTest.xlsx");
			//2、获取Excel工作簿对象
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			//3、得到Excel工作表对象
			XSSFSheet sheetAt = workbook.getSheetAt(0);
			//4、循环读取表格数据
			for (Row row : sheetAt) {
				//首行（即表头）不读取
				if (row.getRowNum() == 0) {
					continue;
				}

				//读取当前行中单元格数据，索引从0开始
//				System.out.println(row.getCell(0).getCellType());
//				System.out.println(row.getCell(1).getCellType());
				//个人信息和选择套餐
				String pname = null;
				String pgendertemp = null;
				int pgender = 1;
				long pphone = 0;
				long gcid = 0;
				String pidentitynumber = null;
				String packname = null;

				//验证身份证号是否合法
				if(row.getCell(4).getCellType() == CellType.STRING){
					pidentitynumber = row.getCell(4).getStringCellValue();
					Pattern digit = compile("\\d+");
					if(pidentitynumber.length()!=18){
						code = 1;
						msg = "提示：身份证号位数有误";
						map.put("code",code);
						map.put("msg",msg);
						return map;
					}
					if(!digit.matcher(pidentitynumber).matches()){
						code = 1;
						msg = "提示：身份证号为纯数字";
						map.put("code",code);
						map.put("msg",msg);
						return map;
					}
				}

				long numberID;
				if(row.getCell(4).getCellType() == CellType.NUMERIC){
					numberID = (long)row.getCell(4).getNumericCellValue();
					String temp = String.valueOf(numberID);
					pidentitynumber = temp;
					if(temp.length()!=18){
						code = 1;
						msg = "提示：身份证号位数有误";
						map.put("code",code);
						map.put("msg",msg);
						return map;
					}
				}

				//验证手机号
				if(row.getCell(1).getCellType() == CellType.STRING){
					code = 1;
					msg = "提示：手机号只能为数字";
					map.put("code",code);
					map.put("msg",msg);
					return map;
				}

				if(row.getCell(0).getCellType() == CellType.STRING&&
						row.getCell(3).getCellType() == CellType.STRING&&
						row.getCell(5).getCellType() == CellType.STRING){
					//后面可以加个判断身份证号和手机号长度的判断
					pname = row.getCell(0).getStringCellValue();
					pgendertemp = row.getCell(3).getStringCellValue();
					packname = row.getCell(5).getStringCellValue();
					//性别录入数据类型转换,默认为1男
					pgender = 1;
					if(pgendertemp.equals("女")){
						pgender = 0;
					}
				} else{
					code = 1;
					msg = "提示：表格数据有误";
					map.put("code",code);
					map.put("msg",msg);
					return map;
				}

				if(row.getCell(1).getCellType() == CellType.NUMERIC&&
				row.getCell(2).getCellType()== CellType.NUMERIC
				){
					pphone = (long)row.getCell(1).getNumericCellValue();
					if(String.valueOf(pphone).length()!=11){
						code = 1;
						msg = "提示：手机号为十一位整数";
						map.put("code",code);
						map.put("msg",msg);
						return map;
					}
					gcid = (long)row.getCell(2).getNumericCellValue();
//					pcardnumber = (long)row.getCell(4).getNumericCellValue();
				} else {
					code = 1;
					msg = "提示：表格数据类型有误";
					map.put("code",code);
					map.put("msg",msg);
					return map;
				}

				System.out.println(pname+"   "+pphone+"  "+gcid+"  "+pgendertemp +"   "+pidentitynumber+"  "+packname);
				//病人状态A1开头
				YePatient patient = new YePatient();
				patient.setPackname(packname);
				patient.setGcid(gcid);
				patient.setPname(pname);
				patient.setPgender(pgender);
				patient.setPidentitynumber(pidentitynumber);
				patient.setPphone(pphone+"");
				patient.setPstate(1);

				listPackage.add(patient);

				if(row.getRowNum()==1){
					gcAccount = gcid;
				}
			}
			//5、关闭流
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		map.put("code",code);
		map.put("data",listPackage);
		map.put("gcAccount",gcAccount);
		return map;
	}

	//判断是否单位账号余额是否足够，足够扣款(需要判断所选择的套餐是否存在)
	//隔离级别之后不用设置，默认就是可重复读级别
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public JSONObject judgeAndAccount(List<YePatient> arr,long gcaccount,HttpServletRequest request){
		int code = 0;
//		System.out.println("code:"+code);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code",code);
		//先从数据库中查询所有的套餐名称及其数量，存到map中
		ArrayList<YePackage> listPackage = new ArrayList<>();
		listPackage = yeWorkerMapper.selectAllPackage();
		HashMap<String, Integer> packageHave = new HashMap<>();

		System.out.println(listPackage.size()+"11111");
		//统计各种套餐数量
		for (int i = 0; i < arr.size(); i++)
		{
			int amount = 0;
			for (int j = 0; j < listPackage.size() ; j++)
			{
				if(arr.get(i).getPackname()!=null&&arr.get(i).getPackname().equals(listPackage.get(j).getPackname())){
					amount++;
					if(packageHave.containsKey(arr.get(i).getPackname())){
						int temp = packageHave.get(arr.get(i).getPackname());
						temp += 1;
						packageHave.put(arr.get(i).getPackname(),temp);
					} else{
						packageHave.put(arr.get(i).getPackname(),1);
					}
				}
			}
			if(amount==0){
				jsonObject.put("code",1);
				jsonObject.put("msg","提示：套餐不存在");
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return jsonObject;
			}
		}

		//获取每个套餐中的项目总费用
		double sum = 0;
		for (Map.Entry<String,Integer> entry: packageHave.entrySet()){
			//查询套餐的费用
			long charge = yeWorkerMapper.selectPackageCharge(entry.getKey());
			//查询套餐的折扣
			long discount = yeWorkerMapper.selectPackageDiscount(entry.getKey());
			sum += charge*entry.getValue()*discount/100;
			
			System.out.println("套餐："+entry.getKey()+"  数量："+entry.getValue()+" 折扣："+discount);
		}
		System.out.println("总价："+sum);
		//查询单位账号余额
		long balance = yeWorkerMapper.selectClientBalance(gcaccount);
		System.out.println(gcaccount+"账号余额："+balance);
		//判断账号余额是否足够，足够扣款，不足结束
		if(balance>sum){
			double restBalance = (balance -sum);
			long result = yeWorkerMapper.updateGcBalance(restBalance,gcaccount);

			if(result==0){
				jsonObject.put("code",1);
				jsonObject.put("msg","提示：扣款失败");
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return jsonObject;
			}
			//插入交易记录表
			YeFundTransaction yeFundTransaction = new YeFundTransaction();
			yeFundTransaction.setFtamount(sum);
			yeFundTransaction.setGcaccount(gcaccount);
			yeFundTransaction.setFttype("体检开单");

			yeWorkerMapper.insertFundTransaction(yeFundTransaction);
		} else {
			jsonObject.put("code",1);
			jsonObject.put("msg","提示：余额不足");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return jsonObject;
		}
		//调用插入订单和订单与套餐关系表
//		JSONObject jsonObject1 = insertGroupOrder(request, gcaccount, sum,arr,listPackage);

		//测试事务回滚
//		System.out.println(1/0);

		//插入订单表
		//获取操作人员id
		String workerAccount= (String) request.getSession().getAttribute("yeWorkerAccount");
		YeWorker yeWorker = workerLogin(workerAccount);
		long id  = yeWorker.getWid();
		//获取单位id
		YeGroupClient yeGroupClient = yeWorkerMapper.selectGcAccount(gcaccount);
		long gcid = yeGroupClient.getGcid();
		System.out.println("gcid111:"+gcid);
		//把录入集合的单位账号都改为单位id
		for (int i = 0; i < arr.size(); i++)
		{
			arr.get(i).setGcid(gcid);
		}
		Date date = new Date(System.currentTimeMillis());
		YeGroupOrder yeGroupOrder = new YeGroupOrder();
		yeGroupOrder.setGcid(gcid);
		yeGroupOrder.setGoprepay(sum);
		yeGroupOrder.setGorefundpay(0);
		yeGroupOrder.setGoreceivedpay(sum);
		yeGroupOrder.setGostate(1+"");
		yeGroupOrder.setGoinvoicingstate(1);
		yeGroupOrder.setWid(id);
		yeGroupOrder.setGoappointdate(date);
		long code2 = yeWorkerMapper.insertGroupOrder(yeGroupOrder);
		long goid = yeGroupOrder.getGoid();
		System.out.println("goid:"+goid);
		long goinvoicingnumber = 0;
		if(goid<1000000000){
			goinvoicingnumber = goid+1000000000;
		} else {
			goinvoicingnumber = goid;
		}

		//更新订单的流水号
		yeWorkerMapper.updateGOInvoicing(goinvoicingnumber,goid);

		JSONObject jsonObject4 = new JSONObject();
		if(code2==0){
			code =1;
			jsonObject4.put("code",code);
			jsonObject4.put("msg","提示：生成订单错误，请重试");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return  jsonObject4;
		}

		//插入订单与套餐关系表（含病人身份证号）
		//先获取上面最新插入的订单id,由事务保证是同一个id
		long lastGroupOrderID = yeWorkerMapper.selectGroupOrderIdLast();
		ArrayList<YeOrderPackageRelation> yeRelationArr= new ArrayList<>();
		for (int i = 0; i < arr.size(); i++)
		{
			YeOrderPackageRelation relation = new YeOrderPackageRelation();
			relation.setGoid(lastGroupOrderID);
			long packid = 1;
			for (int j = 0; j < listPackage.size(); j++)
			{
				if(arr.get(i).getPackname().equals(listPackage.get(j).getPackname()) ){
					packid = listPackage.get(j).getPackid();
					//把病人的选择的套餐id暂存到病人id字段,方便之后导检单的插入
					arr.get(i).setPid(packid);
					break;
				}
			}
			relation.setPackid(packid);
			relation.setPidentitynumber(arr.get(i).getPidentitynumber());
			yeRelationArr.add(relation);
		}

		long result = yeWorkerMapper.insertGroupOrderAndPackageRelation(yeRelationArr);
		if(result==0)
		{
			System.out.println("插入订单与套餐关系表失败");
			JSONObject jsonObject3 = new JSONObject();
			jsonObject3.put("code", 1);
			jsonObject3.put("msg", "提示：插入订单与套餐关系表失败");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return jsonObject3;
		}


		//插入病人表和导检单表
		System.out.println("gcid:"+gcid);
//		for (int i = 0; i < arr.size(); i++)
//		{
//			arr.get(i).setPstate(1);
//			arr.get(i).setGcid(gcid);
//		}
//		if(type.equals("单人")){
//			//插入病人信息
//			long result2 = yeWorkerMapper.insertPatient(arr);
//		} else{
//			//excel提交
//			//先删除该单位之前的病人数据
//			long result1 = yeWorkerMapper.deletePatientByGdID(gcid);
//			//插入该单位新的病人信息
//			long result2 = yeWorkerMapper.insertPatient(arr);
//		}

		List<YePatient> yePatients = yeWorkerMapper.selectPatient(gcid);
		List<YePatient> haveList = new ArrayList<>();
		List<YePatient> noList = new ArrayList<>();

		//先区分该单位本次名单中人员信息，是否在数据库中已经存在，分别存入两个集合
		for (int i = 0; i < arr.size() ; i++)
		{
			int count = 0;
			for (int j = 0; j < yePatients.size(); j++)
			{
				if(arr.get(i).getPidentitynumber()!=null&&
						yePatients.get(j).getPidentitynumber().equals(arr.get(i).getPidentitynumber())){
					count++;
					haveList.add(arr.get(j));
					break;
				}
			}
			if(count==0){
				//未收录有的病人状态修改为1
				arr.get(i).setPstate(1);
				noList.add(arr.get(i));
			}
		}

//		//测试点
//		System.out.println(1/0);

//		//已存在病人实行状态的update,改为1
//		long code3 = 0;
//		if(haveList.size()>0){
//			for (int i = 0; i < haveList.size(); i++)
//			{
//				long b = yeWorkerMapper.updatePatientState(1,haveList.get(i).getPidentitynumber(),gcid,haveList.get(i).getPackname());
//				if(b==0){
//					code = 1;
//					jsonObject.put("code" ,code);
//					jsonObject.put("msg","跟新用户成员信息失败");
//					return jsonObject;
//				}
//			}
//		}
		//未存在的病人直接插入
		if(noList.size()>0){
			long l = yeWorkerMapper.insertPatient(noList);
			if(l!=noList.size()){
				code = 1;
				jsonObject.put("code" ,code);
				jsonObject.put("msg","插入用户成员信息失败");
				return jsonObject;
			}
		}

		List<YeGuideCheck> arrGuide = new ArrayList<>();
		//获得套餐id，暂存在状态字段
		List<YePatient> arrayList = yeWorkerMapper.selectPatientOnState(gcid,1);
		ArrayList<YePatient> arrNew = new ArrayList<>();

		//获取套餐id，暂存到状态id中
		for (int i = 0; i < arrayList.size(); i++)
		{
			for (int j = 0; j <listPackage.size() ; j++)
			{
				if(listPackage.get(j).getPackname().equals(arrayList.get(i).getPackname())){
					arrayList.get(i).setPstate(listPackage.get(j).getPackid());
				}
			}
		}

		for (int i = 0; i < arr.size(); i++)
		{
			for (int j = 0; j < arrayList.size(); j++)
			{
				if(arr.get(i).getPidentitynumber().equals(arrayList.get(j).getPidentitynumber())){
					arrNew.add(arrayList.get(j));
				}
			}
		}
		//根据单位病人的身份证号和单位id生成识别卡号
		Date date2 = new Date(System.currentTimeMillis());
		for (int i = 0; i < arrNew.size(); i++)
		{
			YeGuideCheck yeGuideCheck = new YeGuideCheck();
			yeGuideCheck.setGoid(lastGroupOrderID);
			//之前把暂存到病人状态中套餐id取出
			yeGuideCheck.setPackid(arrNew.get(i).getPstate());
//			yeGuideCheck.setPcardnumber(arrayList.get(i).getPcardnumber());

			long pid = arrNew.get(i).getPid();
			String pidentitynumber = arrNew.get(i).getPidentitynumber();
			String pcard = pidentitynumber+gcid;
			yeGuideCheck.setPcardnumber(pcard);
			yeGuideCheck.setGcdate(date2);
			//导检单处理状态为1
			yeGuideCheck.setGcstate(1+"");
			arrGuide.add(yeGuideCheck);
			//更新病人表的卡号
			yeWorkerMapper.updatePatientCardNumber(pcard,pid);

		}

		//插入导检单表
		for (int i = 0; i < arrGuide.size(); i++)
		{
			long insertGuide = yeWorkerMapper.insertGuideCheck(arrGuide.get(i));
			long gcidInsert = arrGuide.get(i).getGcid();

		}
		//测试点
//		System.out.println(1/0);

		return jsonObject;
	}

	@Transactional
	public boolean groupClientRegister(String account, String name, String pass,String phone,String email){
		long l = yeWorkerMapper.insertGroupClient(name,account,pass,0,phone,1,email);
		boolean flag = false;
		if(l>0){
			flag = true;
		}
		return flag;
	}

	//获取即时的全部套餐信息
	@Transactional
	public List<YePackage> selectAllPackage(){
		ArrayList<YePackage> arr = new ArrayList<>();
		arr = yeWorkerMapper.selectAllPackage();
		return arr;
	}

	//导出名单，获取单位所有正在处理成员的名单,以excel的方式下载
	@Transactional
	public JSONObject excelOut(HttpServletResponse response,HttpServletRequest request, String gcAccount) throws IOException
	{
		JSONObject jsonObject = new JSONObject();
		YeGroupClient yeGroupClient = yeWorkerMapper.selectGcAccount(Long.valueOf(gcAccount));
		if(yeGroupClient==null){
			jsonObject.put("code",1);
			jsonObject.put("msg","单位账号不存在");
			return jsonObject;
		}
		long gcid = yeGroupClient.getGcid();
		List<YePatient> arr = yeWorkerMapper.selectPatientByGcAccount(gcid);

		//判断单位是否存在病人信息
		if(arr.size()==0){
			System.out.println("不存在病人信息");
//			request.setAttribute("outmsg","不存在人员信息")
			jsonObject.put("code",1);
			jsonObject.put("msg","不存在正在受理病人的信息");
			return jsonObject;
		}

		//1.在内存中创建一个excel文件
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
		//2.创建工作簿
		XSSFSheet sheet = xssfWorkbook.createSheet();
		//3.创建标题行
		XSSFRow titlerRow = sheet.createRow(0);
		titlerRow.createCell(0).setCellValue("姓名");
		titlerRow.createCell(1).setCellValue("电话");
		titlerRow.createCell(2).setCellValue("单位账号");
		titlerRow.createCell(3).setCellValue("性别");
		titlerRow.createCell(4).setCellValue("身份证号");
		titlerRow.createCell(5).setCellValue("套餐");
		titlerRow.createCell(6).setCellValue("卡号");

		//4.遍历数据,创建数据行
		for (YePatient patient : arr) {
			//获取最后一行的行号
			int lastRowNum = sheet.getLastRowNum();
			XSSFRow dataRow = sheet.createRow(lastRowNum + 1);
			dataRow.createCell(0).setCellValue(patient.getPname());
			dataRow.createCell(1).setCellValue(patient.getPphone());
			dataRow.createCell(2).setCellValue(gcAccount);
			dataRow.createCell(3).setCellValue(patient.getPgender());
			dataRow.createCell(4).setCellValue(patient.getPidentitynumber());
			dataRow.createCell(5).setCellValue(patient.getPackname());
			dataRow.createCell(6).setCellValue(patient.getPcardnumber());
		}
		//5.创建文件名

		String fileName = yeGroupClient.getGcname()+"单位受理名单.xlsx";
		//6.获取输出流对象
		ServletOutputStream outputStream = response.getOutputStream();

		//7.获取mimeType
		ServletContext servletContext = request.getServletContext();
		String mimeType = servletContext.getMimeType(fileName);
		System.out.println(mimeType);
		//8.获取浏览器信息,对文件名进行重新编码
		fileName = YeFileUtils1.filenameEncoding(fileName,request);

		//9.设置信息头
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition","attachment;filename="+fileName);
		//10.写出文件,关闭流
		xssfWorkbook.write(outputStream);
		xssfWorkbook.close();
		jsonObject.put("code",0);
		return jsonObject;
	}

	@Transactional
	public void modelDownload (HttpServletResponse response,HttpServletRequest request){

		//1.在内存中创建一个excel文件
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
		//2.创建工作簿sheet0
		XSSFSheet sheet = xssfWorkbook.createSheet();
		//3.创建标题行
		XSSFRow titlerRow = sheet.createRow(0);
		titlerRow.createCell(0).setCellValue("姓名");
		titlerRow.createCell(1).setCellValue("电话");
		titlerRow.createCell(2).setCellValue("单位账号");
		titlerRow.createCell(3).setCellValue("性别");
		titlerRow.createCell(4).setCellValue("身份证号");
		titlerRow.createCell(5).setCellValue("套餐");


		//创建第二张工作簿sheet1
		XSSFSheet sheet1 = xssfWorkbook.createSheet();
		//创建标题行
		XSSFRow titlerRow1 = sheet1.createRow(0);
		titlerRow1.createCell(0).setCellValue("套餐名称");
		titlerRow1.createCell(1).setCellValue("套餐介绍");
		titlerRow1.createCell(2).setCellValue("套餐原价");
		titlerRow1.createCell(3).setCellValue("可享折扣");

		//获取所有的套餐信息，包括套餐的原价
		ArrayList<YePackage> yePackages = yeWorkerMapper.selectAllPackage();
		for (int i = 0; i < yePackages.size(); i++)
		{
			long l = yeWorkerMapper.selectPackageCharge(yePackages.get(i).getPackname());
			yePackages.get(i).setTemp1(l+"");
		}

		for (YePackage yePackage : yePackages)
		{
			//获取最后一行的行号
			int lastRowNum = sheet1.getLastRowNum();
			XSSFRow dataRow = sheet1.createRow(lastRowNum + 1);
			dataRow.createCell(0).setCellValue(yePackage.getPackname());
			dataRow.createCell(1).setCellValue(yePackage.getPackintroduction());
			dataRow.createCell(2).setCellValue(yePackage.getTemp1());
			dataRow.createCell(3).setCellValue(yePackage.getPackdiscount());
		}

		//5.创建文件名
		String fileName = "录入人员模板和套餐信息表.xlsx";
		//6.获取输出流对象
		ServletOutputStream outputStream = null;
		try
		{
			outputStream = response.getOutputStream();
			//7.获取mimeType
			ServletContext servletContext = request.getServletContext();
			String mimeType = servletContext.getMimeType(fileName);
			System.out.println(mimeType);
			//8.获取浏览器信息,对文件名进行重新编码
			fileName = YeFileUtils1.filenameEncoding(fileName,request);
			//9.设置信息头
			response.setContentType(mimeType);
			response.setHeader("Content-Disposition","attachment;filename="+fileName);

			//10.写出文件,关闭流
			xssfWorkbook.write(outputStream);
			xssfWorkbook.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	//查询病人列表
	@Transactional
	public List<Object> patientTableMethod(String name ,String gcname, String phone, String date, RowBounds rowBounds){

		java.util.Date dateNow = null;
		java.sql.Date dateUse = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if(date!=null&&!date.equals("")){
			try
			{
				dateNow = simpleDateFormat.parse(date);
				System.out.println(dateNow);
				dateUse = new java.sql.Date(dateNow.getTime());
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
		} else{
			dateNow = null;
		}
		if(name==null||name.equals("")){
			name = null;
		} else{
			name = "%"+name+"%";
		}
		if(gcname==null||gcname.equals("")){
			gcname = null;
		} else{
			gcname =  "%"+gcname+"%";
		}

		if(phone==null||phone.equals("")){
			phone= null;
		}

		System.out.println("last"+name+gcname+phone+dateUse);
		List<YePatient> yePatients = yeWorkerMapper.selectPatientForTable(name, gcname, dateUse, phone, rowBounds);
		long count = yeWorkerMapper.selectCountPatientForTable(name, gcname, dateUse, phone, rowBounds);
		List<Object> list = new ArrayList<>();
		list.add(0,yePatients);
		list.add(1,count);
		return  list;
	}

	//查询资金列表
	@Transactional
	public List<Object> fundTableMethod(String name ,String account, String type, String date, RowBounds rowBounds){

		java.util.Date dateNow = null;
		java.sql.Date dateUse = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if(date!=null&&!date.equals("")){
			try
			{
				dateNow = simpleDateFormat.parse(date);
				System.out.println(dateNow);
				dateUse = new java.sql.Date(dateNow.getTime());
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
		} else {
			dateUse = null;
		}

		if(name==null||name.equals("")){
			name = null;
		} else{
			name = "%"+name+"%";
		}
		if(type==null||type.equals("")){
			type = null;
		} else{
			type =  "%"+type+"%";
		}

		long accountUse =0;
		if(account==null||account.equals("")){
			accountUse = 0;
		} else{
			accountUse = Long.valueOf(accountUse);
		}

		System.out.println("last:"+name+account+type+dateUse);
		List<YeFundTransaction> yeFund = yeWorkerMapper.selectFundTransaction(name, accountUse, dateUse, type, rowBounds);
		long count = yeWorkerMapper.selectCountFund(name, accountUse, dateUse, type, rowBounds);
		List<Object> list = new ArrayList<>();
		list.add(0,yeFund);
		list.add(1,count);
		return  list;
	}

	//查询报告列表
	@Transactional
	public List<Object> selectReport(String packname,String pname, String goid ,RowBounds rowBounds){


		if(pname==null||pname.equals("")){
			pname = null;
		} else{
			pname = "%"+pname+"%";
		}
		if(packname==null||packname.equals("")){
			packname = null;
		} else{
			packname =  "%"+packname+"%";
		}
		long lGoid = 0 ;
		if(goid!=null&&!goid.equals("")){
			if(isNumeric(goid)){
				lGoid = Long.valueOf(goid);
			}
		}

		List<YeCheckupReport> yeReport = yeWorkerMapper.selectForReport(lGoid,packname,pname, rowBounds);
		System.out.println(yeReport.size()+"3333");
		for (int i = 0; i <yeReport.size() ; i++)
		{
			System.out.println(yeReport.get(i)+"1111");
		}

		long count = yeWorkerMapper.selectCountForReport(lGoid,packname,pname, rowBounds);
		List<Object> list = new ArrayList<>();
		list.add(0,yeReport);
		list.add(1,count);
		return  list;
	}


	//查询订单列表
	@Transactional
	public List<Object> orderTableMethod(String name ,String account, String date, RowBounds rowBounds){

		java.util.Date dateNow = null;
		java.sql.Date dateUse = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if(date!=null&&!date.equals("")){
			try
			{
				dateNow = simpleDateFormat.parse(date);
				System.out.println(dateNow);
				dateUse = new java.sql.Date(dateNow.getTime());
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
		} else {
			dateUse = null;
		}

		if(name==null||name.equals("")){
			name = null;
		} else{
			name = "%"+name+"%";
		}

		long accountUse =0;
		if(account==null||account.equals("")){
			accountUse = 0;
		} else{
			if(isNumeric(account)){
				accountUse = Long.valueOf(account);
			}
		}

		System.out.println("last:"+name+account+dateUse);
		List<YeGroupOrder> yeFund = yeWorkerMapper.selectOrderByCondition(name, accountUse, dateUse,  rowBounds);
		long count = yeWorkerMapper.selectCountOrderByCondition(name, accountUse, dateUse,  rowBounds);
		List<Object> list = new ArrayList<>();
		list.add(0,yeFund);
		list.add(1,count);
		return  list;
	}

	public static boolean isNumeric(String str){
		Pattern pattern = compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<YePatient> selectLastGroupPatientMessage(long gcaccount){
		List<YePatient> yePatients = yeWorkerMapper.selectNewestGroupOrder(gcaccount);
		return yePatients;
	}

	//插入交易记录表
	@Transactional
	public long insertFund(YeFundTransaction yeFundTransaction){
		long l = yeWorkerMapper.insertFundTransaction(yeFundTransaction);
		return  l;
	}

	//充值，修改单位账号余额
	@Transactional
	public long recharge(String gcaccount, String amount){
		YeGroupClient yeGroupClient = yeWorkerMapper.selectGcAccount(Long.valueOf(gcaccount));
		double gcbalance = yeGroupClient.getGcbalance();
		gcbalance  += Double.valueOf(amount);
		long l = yeWorkerMapper.updateGcBalance(gcbalance, Long.valueOf(gcaccount));
		return l;
	}

	@Transactional
	public long selecFund(){
		long maxID = yeWorkerMapper.selectMaxFundID();
		return maxID;
	}

	//自动结算(暂时为每天的凌晨两点，数据局库可调整时间)
	@Transactional(propagation = Propagation.SUPPORTS)
	public void automaticAccount(){
		//先获取所有过期需要结束的订单id
		List<YeGroupOrder> arrGoId = yeWorkerMapper.selectOrderOutOfDeal();
//		//一个map，用来存储订单id和该订单所需要的退款金额
//		Map<Long,Double> mapRefund = new HashMap<>();
		//遍历里所有的订单id，
		for (int i = 0; i < arrGoId.size(); i++)
		{
			double goidSum = 0;
			List<YeGuideCheck> yeGuideChecksArr = yeWorkerMapper.selectGcByGoId(arrGoId.get(i).getGoid());
			for (int j = 0; j <yeGuideChecksArr.size() ; j++)
			{
				//由导检单中的套餐id，查询套餐对应的折扣
				long discount = yeWorkerMapper.selectForDiscount(yeGuideChecksArr.get(j).getPackid());
				//查询导检单对应的未做项目的原价
				double sum = yeWorkerMapper.selectGuideChargeNODeal(yeGuideChecksArr.get(j).getGcid());
				double refund = sum*discount/100;
				goidSum +=refund;
				//把过期导检单的状态改为0
				yeWorkerMapper.updateGuideCheckState("0",yeGuideChecksArr.get(j).getGcid());
			}
			//修改单位的余额
			yeWorkerMapper.updateClientBalanceDecrease(goidSum,arrGoId.get(i).getGcid());
			//修改订单状态为0，修改退款金额，和实收金额
			yeWorkerMapper.updateOrderOutOfDeal(goidSum,arrGoId.get(i).getGoid());
		}
	}

	//单位申请退款（结算）
	public JSONObject refundMethod(String account, String pass,String order){
		//先判断单位账号密码是否正确
		JSONObject jsonObject = new JSONObject();
		YeGroupClient yeGroupClient = yeWorkerMapper.selectGcAccount(Long.valueOf(account));
		if(yeGroupClient==null){
			jsonObject.put("code",1);
			jsonObject.put("msg","账号不存在");
			return jsonObject;
		}
		if(!yeGroupClient.getGcpass().equals(pass)){
			jsonObject.put("code",1);
			jsonObject.put("msg","密码不正确");
			return  jsonObject;
		}
		//查询订单表
		YeGroupOrder yeGroupOrder = yeWorkerMapper.selecOrderByGoid(Long.valueOf(order));
		if(yeGroupOrder==null){
			jsonObject.put("code",1);
			jsonObject.put("msg","订单不存在");
			return jsonObject;
		}
		if(yeGroupOrder.getGcid()!=yeGroupClient.getGcid()){
			jsonObject.put("code",1);
			jsonObject.put("msg","单位不存在此订单");
			return jsonObject;
		}
		if(yeGroupOrder.getGostate().equals("0")){
			jsonObject.put("code",1);
			jsonObject.put("msg","订单已结算");
			return jsonObject;
		}

		double goidSum = 0;
		//查询此订单下的所有导检单
		List<YeGuideCheck> yeGuideChecksArr = yeWorkerMapper.selectGcByGoId(yeGroupOrder.getGoid());
		for (int j = 0; j <yeGuideChecksArr.size() ; j++)
		{
			//由导检单中的套餐id，查询套餐对应的折扣
			long discount = yeWorkerMapper.selectForDiscount(yeGuideChecksArr.get(j).getPackid());
			//查询导检单对应的未做项目的原价
			double sum = yeWorkerMapper.selectGuideChargeNODeal(yeGuideChecksArr.get(j).getGcid());
			System.out.println("套餐折扣:"+discount);
			double refund = sum*discount/100;
			goidSum +=refund;
			System.out.println("退款金额："+goidSum);
			System.out.println("导检单id："+yeGuideChecksArr.get(j).getGcid());
			//把过期导检单的状态改为0
			yeWorkerMapper.updateGuideCheckState("0",yeGuideChecksArr.get(j).getGcid());
			//修改过期体检项目表状态为2，已结束
			yeWorkerMapper.updateCheckupProject(yeGuideChecksArr.get(j).getGcid(),2);
		}
		//修改单位的余额
		yeWorkerMapper.updateClientBalanceDecrease(goidSum,yeGroupOrder.getGcid());
		//修改订单状态为0，修改退款金额，和实收金额
		yeWorkerMapper.updateOrderOutOfDeal(goidSum,yeGroupOrder.getGoid());

		//退款成功
		jsonObject.put("code",0);
		return jsonObject;
	}

	//打印发票
	@Transactional
	public YeGroupOrder forPrint(String goidTemp){
		long goid = Long.valueOf(goidTemp);
		YeGroupOrder yeGroupOrder = yeWorkerMapper.selectOrderForOne(goid);
		return  yeGroupOrder;
	}

	//申请延长预约天数
	@Transactional
	public long editDays(Map<String,Object> mapTable,Map<String,Object> mapForm){
		double  goidS = (double)mapTable.get("goid");
		long goid = (long)goidS;
		long days = Long.valueOf((String)mapForm.get("days"));
		long l = yeWorkerMapper.editDay(days, goid);
		return l;
	}

}
