package com.cy.tpes.mapper.hjx;

import com.cy.tpes.entity.hjx.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WxProjectMapper {
   List<Project> findproject(String packid);
}
