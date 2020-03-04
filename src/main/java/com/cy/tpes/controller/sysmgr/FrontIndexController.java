package com.cy.tpes.controller.sysmgr;


import com.baidu.aip.face.AipFace;
import com.baidu.aip.util.Base64Util;
import com.cy.tpes.entity.sysmgr.RespDate;
import com.cy.tpes.service.sysmgr.CheckService;
import org.apache.ibatis.session.RowBounds;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/front/")
public class FrontIndexController
{

	@RequestMapping(path = "faceLogin",produces = "application/json")
	@ResponseBody
	public Object faceLogin(String img)
	{
		Object ob = imageMacth(img);

		if (null!=ob){



			return true;
		}

		return false;
	}


	public static void main(String[] args) throws IOException
	{
		AipFace aipFace = new AipFace("18665947","MC23hXWxWieVVIudkV5lZIGb","pDfUc1rOM2SQya67nIGVPTxoRisQ1iWr");
		HashMap<String, String> map = new HashMap<>();
		map.put("liveness_control", "NORMAL");
		map.put("quality_control", "LOW");

		String path = "C:\\Users\\127\\Desktop\\file\\1.jpg";
		byte[] bytes = Files.readAllBytes(Paths.get(path));
		String imageEncode  = Base64Util.encode(bytes);

		JSONObject res = aipFace.addUser(imageEncode,"BASE64","tpesface","1",map);
		System.out.println(res.toString());
	}



	public void imageAdd(String image){
		AipFace aipFace = new AipFace("18665947","MC23hXWxWieVVIudkV5lZIGb","pDfUc1rOM2SQya67nIGVPTxoRisQ1iWr");
		HashMap<String, String> map = new HashMap<>();
		map.put("liveness_control", "NORMAL");
		map.put("quality_control", "LOW");
		JSONObject res = aipFace.addUser(image,"BASE64","tpesface","1",map);
		System.out.println(res.toString());
	}

	public Object imageMacth(String image){
		AipFace aipFace = new AipFace("18665947","MC23hXWxWieVVIudkV5lZIGb","pDfUc1rOM2SQya67nIGVPTxoRisQ1iWr");
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

		System.out.println(score);
		return id;
	}
}
