package com.cy.tpes.mapper.yeymapper;/**
 * @ClassName:GroupInspectionBillMapper
 * @Description: TTTT
 * @Date：2020/2/17 14:54
 * @Author ：Administrator
 **/
import com.cy.tpes.entity.yeyentity.YePatient;
import org.apache.ibatis.annotations.Mapper;

/**
 *@program: tpes
 *@description: 团体开单的业务
 *@author: liu yan
 *@create: 2020-02-17 14:54
 */
@Mapper
public interface YeGroupInspectionBillMapper
{
	public long insertPatientMessage(YePatient patient);
}
