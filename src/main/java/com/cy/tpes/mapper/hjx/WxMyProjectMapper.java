package com.cy.tpes.mapper.hjx;

import com.cy.tpes.entity.hjx.CheckupProject;
import com.cy.tpes.entity.hjx.CheckupReport;
import com.cy.tpes.entity.hjx.GuideCheck;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper

public interface WxMyProjectMapper {

   List<GuideCheck> findMyPro(String pidentitynumber, String pname, int ofset, int limit);
   List<CheckupProject>  findMyItem(String gcid, String cpid);

  int myProCount(String pidentitynumber, String pname);


    int  myreportcount(String pidentitynumber, String pname);
    List<GuideCheck>  findreport(String pidentitynumber, String pname, int ofset, int limit);
      List<CheckupReport> findreportinfo(String gcid, String crid);

}
