package com.cy.tpes.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * created on 2020/2/24
 *
 * @author:胡文贤
 * 文件工具类
 **/
public class HwxFileUtils
{
	public static Boolean uploadFile(byte[] file, String filePath, String fileName) throws Exception {
		FileOutputStream out = null;
		try {
			File targetFile = new File(filePath);
			//如果目录不存在，创建目录
			if(!targetFile.exists()){
				targetFile.mkdirs();
			}
			out = new FileOutputStream(filePath+fileName);
			out.write(file);
			out.flush();
			//写入成功
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			//写入失败
			return false;
		} finally {
			out.close();
		}
	}

}
