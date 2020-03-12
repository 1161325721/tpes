package com.cy.tpes.mapper.hjx;

import com.cy.tpes.entity.hjx.Patient;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WxPatientMapper {
  int  insertPatient(Patient patient);

   List<Patient> findByidentityCard(String findByidentityCard);

    List<Patient>  findPatientinfo(String pidentitynumber, String pname, String gcname);


    int updatepatientInfo(String pphone, String pgender, String pid);

}
