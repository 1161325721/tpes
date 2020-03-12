package com.cy.tpes.mapper.hjx;

import com.cy.tpes.entity.hjx.Package;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WxPackageMapper {
     List<Package> findcombo(@Param("packname") String packname,
                             @Param("packid") String packid);
}
