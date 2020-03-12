package com.cy.tpes.mapper.hjx;

import com.cy.tpes.entity.hjx.GroupClient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WxGroupClientMapper {
   GroupClient find(long gcaccount);
   int addGroupClient(@Param("groupClient") GroupClient groupClient);
   int updateClient(@Param("groupClient") GroupClient groupClient);

}
