package com.cy.tpes.controller.sysmgr;

import com.google.gson.Gson;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @program: Test20200109
 * @ClassName: StringToMapConverter
 * @description: Json字符串转Map
 * @author: JX190728
 * @create: 2020-01-09 15:48
 **/


@Component
public class StringToMapConverter implements Converter<String, HashMap<String,Object>>
{

	@Override
	public HashMap<String, Object> convert(String s)
	{
		HashMap<String, Object> map = null;
		Gson gson = new Gson();
		if (null==s){
			map = new HashMap<>();
		}else
		{
			System.out.println("自定义参数转换：String -> Map");
			System.out.println(s);
			map = gson.fromJson(s, HashMap.class);
		}

		//去除为空的参数
		Iterator<Map.Entry<String,Object>> it = map.entrySet().iterator();
		while (it.hasNext()){
			Map.Entry<String,Object> entry = it.next();
			String val = String.valueOf(entry.getValue());
			val =val.trim();
			if (null==val || val.length()==0){
				it.remove();
			}
		}

		return map;
	}
}
