package com.foan.crm.service;

import com.foan.crm.constants.CRMServiceErrorCode;
import com.foan.crm.entity.Company;
import com.foan.crm.exception.CRMException;
import com.foan.crm.handler.CRMExceptionFactory;

import com.foan.crm.repository.CompanyRepository;
import com.foan.crm.repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepospory;

    @Autowired
    private RedisRepository redisRepository;

    public Company getCompany(String name){
        return companyRepospory.findByName(name).
                orElseThrow(() -> CRMExceptionFactory.create(CRMException.class, CRMServiceErrorCode.CAN_NOT_FINDCLINETDATA));
    }

    public Company addCompany(String name, String address, String token){
        Company company = new Company();
        company.setName(name);
        company.setAddress(address);
        String createdby = "";
        try {
            createdby = redisRepository.findKey(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        company.setCreated_by(createdby);
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        company.setCreatedat(ts);
        return companyRepospory.save(company);
    }

    public Company updateCompany(String name, String address, String token){
        if(companyRepospory.findByName(name).isPresent()){
            throw CRMExceptionFactory.create(CRMException.class, CRMServiceErrorCode.CAN_NOT_FINDCOMPANYDATA);
        }
        Company company = companyRepospory.findByName(name).get();
        company.setAddress(address);
        String updatedby = "";
        try {
            updatedby = redisRepository.findKey(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        company.setUpdateby(updatedby);
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        company.setUpdatedat(ts);
        return companyRepospory.save(company);
    }

    public Boolean deleteCompany(String name, String token){
        if(companyRepospory.findByName(name).isPresent()){
            throw CRMExceptionFactory.create(CRMException.class, CRMServiceErrorCode.CAN_NOT_FINDCOMPANYDATA);
        }
        Company company = companyRepospory.findByName(name).get();
        companyRepospory.delete(company);
        return true;
    }
}
