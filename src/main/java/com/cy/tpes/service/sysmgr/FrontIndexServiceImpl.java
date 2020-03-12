package com.cy.tpes.service.sysmgr;


import com.baidu.aip.face.AipFace;
import com.baidu.aip.util.Base64Util;
import com.cy.tpes.entity.sysmgr.BaiduFace;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;


@Service
public class FrontIndexServiceImpl implements FrontIndexService
{

	@Autowired
	private BaiduFace baiduFace;

	@Override
	public Boolean addface(String image,String userId)
	{

		AipFace aipFace = new AipFace(baiduFace.getAppid(),baiduFace.getApikey(),baiduFace.getSecretkey());
		HashMap<String, String> map = new HashMap<>();
		map.put("liveness_control", "NORMAL");
		map.put("quality_control", "LOW");
		JSONObject res = aipFace.addUser(image,"BASE64","tpesface",userId,map);
		System.out.println(res.toString());
		return false;
	}


	@Override
	public Object imageSearch(String image){
		AipFace aipFace = new AipFace(baiduFace.getAppid(),baiduFace.getApikey(),baiduFace.getSecretkey());
		HashMap<String, String> map = new HashMap<>();
		map.put("liveness_control", "NORMAL");
		map.put("quality_control", "NORMAL");
		JSONObject res = aipFace.search(image,"BASE64","tpesface",null);
		System.out.println(res.toString());
		if (res.getInt("error_code")!=0){
			return null;
		}
		int score = res.getJSONObject("result").getJSONArray("user_list").getJSONObject(0).getInt("score");
		int id = res.getJSONObject("result").getJSONArray("user_list").getJSONObject(0).getInt("user_id");

		if (score<80){
			return null;
		}
		System.out.println("ID:"+id);
		System.out.println("人脸相似度:"+score);
		return id;
	}

	@Override
	public Boolean imageDetect(String image)
	{
		Boolean flag  = false;
		AipFace aipFace = new AipFace(baiduFace.getAppid(),baiduFace.getApikey(),baiduFace.getSecretkey());
		JSONObject res = aipFace.detect(image,"BASE64",null);
		if (res.getInt("error_code")==0){
			flag = true;
		}
		return flag;
	}

	@Override
	public Boolean findUser(String userId)
	{
		Boolean flag  = false;

		AipFace aipFace = new AipFace("18665947","MC23hXWxWieVVIudkV5lZIGb","pDfUc1rOM2SQya67nIGVPTxoRisQ1iWr");
		HashMap<String, String> map = new HashMap<>();
		JSONObject res = aipFace.getUser(userId,"tpesface",map);
		System.out.println(res.toString());

		int error_code = res.getInt("error_code");

		if (res.getInt("error_code")==0){
			flag = true;
		}

		return flag;
	}

	@Override
	public Boolean updateUser(String userId,String img)
	{
		Boolean flag  = false;
		AipFace aipFace = new AipFace("18665947","MC23hXWxWieVVIudkV5lZIGb","pDfUc1rOM2SQya67nIGVPTxoRisQ1iWr");
		HashMap<String, String> map = new HashMap<>();
		JSONObject res = aipFace.updateUser(img,"BASE64","tpesface",userId,map);
		System.out.println(res.toString());
		if (res.getInt("error_code")==0){
			flag = true;
		}
		return flag;
	}
}
