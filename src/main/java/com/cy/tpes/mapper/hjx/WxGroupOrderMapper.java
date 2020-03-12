package com.cy.tpes.mapper.hjx;

import com.cy.tpes.entity.hjx.GroupOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WxGroupOrderMapper {
  int  insertOrder(GroupOrder groupOrder);
  int  insertOrderRelation(long goid, long packid, String pidentitynumber);
  List<GroupOrder> findbyGcid(long gcid, String gostate);
  int ordercount(long goid);

  int reportcount(long goid);
  int updateorder(long goid, String gostate, double gorefundpay, double goreceivedpay);


}
