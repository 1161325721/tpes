package com.cy.tpes.entity.sysmgr;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(value = "baiduface")
public class BaiduFace
{

	private String appid;
	private String apikey;
	private String secretkey;

	public String getAppid()
	{
		return appid;
	}

	public void setAppid(String appid)
	{
		this.appid = appid;
	}

	public String getApikey()
	{
		return apikey;
	}

	public void setApikey(String apikey)
	{
		this.apikey = apikey;
	}

	public String getSecretkey()
	{
		return secretkey;
	}

	public void setSecretkey(String secretkey)
	{
		this.secretkey = secretkey;
	}
}
