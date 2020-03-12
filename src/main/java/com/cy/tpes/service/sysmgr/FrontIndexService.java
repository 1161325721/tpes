package com.cy.tpes.service.sysmgr;

public interface FrontIndexService
{
	public Boolean addface(String img,String userId);
	public Object imageSearch(String image);
	public Boolean imageDetect(String image);
	public Boolean findUser(String userid);
	public Boolean updateUser(String userId,String img);


}
