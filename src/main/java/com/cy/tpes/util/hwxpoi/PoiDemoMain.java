package com.cy.tpes.util.hwxpoi;
import com.cy.tpes.entity.hwxbean.*;
import com.cy.tpes.util.hwxpoi.bean.PoiImage;
import com.cy.tpes.util.hwxpoi.bean.IPoiWordTable;
import com.cy.tpes.util.hwxpoi.bean.PWATwithHeaderBottom;
import com.cy.tpes.util.hwxpoi.bean.PoiWordAutoTable;

import com.cy.tpes.util.hwxpoi.PoiWordUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.util.*;

/**
 * created on 2020/3/9
 *
 * @author:胡文贤
 **/

public class PoiDemoMain
{
	public void createWord(List<ItemList> list, Pinfo pinfo, List<Project> projectList, List<ProjectList> projectListList, CheckupReport checkupReport){
		//创建文件夹
		System.out.println("createword: 创建文件夹");

		//模板文件地址
		String inputUrl = "./word/word-demo.docx";
		//新生产的模板文件
		String filename = pinfo.getGchid()+".docx";
		String outputUrl = "./word/"+filename;
//		如果存在，创建一个
		File docFile = new File(outputUrl);


		//名字加导检单号   如果不存在新建一个   打印成功以后要插入数据
//		String outputUrl = "./word/output.docx";


		System.out.println("pinfo String :"+pinfo.getGcid()+pinfo.getGcid()+pinfo.getPname()+pinfo.getPgender()+pinfo.getPphone());
		// （一）文本替换数据
		Map<String, String> testMap = new HashMap<String, String>();

		testMap.put("t_gcname", String.valueOf(pinfo.getGcname()));
		testMap.put("t_gcid", String.valueOf(pinfo.getGcid()));
		testMap.put("t_gchid", String.valueOf(pinfo.getGchid()));
		testMap.put("t_pname", String.valueOf(pinfo.getPname()));
		testMap.put("t_pgender", String.valueOf(pinfo.getPgender()));
		testMap.put("t_pphone", String.valueOf(pinfo.getPphone()));
		testMap.put("t_summary", String.valueOf(checkupReport.getCrsummary()));
		testMap.put("t_suggest", String.valueOf(checkupReport.getCrsuggest()));
		testMap.put("t_lifeguide", String.valueOf(checkupReport.getCrlifeguide()));
		testMap.put("t_author", String.valueOf(checkupReport.getWname()));
		testMap.put("t_crdate", String.valueOf(checkupReport.getCrdate()));
//		System.out.println("phone"+ " "+testMap.get("t_phone") +"author"+testMap.get("t_author"));
		//（二）动态表格数据
		Map<String, List<IPoiWordTable>> autoTableMap = new HashMap();
		PoiWordAutoTable writeData = new PoiWordAutoTable(list.size(),6);
		PoiWordAutoTable pdata = new PoiWordAutoTable(projectList.size(),3);
		PoiWordAutoTable psdata = new PoiWordAutoTable(projectListList.size(),3);

		for (int i = 0; i <projectList.size(); i++)
		{
			pdata.setCell(i, 0, i+"");
			pdata.setCell(i, 1, projectList.get(i).getDname()+"");
			pdata.setCell(i, 2, projectList.get(i).getPname()+"");
		}
		autoTableMap.put("at_row_autoRow1", Arrays.<IPoiWordTable>asList(pdata));

		for (int i = 0; i <list.size(); i++)
		{
			writeData.setCell(i, 0, list.get(i).getIid()+"");
			writeData.setCell(i, 1, list.get(i).getIname()+"");
			writeData.setCell(i, 2, list.get(i).getCicompareresult()+"");
			writeData.setCell(i, 3, list.get(i).getCicheckvalue()+"");
			writeData.setCell(i, 4, list.get(i).getRange()+"");
			writeData.setCell(i, 5, list.get(i).getIunit()+"");
		}

		autoTableMap.put("at_row_autoRow", Arrays.<IPoiWordTable>asList(writeData));


		for (int i = 0; i <projectListList.size(); i++)
		{
			psdata.setCell(i, 0, i+"");
			psdata.setCell(i, 1, projectListList.get(i).getPname()+"");
			psdata.setCell(i, 2, projectListList.get(i).getCpsummary()+"");
		}

		autoTableMap.put("at_row_autoRow2", Arrays.<IPoiWordTable>asList(psdata));

		//at_row_autoRow2





		Map<String, String> noneTableTextMap = new HashMap();

		// （三）图片替换数据
		Map<String, PoiImage> imageMap = new HashMap<String, PoiImage>();
		imageMap.put("img00_pic1", new PoiImage("./word/4.jpg",
				XWPFDocument.PICTURE_TYPE_JPEG,
				100,
				100));

		PoiWordUtil.changWord(inputUrl, outputUrl, testMap, autoTableMap, noneTableTextMap, imageMap);
		System.out.println("更改成功");
	}








