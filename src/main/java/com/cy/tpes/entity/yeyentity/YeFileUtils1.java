package com.cy.tpes.entity.yeyentity;/**
 * @ClassName:FileUtils
 * @Description: TTTT
 * @Date：2020/2/12 20:13
 * @Author ：Administrator
 **/

import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @ClassName:FileUtils
 *@Description: TTTT
 * @Date：2020/2/12 20:13
 * @Author ：Administrator
 **/
public class YeFileUtils1
{

	public static String filenameEncoding(String filename, HttpServletRequest request) throws IOException
	{
		String agent = request.getHeader("User-Agent"); //获取浏览器
		if (agent.contains("Firefox")) {
			BASE64Encoder base64Encoder = new BASE64Encoder();
			filename = "=?utf-8?B?"
					+ base64Encoder.encode(filename.getBytes("utf-8"))
					+ "?=";
		} else if(agent.contains("MSIE")) {
			filename = URLEncoder.encode(filename, "utf-8");
		} else if(agent.contains ("Safari")) {
			filename = new String (filename.getBytes ("utf-8"),"ISO8859-1");
		} else {
			filename = URLEncoder.encode(filename, "utf-8");
		}
		return filename;
	}
}
