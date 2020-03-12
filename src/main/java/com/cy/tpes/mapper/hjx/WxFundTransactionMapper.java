package com.cy.tpes.mapper.hjx;

import com.cy.tpes.entity.hjx.FundTransaction;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WxFundTransactionMapper {

      int insertTrade(FundTransaction fundTransaction);

}