	public static void main(String[] args) {
		//模板文件地址
		String inputUrl = "./word/word-demo.docx";
		//新生产的模板文件
		String outputUrl = "./word/output.docx";

		// （一）文本替换数据
		Map<String, String> testMap = new HashMap<String, String>();
		testMap.put("t_author", "凶猛的贤哥");
		testMap.put("t_email", "448241091@qq.com");
		testMap.put("t_company", "xxx");
		testMap.put("t_companyNumber", "COMPANY001");
		testMap.put("t_year", "2018");
		testMap.put("t_month", "11");
		testMap.put("t_day", "30");
		testMap.put("t_poi_cool", "【我不会影响左右两边的文本】");

		//（二）动态表格数据
		Map<String, List<IPoiWordTable>> autoTableMap = new HashMap();
		PoiWordAutoTable writeData = new PoiWordAutoTable(5,3);
		writeData.setCell(0, 0, "股东1");
		writeData.setCell(0, 1, "股东类型1");
		writeData.setCell(0, 2, "很有钱");

		writeData.setCell(1, 0, "股东2");
		writeData.setCell(1, 1, "股东类型2");
		writeData.setCell(1, 2, "很穷");

		writeData.setCell(2, 0, "股东3");
		writeData.setCell(2, 1, "股东类型3");
		writeData.setCell(2, 2, "很穷3");

		writeData.setCell(3, 0, "股东4");
		writeData.setCell(3, 1, "股东类型4");
		writeData.setCell(3, 2, "很穷4");

		writeData.setCell(4, 0, "股东5");
		writeData.setCell(4, 1, "5");
		writeData.setCell(4, 2, "5");

		autoTableMap.put("at_row_autoRow", Arrays.<IPoiWordTable>asList(writeData));

		// 动态表格(max01)
		PoiWordAutoTable data1 = new PoiWordAutoTable(2,2);
		data1.setCell(0, 0, "企业名称");
		data1.setCell(0, 1, "xxx");
		data1.setCell(1, 0, "注册号");
		data1.setCell(1, 1, "XXX123");

		PoiWordAutoTable data2 = new PoiWordAutoTable(2,2);
		data2.setCell(0, 0, "企业名称");
		data2.setCell(0, 1, "xxx");
		data2.setCell(1, 0, "注册号");
		data2.setCell(1, 1, "---x2---");
		autoTableMap.put("at_max01_auto", Arrays.<IPoiWordTable>asList(data1, data2));

		// 动态表格(max02)
		PWATwithHeaderBottom pwat1 = new PWATwithHeaderBottom(3,2);
		pwat1.setTitle("1.实际控制人：xxx（身份证号：441900XXXXXXX）查询日期：1995年11月23日");
		pwat1.setBottom("");
		pwat1.setCell(0, 1, "信用卡");
		pwat1.setCell(1, 0, "账户数");
		pwat1.setCell(1, 1, "2个");
		pwat1.setCell(2, 0, "未结清/未注销账户数");
		pwat1.setCell(2, 1, "2个");

		PWATwithHeaderBottom pwat2 = new PWATwithHeaderBottom(3,2);
		pwat2.setTitle("2.实际控制人：xxx（身份证号：xxx）查询日期：2018年11月22日");
		pwat2.setBottom("底部跟随文本");
		pwat2.setCell(0, 1, "信用卡");
		pwat2.setCell(1, 0, "账户数");
		pwat2.setCell(1, 1, "255个");
		pwat2.setCell(2, 0, "未结清/未注销账户数");
		pwat2.setCell(2, 1, "255个");
		autoTableMap.put("at_max02_auto", Arrays.<IPoiWordTable>asList(pwat1, pwat2));

		Map<String, String> noneTableTextMap = new HashMap();

		// （三）图片替换数据
		Map<String, PoiImage> imageMap = new HashMap<String, PoiImage>();
		imageMap.put("img00_pic1", new PoiImage("./word/4.jpg",
				XWPFDocument.PICTURE_TYPE_JPEG,
				100,
				100));

		PoiWordUtil.changWord(inputUrl, outputUrl, testMap, autoTableMap, noneTableTextMap, imageMap);
	}
}
