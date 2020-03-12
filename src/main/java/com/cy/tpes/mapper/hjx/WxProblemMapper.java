package com.cy.tpes.mapper.hjx;

import com.cy.tpes.entity.hjx.Problem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WxProblemMapper {
   List<Problem> findproblem(String problemtittle, int ofset, int limit);

    int  problemcount(String problemtittle);
}
